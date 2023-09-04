package com.carentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String id;
    private String username;
    private String password;
    private String email;
    private String nic;
    private String drivingLicenseNumber;
    private String address;
    private String phoneNumber;
}
