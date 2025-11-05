package com.paytrack.payments.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class InvoiceDTO {
    private String id;
    @NotNull
    private String clientId, userId;
    private String status;
    private float total;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private List<InvoiceItems> invoiceItems;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class InvoiceItems{
        private String description;
        private float price, tax;
        private int quantity;
    }
}
