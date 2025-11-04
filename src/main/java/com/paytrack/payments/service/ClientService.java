package com.paytrack.payments.service;

import com.paytrack.payments.dto.ClientDTO;
import com.paytrack.payments.entity.Client;
import com.paytrack.payments.entity.User;
import com.paytrack.payments.repository.ClientRepository;
import com.paytrack.payments.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    public Client create(ClientDTO client){
        User user = userRepository.findById(client.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Client clientObj = Client.builder()
                .name(client.getName())
                .email(client.getEmail())
                .notes(client.getNotes())
                .phone(client.getPhone())
                .user(user)
                .build();
        clientRepository.save(clientObj);
        return clientObj;
    }

    public Client get(String id){
        return clientRepository.findById(id).
        orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public Client update(String id, ClientDTO client){
         Client foundClient = clientRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Client not found"));
        BeanUtils.copyProperties(client, foundClient, "id");
        clientRepository.save(foundClient);
        return foundClient;
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public String  delete(String id) {
        clientRepository.deleteById(id);
        return id;
    }
}
