package edu.karazin.shop.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToMany(cascade = CascadeType.DETACH)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "book_order",
            joinColumns = {
                    @JoinColumn(name = "order_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id")
            })
    private List<BookList> bookLists;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "order_order_details",
            joinColumns = {
                    @JoinColumn(name = "order_id")
            }, inverseJoinColumns = {
                    @JoinColumn(name = "order_details_id")
            })
    private List<OrderDetails> orderDetails;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderid) {
        this.orderId = orderid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userid) {
        this.userId = userid;
    }

    public List<BookList> getBookLists() {
        return bookLists;
    }

    public void setBookLists(List<BookList> bookLists) {
        this.bookLists = bookLists;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderList orderList = (OrderList) o;

        if (orderId != null ? !orderId.equals(orderList.orderId) : orderList.orderId != null) return false;
        if (userId != null ? !userId.equals(orderList.userId) : orderList.userId != null) return false;
        if (totalPrice != null ? !totalPrice.equals(orderList.totalPrice) : orderList.totalPrice != null) return false;
        if (bookLists != null ? !bookLists.equals(orderList.bookLists) : orderList.bookLists != null) return false;
        return orderDetails != null ? orderDetails.equals(orderList.orderDetails) : orderList.orderDetails == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (bookLists != null ? bookLists.hashCode() : 0);
        result = 31 * result + (orderDetails != null ? orderDetails.hashCode() : 0);
        return result;
    }
}
