package rw.gakbank.corebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rw.gakbank.corebanking.model.Customer;
import rw.gakbank.corebanking.service.ICustomerService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Customer customer) {
        customer = service.create(customer);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "{email}")
    public ResponseEntity<?> getCustomer(@PathVariable String email) {
        Customer customer = service.findByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value = "{pageNo}/{pageSize}")
    public ResponseEntity<?> getCustomers(@PathVariable int pageNo, @PathVariable int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page page = service.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


}
