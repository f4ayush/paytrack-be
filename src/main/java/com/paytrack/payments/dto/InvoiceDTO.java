package com.paytrack.payments.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class InvoiceDTO {
    private UUID id;
    @NotNull
    private String name, email, phone, notes;
    private String userId;
}
