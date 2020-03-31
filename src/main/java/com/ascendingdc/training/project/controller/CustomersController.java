package com.ascendingdc.training.project.controller;

import com.ascendingdc.training.project.model.Customers;
import com.ascendingdc.training.project.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/customers"})
public class CustomersController {
    @Autowired
    private CustomersService customersService;

    //{prefix}/customers GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Customers> getCustomers(){
        return customersService.getCustomers();
    }

    //{prefix}/customers POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<Customers> getCustomerssdfg(){
        return customersService.getCustomers();
    }

}
