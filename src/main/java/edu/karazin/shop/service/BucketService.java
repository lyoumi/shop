package edu.karazin.shop.service;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.OrderList;
import edu.karazin.shop.model.User;

import java.util.List;

public interface BucketService {
    OrderList createOrder(User user);

    OrderList getOrder(int id);

    List<OrderList> getAllOrders();

    List<BookList> getOrderBooks(int id);

    OrderList getOrderByUserId(Integer id);

    OrderList addBookToOrder(User user, BookList bookList);
}
