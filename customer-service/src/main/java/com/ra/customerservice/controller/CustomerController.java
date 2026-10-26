package com.ra.customerservice.controller;

import com.ra.customerservice.dto.request.CustomerRequestDTO;
import com.ra.customerservice.dto.request.LoginRequestDTO;
import com.ra.customerservice.dto.response.CustomerResponseDTO;
import com.ra.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> register(@RequestBody CustomerRequestDTO request) {
        return new ResponseEntity<>(customerService.register(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/login")
    public ResponseEntity<CustomerResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(customerService.login(request));
    }
}
