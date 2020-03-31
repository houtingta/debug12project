package com.ascendingdc.training.project.service;

import com.ascendingdc.training.project.model.Customers;
import com.ascendingdc.training.project.repository.CustomersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    @Autowired
    private CustomersDao customersDao;

    public Customers save(Customers customers) {
        customers.setName(customers.getName() + customers.getLastName());
        Customers c1 = customersDao.insert(customers);
        return c1;
    }

    public Customers update(Customers customers) { return customersDao.update(customers); }
    public boolean delete(String custName) { return customersDao.deleteCustomer(custName); }

    public List<Customers> getCustomers() { return customersDao.getCustomers(); }
    public List<Customers> getCustomersWithOrders() { return customersDao.getCustomersWithOrders(); }

    public Customers getCustomersByName(String custName) { return customersDao.getCustomersByName(custName); }


}
