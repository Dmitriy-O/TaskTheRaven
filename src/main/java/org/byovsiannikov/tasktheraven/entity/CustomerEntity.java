package org.byovsiannikov.tasktheraven.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "customer",
        uniqueConstraints =
        @UniqueConstraint(columnNames = "email")

)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigInteger created;

    private BigInteger updated;

    @NotEmpty(message = "Value should not be empty")
    @Size(min = 2, max = 50, message = "Full name must be within the bound of 2 to 50")
    private String fullName;


    @Size(min = 2, max = 100, message = "Email must be within the bound of 2 to 100")
    @Email(message = "Email should be valid")
    private String email;


    @Column(nullable = true)
    @Pattern(regexp = "[^\\+\\d{6,14}]?", message = "Phone pattern not valid")
    private String phone;

    private boolean isActive;


}
