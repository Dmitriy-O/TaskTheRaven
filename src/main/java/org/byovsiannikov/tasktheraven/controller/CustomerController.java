package org.byovsiannikov.tasktheraven.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.byovsiannikov.tasktheraven.entity.CustomerEntity;
import org.byovsiannikov.tasktheraven.model.Customer;
import org.byovsiannikov.tasktheraven.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CustomerController {

    private final Service service;

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerEntity customerEntity) {

            return ResponseEntity.ok(service.createCustomer(customerEntity));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(service.readAllCustomers());
    }


    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable(name = "id") Long findId) {
        return ResponseEntity.ok(service.readCustomerByID(findId));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomerByID(@Valid @PathVariable(name = "id") Long id, @RequestBody
    CustomerEntity customer) {
            return ResponseEntity.ok(service.updateCustomerByID(id, customer.getFullName(), customer.getPhone()));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomerByID(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.deleteCustomerByID(id));
    }
}
