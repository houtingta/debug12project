package com.ascendingdc.training.project.jdbc;

import com.ascendingdc.training.project.model.Customers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class CustomersJDBCDaoTest {
    public CustomersJDBCDao customersJDBCDao;

    @Before
    public void init() { customersJDBCDao = new CustomersJDBCDao(); }

    @Test
    public void insertCustomerTest() {
        List<Customers> customers = customersJDBCDao.getCustomers();
        System.out.println(customers.size());
        customersJDBCDao.insertCustomer("Catherine");
        customers = customersJDBCDao.getCustomers();
        System.out.println(customers.size());
    }

    @Test
    public void getCustomersTestAll() {
        List<Customers> customers = customersJDBCDao.getCustomers();

        //Set the expected number of records
        int expectedNumOfCust = 10;

        //print all records' address string
        for (Customers customer : customers) {
            System.out.println(customer);
        }

        //determine the number of records is correct or not
        Assert.assertEquals(expectedNumOfCust, customers.size());
    }

    @Test
    public void getCustomerTest(){
        Customers customer = customersJDBCDao.getCustomers("Ruby");
        System.out.println(customer);
    }

    @Test
    public void updateCustomerTest(){
        customersJDBCDao.updateCustomer("Mom", (long) 12);
    }

    @Test
    public void deleteCustomerTest() {
        List<Customers> customers = customersJDBCDao.getCustomers();
        System.out.println(customers.size());
        customersJDBCDao.deleteCustomer("Catherine");
        customers = customersJDBCDao.getCustomers();
        System.out.println(customers.size());
    }

}
