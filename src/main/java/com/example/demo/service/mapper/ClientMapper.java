package com.example.demo.service.mapper;

import com.example.demo.dto.ClientDto;
import com.example.demo.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ClientMapper {

    public ClientDto transformerEnDto(ClientEntity client) {
        return new ClientDto(client.getId(), client.getNom(), client.getPrenom(), Period.between(client.getDateNaissance(), LocalDate.now()).getYears());
    }

}
