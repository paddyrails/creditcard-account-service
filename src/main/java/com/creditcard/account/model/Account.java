package com.creditcard.account.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.*;

@Entity @Table(name="accounts") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private String id;
    @Column(unique=true,nullable=false) private String accountNumber;
    private String customerId;
    @Column(nullable=false) private String firstName;
    @Column(nullable=false) private String lastName;
    @Column(unique=true,nullable=false) private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Integer creditScore;
    @Column(precision=15,scale=2) private BigDecimal totalCreditLimit;
    @Column(precision=15,scale=2) private BigDecimal availableCredit;
    @Column(precision=15,scale=2) private BigDecimal currentBalance;
    private LocalDate paymentDueDate;
    @Enumerated(EnumType.STRING) private AccountStatus status;
    @Column(precision=5,scale=2) private BigDecimal interestRate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist void onCreate(){createdAt=updatedAt=LocalDateTime.now();if(status==null)status=AccountStatus.ACTIVE;if(currentBalance==null)currentBalance=BigDecimal.ZERO;}
    @PreUpdate void onUpdate(){updatedAt=LocalDateTime.now();}
}
