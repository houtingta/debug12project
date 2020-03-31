package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Orders;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrdersDaoTest {
    private OrdersDao ordersDao;
    private Orders o1;
    private long custId = 7;
    private long airId = 8;
    private long updateAirId = 7;
    private long testCustId = 3;
    private long testAirId = 5;
    private long testDeleteCustId = 4;
    private long testDeleteAirId = 4;

    @Before
    public void init() {
        ordersDao = new OrdersDaoImpl();
        o1 = new Orders();
        o1.setCustomerId(custId);
        o1.setAirlineId(airId);
        o1 = ordersDao.insert(o1);
    }

    @After
    public void tearDown() { ordersDao.deleteOrder(o1); }

    @Test
    public void updateOrderTest() {

        o1.setAirlineId(updateAirId);
        o1 = ordersDao.update(o1);
        System.out.println(ordersDao.getOrdersById(custId, updateAirId));

        o1.setAirlineId(airId);
        o1 = ordersDao.update(o1);
        System.out.println(ordersDao.getOrdersById(custId, airId));
    }

    @Test
    public void deleteOrderByNameTest() {
        Orders orders = new Orders();
        orders.setCustomerId(testDeleteCustId);
        orders.setAirlineId(testDeleteAirId);

        ordersDao.insert(orders);
        int beforeDelete = ordersDao.getOrders().size();

        ordersDao.deleteOrder(testDeleteCustId, testDeleteAirId);
        Assert.assertEquals(beforeDelete, ordersDao.getOrders().size());
    }

    @Test
    public void getOrdersTest() {
        List<Orders> orders = ordersDao.getOrders();
        int expectedNumOfOrd = 20;

        orders.forEach(order -> System.out.println(order));
        Assert.assertEquals(expectedNumOfOrd, orders.size());
    }

    @Test
    public void getOrderById() {
        Orders orders = ordersDao.getOrdersById(testCustId, testAirId);
        System.out.println(orders);
    }
}
