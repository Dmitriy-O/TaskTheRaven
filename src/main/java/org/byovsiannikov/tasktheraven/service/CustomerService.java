package org.byovsiannikov.tasktheraven.service;


import lombok.RequiredArgsConstructor;
import org.byovsiannikov.tasktheraven.entity.CustomerEntity;
import org.byovsiannikov.tasktheraven.model.Customer;
import org.byovsiannikov.tasktheraven.repository.CustomerRepository;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class CustomerService implements Service {

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

        repository.save(customerEntity);
        return Customer.builder()
                .id(customerEntity.getId())
                .fullName(customerEntity.getFullName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone()).build();
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
        Optional<CustomerEntity> customerEntity = repository.findById(id);
        return Customer.builder()
                .id(customerEntity.get().getId())
                .fullName(customerEntity.get().getFullName())
                .phone(customerEntity.get().getPhone())
                .email(customerEntity.get().getEmail())
                .build();
    }

    @Override
    public Customer updateCustomerByID(Long id, String fullName, String phone) {
        Optional<CustomerEntity> customerEntity = repository.findById(id);

        CustomerEntity customerForUpdate = CustomerEntity.builder()
                .id(id)
                .fullName(fullName)
                .created(customerEntity.get().getCreated())
                .updated(BigInteger.valueOf(Instant.now().getEpochSecond()))
                .email(customerEntity.get().getEmail())
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
        Optional<CustomerEntity> markAsDeleted =repository.findById(id);
        markAsDeleted.get().setActive(false);
        return "Marked as deleted";
    }

}
