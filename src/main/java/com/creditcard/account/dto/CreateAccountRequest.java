package com.creditcard.account.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateAccountRequest {
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @NotBlank @Email private String email;
    private String phone;
    @NotNull @Past private LocalDate dateOfBirth;
    @NotNull @DecimalMin("500.00") private BigDecimal totalCreditLimit;
    @DecimalMin("0.00") @DecimalMax("35.00") private BigDecimal interestRate;
}
