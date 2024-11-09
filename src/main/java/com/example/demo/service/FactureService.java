package com.example.demo.service;

import com.example.demo.dto.FactureDto;

import java.util.List;

public interface FactureService {
    List<FactureDto> findAll();
    FactureDto findById(Long id);
    List<FactureDto> findByClientId(Long clientId);
}
