package org.byovsiannikov.tasktheraven.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.byovsiannikov.tasktheraven.entity.CustomerEntity;
import org.byovsiannikov.tasktheraven.model.Customer;
import org.byovsiannikov.tasktheraven.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerEntity customerEntity) {
        Customer customer = customerService.createCustomer(customerEntity);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> readAllCustomers = customerService.readAllCustomers();
        return ResponseEntity.ok(readAllCustomers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerByID( @PathVariable(name = "id") Long findId) {
        Customer readCustomerByID = customerService.readCustomerByID(findId);
        return ResponseEntity.ok(readCustomerByID);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomerByID(@Valid @PathVariable(name = "id") Long id, @RequestBody
    CustomerEntity customer) {
        Customer updateCustomerByID = customerService.updateCustomerByID(id, customer.getFullName(), customer.getPhone());
        return ResponseEntity.ok(updateCustomerByID);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomerByID(@PathVariable(name = "id") Long id) {
        String deleteCustomerByID = customerService.deleteCustomerByID(id);
        return ResponseEntity.ok(deleteCustomerByID);
    }
}
