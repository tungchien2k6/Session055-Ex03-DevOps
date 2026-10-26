package com.ra.customerservice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
    private String fullName;
    private String email;
    private String password;
}
