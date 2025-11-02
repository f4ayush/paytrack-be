package com.paytrack.payments.service;

import com.paytrack.payments.dto.ClientDTO;
import com.paytrack.payments.entity.Client;
import com.paytrack.payments.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    public Client create(ClientDTO client){
        Client clientObj = Client.builder()
                .name(client.getName())
                .email(client.getEmail())
                .notes(client.getNotes())
                .phone(client.getPhone())
                .build();
        clientRepository.save(clientObj);
        return clientObj;
    }
}
