package org.byovsiannikov.tasktheraven.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.byovsiannikov.tasktheraven.entity.CustomerEntity;
import org.byovsiannikov.tasktheraven.model.Customer;
import org.byovsiannikov.tasktheraven.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    @Override
    public Customer createCustomer(CustomerEntity customer) {

        long epochSecond = Instant.now().getEpochSecond();
        CustomerEntity customerEntity = CustomerEntity.builder()
                .fullName(customer.getFullName())
                .created(BigInteger.valueOf(epochSecond))
                .updated(BigInteger.valueOf(epochSecond))
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .isActive(true)
                .build();

        CustomerEntity saved = repository.save(customerEntity);

        return Customer.builder()
                .id(saved.getId())
                .fullName(saved.getFullName())
                .email(saved.getEmail())
                .phone(saved.getPhone()).build();
    }

    @Override
    public List<Customer> readAllCustomers() {
        List<CustomerEntity> customerEntityList = repository.findAll();
        return customerEntityList.stream()
                .map(customer -> Customer.builder()
                        .id(customer.getId())
                        .fullName(customer.getFullName())
                        .email(customer.getEmail())
                        .phone(customer.getPhone())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Customer readCustomerByID(Long id) {
        CustomerEntity customerEntity = repository.findById(id).orElseThrow(()->new EntityNotFoundException("Customer not found"));
        return Customer.builder()
                .id(customerEntity.getId())
                .fullName(customerEntity.getFullName())
                .phone(customerEntity.getPhone())
                .email(customerEntity.getEmail())
                .build();
    }

    @Override
    public Customer updateCustomerByID(Long id, String fullName, String phone) {

        CustomerEntity customerEntity = repository.findById(id).orElseThrow(()->new EntityNotFoundException("Customer not found"));

        CustomerEntity customerForUpdate = CustomerEntity.builder()
                .id(id)
                .fullName(fullName)
                .created(customerEntity.getCreated())
                .updated(BigInteger.valueOf(Instant.now().getEpochSecond()))
                .email(customerEntity.getEmail())
                .phone(phone).build();
        repository.save(customerForUpdate);


        return Customer.builder()
                .id(customerForUpdate.getId())
                .fullName(customerForUpdate.getFullName())
                .email(customerForUpdate.getEmail())
                .phone(customerForUpdate.getPhone()).build();
    }

    @Override
    public String deleteCustomerByID(Long id) {
        CustomerEntity markAsDeleted =repository.findById(id).orElseThrow(()->new EntityNotFoundException("Customer not found"));
        markAsDeleted.setActive(false);
        return "Marked as deleted";
    }

}
