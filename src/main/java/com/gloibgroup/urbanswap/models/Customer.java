package com.gloibgroup.urbanswap.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
