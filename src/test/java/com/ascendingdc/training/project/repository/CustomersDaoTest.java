package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Customers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CustomersDaoTest {
    private CustomersDao customersDao;
    private Customers c1;
    private String custName = "May";
    private String updateCustName = "July";
    private String testCustName = "Allen";
    private String testDeleteName = "Alex";

    @Before
    public void init() {
        customersDao = new CustomersDaoImpl();
        c1 = new Customers();
        c1.setName(custName);
        c1 = customersDao.insert(c1);
    }

    @After
    public void tearDown() { customersDao.deleteCustomer(c1); }

    @Test
    public void updateCustomerTest() {
        c1.setName(updateCustName);
        c1 = customersDao.update(c1);
        System.out.println(customersDao.getCustomersByName(updateCustName));

        c1.setName(custName);
        c1 = customersDao.update(c1);
        System.out.println(customersDao.getCustomersByName(custName));
    }

    @Test
    public void deleteCustomerByNameTest() {
        Customers customers = new Customers();
        customers.setName(testDeleteName);

        customersDao.insert(customers);
        int beforeDelete = customersDao.getCustomers().size();

        customersDao.deleteCustomer(testDeleteName);
        Assert.assertEquals(beforeDelete, customersDao.getCustomers().size());
    }


    @Test
    public void getCustomersEagerByTest() {
        Customers customers = customersDao.getCustomersEagerBy(c1.getId());
        Assert.assertNotNull(customers);
        Assert.assertEquals(customers.getName(), c1.getName());
        Assert.assertTrue(customers.getOrders().size()>0);
    }

    @Test
    public void getCustomersTest() {
        List<Customers> customers = customersDao.getCustomers();
        int expectedNumOfCust = 14;

        customers.forEach(customer -> System.out.println(customer));
        Assert.assertEquals(expectedNumOfCust, customers.size());
    }

    @Test
    public void getCustomerByName() {
        Customers customers = customersDao.getCustomersByName(testCustName);
        System.out.println(customers);
    }



}
