package com.paytrack.payments.service;

import com.paytrack.payments.dto.ClientDTO;
import com.paytrack.payments.dto.UserDTO;
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
public class UserService {
    private final UserRepository userRepository;
    public User create(UserDTO user){
        User userObj = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        userRepository.save(userObj);
        return userObj;
    }

    public User get(String id){
        return userRepository.findById(id).
        orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public User update(String id, UserDTO user){
         User foundUser = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Client not found"));
        BeanUtils.copyProperties(user, foundUser, "id");
        userRepository.save(foundUser);
        return foundUser;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public String delete(String id) {
        userRepository.deleteById(id);
        return id;
    }
}
