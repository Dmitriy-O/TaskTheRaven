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
    public ResponseEntity<Object> getCustomerByID(@PathVariable(name = "id") Long findId) {
        try {
            Customer readCustomerByID = customerService.readCustomerByID(findId);
            return ResponseEntity.ok(readCustomerByID);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomerByID(@Valid @PathVariable(name = "id") Long id, @RequestBody
    CustomerEntity customer) {
        try {
            Customer updateCustomerByID = customerService.updateCustomerByID(id, customer.getFullName(), customer.getPhone());
            return ResponseEntity.ok(updateCustomerByID);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomerByID(@PathVariable(name = "id") Long id) {
        try {
            String deleteCustomerByID = customerService.deleteCustomerByID(id);
            return ResponseEntity.ok(deleteCustomerByID);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }
}
