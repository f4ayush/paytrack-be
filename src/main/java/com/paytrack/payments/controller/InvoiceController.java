package com.paytrack.payments.controller;

import com.paytrack.payments.dto.InvoiceDTO;
import com.paytrack.payments.dto.UpdateInvoiceDTO;
import com.paytrack.payments.entity.Invoice;
import com.paytrack.payments.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    @PostMapping("/invoice")
    public ResponseEntity<Invoice> create(@RequestBody InvoiceDTO invoiceData){
        return ResponseEntity.ok(invoiceService.create(invoiceData));
    }

    @GetMapping("/invoice")
    public ResponseEntity<List<InvoiceDTO>> getAll(){
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable String id){
        return ResponseEntity.ok(invoiceService.get(id));
    }

    @PatchMapping("/invoice/{id}")
    public ResponseEntity<Invoice> updateInvoiceStatus(@PathVariable String id, @RequestBody UpdateInvoiceDTO invoiceData){
        return ResponseEntity.ok(invoiceService.updateStatus(id, invoiceData));
    }
}
