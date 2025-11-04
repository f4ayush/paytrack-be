package com.paytrack.payments.controller;

import com.paytrack.payments.dto.ClientDTO;
import com.paytrack.payments.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @PostMapping("/client")
    public ResponseEntity<Object> createClient(@Valid @RequestBody ClientDTO client){
        return ResponseEntity.ok(clientService.create(client));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Object> getClient(@PathVariable String id){
        return ResponseEntity.ok(clientService.get(id));
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable String id,@Valid @RequestBody ClientDTO client){
        return ResponseEntity.ok(clientService.update(id, client));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable String id){
        return ResponseEntity.ok(clientService.delete(id));
    }

    @GetMapping("/client")
    public ResponseEntity<Object> getAllClient(){
        return ResponseEntity.ok(clientService.getAll());
    }


}
