package com.ecommerce.user.dto;

import com.ecommerce.user.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDto address;
}
