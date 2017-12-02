package edu.karazin.shop.dao;

import edu.karazin.shop.model.OrderList;
import org.springframework.data.repository.CrudRepository;

public interface OrderDao extends CrudRepository<OrderList, Integer> {
    OrderList getOrderListByUserId(Integer id);
}
