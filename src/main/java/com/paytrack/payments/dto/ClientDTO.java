package com.paytrack.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ClientDTO {
    private UUID id;
    private String name, email, phone, notes, user_id;
}
