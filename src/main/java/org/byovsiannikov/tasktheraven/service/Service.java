package org.byovsiannikov.tasktheraven.service;

import org.byovsiannikov.tasktheraven.entity.CustomerEntity;
import org.byovsiannikov.tasktheraven.model.Customer;

import java.util.List;

public interface Service {
    public Customer createCustomer(CustomerEntity customer);
    public List<Customer> readAllCustomers();
    public Customer readCustomerByID(Long id);
    public Customer updateCustomerByID(Long id,String fullName,String phone);
    public String deleteCustomerByID(Long id);
}
