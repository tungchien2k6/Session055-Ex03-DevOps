package com.ra.customerservice.service.impl;

import com.ra.customerservice.dto.request.CustomerRequestDTO;
import com.ra.customerservice.dto.request.LoginRequestDTO;
import com.ra.customerservice.dto.response.CustomerResponseDTO;
import com.ra.customerservice.entity.Customer;
import com.ra.customerservice.exception.BadRequestException;
import com.ra.customerservice.exception.ResourceNotFoundException;
import com.ra.customerservice.repository.CustomerRepository;
import com.ra.customerservice.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDTO register(CustomerRequestDTO request) {
        // Mã hóa mật khẩu đơn giản (Base64)
        String encodedPassword = Base64.getEncoder().encodeToString(request.getPassword().getBytes());

        Customer customer = Customer.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng với ID " + id + " không tồn tại!"));
        return mapToResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO login(LoginRequestDTO request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("email or password incorrect"));

        String encodedPassword = Base64.getEncoder().encodeToString(request.getPassword().getBytes());
        if (!customer.getPassword().equals(encodedPassword)) {
            throw new BadRequestException("email or password incorrect");
        }

        return mapToResponseDTO(customer);
    }

    private CustomerResponseDTO mapToResponseDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .build();
    }
}
