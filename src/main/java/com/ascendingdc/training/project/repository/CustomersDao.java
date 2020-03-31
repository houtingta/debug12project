package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Customers;

import java.util.List;

public interface CustomersDao {
    Customers insert(Customers customers);
    Customers update(Customers customers);
    boolean deleteCustomer(String custName);
    boolean deleteCustomer(Customers customers);
    List<Customers> getCustomers();
    List<Customers> getCustomersWithOrders();
    Customers getCustomersEagerBy(Long id);
    Customers getCustomersLazyBy(Long id);
    Customers getCustomersByName(String custName);
}
