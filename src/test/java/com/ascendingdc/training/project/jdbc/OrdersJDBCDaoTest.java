package com.ascendingdc.training.project.jdbc;

import com.ascendingdc.training.project.model.Customers;
import com.ascendingdc.training.project.model.Orders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrdersJDBCDaoTest {
    public OrdersJDBCDao ordersJDBCDao;

    @Before
    public void init() { ordersJDBCDao = new OrdersJDBCDao(); }

    @Test
    public void insertOrderTest() {
        List<Orders> orders = ordersJDBCDao.getOrders();
        System.out.println(orders.size());
        ordersJDBCDao.insertOrder(2,9);
        orders = ordersJDBCDao.getOrders();
        System.out.println(orders.size());
    }

    @Test
    public void getOrdersTest() {
        List<Orders> orders = ordersJDBCDao.getOrders();

        //Set the expected number of records
        int expectedNumOfCust = 10;

        //print all records' address string
        for (Orders order : orders) {
            System.out.println(order);
        }

        //determine the number of records is correct or not
        Assert.assertEquals(expectedNumOfCust, orders.size());
    }

    @Test
    public void getOrderTest(){
        Orders order = ordersJDBCDao.getOrder(1,1);
        System.out.println(order);
    }

    @Test
    public void updateOrderTest(){
        ordersJDBCDao.updateOrder((long) 9 , (long) 9, (long) 12);
    }



    @Test
    public void deleteOrderTest() {
        List<Orders> orders = ordersJDBCDao.getOrders();
        System.out.println(orders.size());
        ordersJDBCDao.deleteOrder(9,9);
        orders = ordersJDBCDao.getOrders();
        System.out.println(orders.size());
    }
}
