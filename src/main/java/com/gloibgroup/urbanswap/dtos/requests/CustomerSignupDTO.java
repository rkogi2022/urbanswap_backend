package com.gloibgroup.urbanswap.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSignupDTO {
    @NotBlank(message = "Phone number must be present")
    private String phoneNumber;
    @NotBlank(message = "Email must be present")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "First name must be present")
    private String firstName;
    @NotBlank(message = "Last name must be present")
    private String lastName;
    @NotBlank(message = "Firebase auth ID must be present")
    private String firebaseAuthId;
}
