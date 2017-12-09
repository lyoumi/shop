package edu.karazin.shop.service;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.OrderList;
import edu.karazin.shop.model.OrderStory;
import edu.karazin.shop.model.User;
import edu.karazin.shop.repository.BookRepository;
import edu.karazin.shop.repository.OrderRepository;
import edu.karazin.shop.repository.OrderStoryRepository;
import edu.karazin.shop.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private OrderStoryRepository orderStoryRepository;

    @Autowired
    BasketService basketService;

    @Test
    public void shouldCreateOrder(){
        when(orderRepository.save((OrderList) anyObject())).thenReturn(new OrderList());

        OrderList order = basketService.createOrder(new User());

        assertNotNull(order);
    }

    @Test
    public void shouldGetOrderByUserId(){
        when(orderRepository.getOrderListByUserId(anyInt())).thenReturn(new OrderList());
        when(orderRepository.save((OrderList) anyObject())).thenReturn(new OrderList());

        OrderList order = orderRepository.getOrderListByUserId(1);

        assertNotNull(order);
    }

    @Test
    public void shouldAddBookToOrder(){
        when(orderRepository.getOrderListByUserId(anyInt())).thenReturn(new OrderList());
        when(orderRepository.save((OrderList) anyObject())).thenReturn(new OrderList());
        when(orderRepository.save((OrderList) anyObject())).thenReturn(new OrderList());

        BookList book = new BookList();
        book.setId(1L);
        book.setName("Test");
        book.setAuthors(new ArrayList<>());
        book.setGenres(new ArrayList<>());
        book.setOrderLists(new ArrayList<>());
        book.setOrdersStory(new ArrayList<>());
        book.setPages(1);
        book.setPrice(1d);
        book.setPublisher("Test");

        OrderList order = basketService.addBookToOrder(new User(), book);

        assertNotNull(order);
    }

    @Test
    public void shouldRemoveBookFromOrder(){
        when(bookRepository.findOne(anyLong())).thenReturn(new BookList());
        when(orderRepository.getOrderListByUserId(anyInt())).thenReturn(new OrderList());
        when(orderRepository.save((OrderList) anyObject())).thenReturn(new OrderList());

        OrderList order = basketService.removeBookFromOrder(new User(), 1L);

        assertNotNull(order);
    }

    @Test
    public void shouldAddOrderToStory(){
        when(usersRepository.findOne(1)).thenReturn(new User());
        when(orderStoryRepository.save((OrderStory) anyObject())).thenReturn(new OrderStory());

        OrderList order = new OrderList();
        order.setOrderId(1);
        order.setTotalPrice(1D);
        order.setOrderDetails(new ArrayList<>());
        order.setBookLists(new ArrayList<>());
        order.setUserId(1);

        OrderStory orderStory = basketService.addOrderToStory(order);

        assertNotNull(orderStory);
    }

}
