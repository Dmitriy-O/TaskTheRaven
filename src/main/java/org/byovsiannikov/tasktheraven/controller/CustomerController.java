package org.byovsiannikov.tasktheraven.controller;

import jakarta.validation.Valid;
import org.byovsiannikov.tasktheraven.entity.CustomerEntity;
import org.byovsiannikov.tasktheraven.model.Customer;
import org.byovsiannikov.tasktheraven.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CustomerController {

    private final Service service;

    public CustomerController(Service service) {
        this.service = service;
    }

    private String phonePattern = "^\\+\\d{6,14}";
    private Pattern pattern = Pattern.compile(phonePattern);

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerEntity customerEntity) {

        boolean matches = pattern.matcher(customerEntity.getPhone()).matches();
        if (matches) {
            return ResponseEntity.ok(service.createCustomer(customerEntity));
        } else return ResponseEntity.badRequest().body("Phone pattern doesn't match");
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
        if (pattern.matcher(customer.getPhone()).matches()) {
            return ResponseEntity.ok(service.updateCustomerByID(id, customer.getFullName(), customer.getPhone()));
        } else
            return ResponseEntity.badRequest().body("Phone pattern doesn't match");
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomerByID(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.deleteCustomerByID(id));
    }
}
