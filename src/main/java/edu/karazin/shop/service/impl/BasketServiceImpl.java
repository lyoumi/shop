package edu.karazin.shop.service.impl;


import edu.karazin.shop.dao.OrderDao;
import edu.karazin.shop.dao.OrderStoryDao;
import edu.karazin.shop.dao.UsersDao;
import edu.karazin.shop.model.*;
import edu.karazin.shop.service.BasketService;
import edu.karazin.shop.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BookStoreService bookStoreService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private OrderStoryDao orderStoryDataAccessObject;

    @Override
    public OrderList createOrder(User user) {
        OrderList orderList = new OrderList();
        orderList.setUserId(user.getId());
        orderDao.save(orderList);
        return orderList;
    }

    @Override
    public OrderList getOrderByUserId(User user){
        OrderList orderListByUserId = orderDao.getOrderListByUserId(user.getId());
        if (Objects.equals(orderListByUserId, null)) orderListByUserId = createOrder(user);
        return orderListByUserId;
    }

    @Override
    public OrderList addBookToOrder(User user, BookList book) {

        OrderList orderList = getOrderByUserId(user);
        if (orderList == null) orderList = createOrder(user);

        List<BookList> books = orderList.getBookLists();

        System.err.println("Books " + books);

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
        if (Objects.equals(orderDetails, null)) orderDetailsList = new ArrayList<>();

        orderDetailsList.add(orderDetails);

        orderList.setOrderDetails(orderDetailsList);

        orderDao.save(orderList);

        return orderList;
    }

    @Override
    public OrderList removeBookFromOrder(User user, Long id){
        BookList book = bookStoreService.getBookById(id);
        OrderList order = getOrderByUserId(user);

        List<BookList> books = order.getBookLists();
        books.remove(book);

        double totalPrice = 0;
        for (BookList bookFromOrder :
                books) {
            totalPrice += bookFromOrder.getPrice();
        }

        order.setTotalPrice(totalPrice);
        order.setBookLists(books);

        orderDao.save(order);

        return order;
    }

    @Override
    public void removeOrder(User user){
        orderDao.delete(getOrderByUserId(user));
    }

    @Override
    public void addOrderToStory(OrderList order){
        OrderStory orderStory = new OrderStory();
        orderStory.setName(usersDao.findOne(order.getUserId()).getUsername());
        String description = "";
        for (BookList book :
                order.getBookLists()) {
            description += book.getName() + ", " + book.getPrice() + "; ";
        }
        description += "Total: " + order.getTotalPrice() + ".";
        orderStory.setDescription(description);
        orderStoryDataAccessObject.save(orderStory);
    }

    @Override
    public List<OrderStory> getOrderStory(String name){
        return orderStoryDataAccessObject.findAllByName(name);
    }

}
