package com.paytrack.payments.controller;

import com.paytrack.payments.dto.ClientDTO;
import com.paytrack.payments.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @PostMapping("/client")
    public ResponseEntity<Object> createClient(@RequestBody ClientDTO client){
        return ResponseEntity.ok(clientService.create(client));
    }

}
