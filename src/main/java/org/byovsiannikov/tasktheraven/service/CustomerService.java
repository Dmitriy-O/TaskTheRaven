package org.byovsiannikov.tasktheraven.service;

import org.byovsiannikov.tasktheraven.entity.CustomerEntity;
import org.byovsiannikov.tasktheraven.model.Customer;

import java.util.List;

public interface CustomerService {
     Customer createCustomer(CustomerEntity customer);
     List<Customer> readAllCustomers();
     Customer readCustomerByID(Long id);
     Customer updateCustomerByID(Long id,String fullName,String phone);
     String deleteCustomerByID(Long id);
}
