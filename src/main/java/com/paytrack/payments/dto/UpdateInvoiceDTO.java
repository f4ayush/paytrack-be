package com.paytrack.payments.dto;

import com.paytrack.payments.enums.InvoiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateInvoiceDTO {
    private InvoiceStatus status;
}
