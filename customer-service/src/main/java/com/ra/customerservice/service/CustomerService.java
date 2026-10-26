package com.ra.customerservice.service;

import com.ra.customerservice.dto.request.CustomerRequestDTO;
import com.ra.customerservice.dto.request.LoginRequestDTO;
import com.ra.customerservice.dto.response.CustomerResponseDTO;

public interface CustomerService {
    CustomerResponseDTO register(CustomerRequestDTO request);
    CustomerResponseDTO getCustomerById(Long id);
    CustomerResponseDTO login(LoginRequestDTO request);
}
