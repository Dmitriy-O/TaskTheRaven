package org.byovsiannikov.tasktheraven.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
}
