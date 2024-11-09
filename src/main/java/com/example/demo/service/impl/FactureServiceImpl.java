package com.example.demo.service.impl;

import com.example.demo.dto.FactureDto;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.FactureService;
import com.example.demo.service.mapper.FactureMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Service contenant les actions métiers liées aux Factures.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final FactureMapper factureMapper;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
    }

    @Override
    public List<FactureDto> findAll() {
        return factureRepository.findAll().stream().map(factureMapper::transformerEnDto).collect(toList());
    }

    @Override
    public FactureDto findById(Long id) {
        return factureRepository.findById(id).map(factureMapper::transformerEnDto).orElse(null);
    }

    @Override
    public List<FactureDto> findByClientId(Long clientId) {
        return factureRepository.findByClientId(clientId).stream()
                .map(factureMapper::transformerEnDto)
                .collect(toList());
    }
}
