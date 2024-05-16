package com.gloibgroup.urbanswap.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String id;

    @NotBlank(message = "Phone number must be present")
    @Column(unique = true)
    private String phoneNumber;

    @NotBlank(message = "Email must be present")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "First name must be present")
    private String firstName;

    @NotBlank(message = "Last name must be present")
    private String lastName;

    @NotBlank(message = "Firebase auth UID must be present")
    @Column(name = "firebase_uid", unique = true)
    private String firebaseUID;
}
