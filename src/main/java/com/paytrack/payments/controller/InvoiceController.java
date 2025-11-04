package com.paytrack.payments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    @PostMapping("/invoice")
    public ResponseEntity<Object> create(@RequestBody InvoiceDTO invoiceData){

    }
}
