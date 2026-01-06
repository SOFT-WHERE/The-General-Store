package com.ecommerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

//    private String id;
    private String name;
    private String email;
    private String phone;
    private AddressDto address;
}
