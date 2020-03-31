package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Orders;

import java.util.List;

public interface OrdersDao {
    Orders insert(Orders orders);
    Orders update(Orders orders);
    boolean deleteOrder(long custId, long airId);
    boolean deleteOrder(Orders orders);
    List<Orders> getOrders();
    Orders getOrdersById(long custId, long airId);
}
