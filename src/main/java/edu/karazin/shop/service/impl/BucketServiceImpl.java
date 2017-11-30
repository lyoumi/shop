package edu.karazin.shop.service.impl;


import edu.karazin.shop.dao.OrderDataAccessObject;
import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.OrderList;
import edu.karazin.shop.model.OrderDetails;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    OrderDataAccessObject orderDataAccessObject;

    @Override
    public OrderList createOrder(User user) {
        OrderList orderList = new OrderList();
        orderList.setUserId(user.getId());
        orderDataAccessObject.save(orderList);
        return orderList;
    }

    @Override
    public OrderList getOrder(int id) {
        return orderDataAccessObject.findOne(id);
    }

    @Override
    public List<OrderList> getAllOrders() {
        return (List<OrderList>) orderDataAccessObject.findAll();
    }

    @Override
    public List<BookList> getOrderBooks(int id) {
        return orderDataAccessObject.getOrderBooks(id);
    }

    @Override
    public OrderList getOrderByUserId(Integer id){
        return orderDataAccessObject.getOrderListByUserId(id);
    }

    @Override
    public OrderList addBookToOrder(User user, BookList book) {

        OrderList orderList = getOrderByUserId(user.getId());
        if (orderList == null) orderList = createOrder(user);

        List<BookList> books = orderList.getBookLists();

        System.err.println("Books " + books);

        if (Objects.equals(books, null)) books = new ArrayList<>();
        books.add(book);

        int totalPrice = 0;

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

        orderDataAccessObject.save(orderList);

        return orderList;
    }

}
