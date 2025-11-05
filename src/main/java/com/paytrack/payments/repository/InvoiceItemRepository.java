package com.paytrack.payments.repository;

import com.paytrack.payments.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, String> {
    List<InvoiceItem> findAllByInvoiceId(String invoiceId);
}
