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
    public void getCustomersTest() {
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
}
