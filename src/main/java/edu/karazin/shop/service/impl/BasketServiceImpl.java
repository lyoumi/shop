package edu.karazin.shop.service.impl;


import edu.karazin.shop.repository.BookRepository;
import edu.karazin.shop.repository.OrderRepository;
import edu.karazin.shop.repository.OrderStoryRepository;
import edu.karazin.shop.repository.UsersRepository;
import edu.karazin.shop.model.*;
import edu.karazin.shop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OrderStoryRepository orderStoryRepository;

    @Override
    public OrderList createOrder(User user) {
        OrderList orderList = new OrderList();
        orderList.setUserId(user.getId());
        orderRepository.save(orderList);
        return orderList;
    }

    @Override
    public OrderList getOrCreateOrderByUserId(User user){
        OrderList orderListByUserId = orderRepository.getOrderListByUserId(user.getId());
        if (Objects.equals(orderListByUserId, null)) orderListByUserId = createOrder(user);
        return orderListByUserId;
    }

    @Override
    public OrderList addBookToOrder(User user, BookList book) {

        OrderList orderList = getOrCreateOrderByUserId(user);
        if (orderList == null) orderList = createOrder(user);

        List<BookList> books = orderList.getBookLists();

        if (Objects.equals(books, null)) books = new ArrayList<>();
        books.add(book);

        double totalPrice = 0;

        for (BookList bookList:
             books) {
            totalPrice += bookList.getPrice();
        }

        orderList.setBookLists(books);
        orderList.setTotalPrice(totalPrice);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setQuantity(1);

        List<OrderDetails> orderDetailsList = orderList.getOrderDetails();
        if (orderDetailsList == null) orderDetailsList = new ArrayList<>();

        orderDetailsList.add(orderDetails);

        orderList.setOrderDetails(orderDetailsList);

        orderList = orderRepository.save(orderList);

        return orderList;
    }

    @Override
    public OrderList removeBookFromOrder(User user, Long id){
        BookList book = bookRepository.findOne(id);
        OrderList order = getOrCreateOrderByUserId(user);

        List<BookList> books = order.getBookLists();
        if (books != null && books.contains(book)) {
            books.remove(book);
            double totalPrice = 0;
            for (BookList bookFromOrder :
                    books) {
                totalPrice += bookFromOrder.getPrice();
            }

            order.setTotalPrice(totalPrice);
            order.setBookLists(books);

            order = orderRepository.save(order);
        }
        return order;
    }

    @Override
    public void removeOrder(User user){
        orderRepository.delete(getOrCreateOrderByUserId(user).getOrderId());
    }

    @Override
    public OrderStory addOrderToStory(OrderList order){
        OrderStory orderStory = new OrderStory();
        orderStory.setName(usersRepository.findOne(order.getUserId()).getUsername());
        List<BookList> books = new ArrayList<>(order.getBookLists());
        orderStory.setBooks(books);
        orderStory.setDate(new Date());
        String description = "Total: " + order.getTotalPrice() + ".";
        orderStory.setDescription(description);
        return orderStoryRepository.save(orderStory);
    }

    @Override
    public List<OrderStory> getOrderStory(String name){
        return orderStoryRepository.findAllByName(name);
    }

    @Override
    public List<OrderStory> getAllOrders() {
        return (List<OrderStory>) orderStoryRepository.findAll();
    }

}
