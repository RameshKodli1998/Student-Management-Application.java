package org.example.student;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record StudentRequest(
        @NotBlank @Size(max=80) String firstName,
        @NotBlank @Size(max=80) String lastName,
        @NotBlank @Email @Size(max=254) String email,
        @Size(max=40) String phone,
        @Past LocalDate dateOfBirth,
        @Size(max=30) String gender,
        @Size(max=500) String address,
        @NotBlank @Size(max=120) String course,
        @NotNull @PastOrPresent LocalDate enrollmentDate,
        @NotNull StudentStatus status) {}
