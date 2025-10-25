package org.example.filieretp1.service;

import org.example.filieretp1.dto.RequestFiliereDto;
import org.example.filieretp1.dto.ResponseFiliereDto;
import org.example.filieretp1.entities.Filiere;
import org.example.filieretp1.mappers.FiliereMapper;
import org.example.filieretp1.repository.FiliereRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FiliereServiceImpl implements FiliereService {
    private FiliereRepository filiereRepository;
    private FiliereMapper filiereMapper;
    public FiliereServiceImpl(FiliereRepository filiereRepository, FiliereMapper filiereMapper) {
        this.filiereRepository = filiereRepository;
        this.filiereMapper = filiereMapper;
    }
    @Override
    public ResponseFiliereDto addFiliere(RequestFiliereDto requestFiliereDto) {
        Filiere filiere = filiereMapper.DTO_To_Entity(requestFiliereDto);
        Filiere savedFiliere = filiereRepository.save(filiere);
        return filiereMapper.Entity_To_DTO(savedFiliere);

    }

    @Override
    public List<ResponseFiliereDto> getAllFilieres() {
        List <Filiere> filieres = filiereRepository.findAll();
        List <ResponseFiliereDto> filiereDtos = new ArrayList<>();
        for (Filiere filiere : filieres) {
            filiereDtos.add(filiereMapper.Entity_To_DTO(filiere));
        }
        return filiereDtos;
    }

    @Override
    public ResponseFiliereDto getFiliereById(int id) {
        Filiere filiere = filiereRepository.findById(id).orElseThrow();
        return filiereMapper.Entity_To_DTO(filiere);
    }

    @Override
    public ResponseFiliereDto updateFiliere(RequestFiliereDto requestFiliereDto, int id) {
        Filiere newFiliere = filiereMapper.DTO_To_Entity(requestFiliereDto);

        Filiere existingFiliere = filiereRepository.findById(id).orElseThrow();

        if (newFiliere.getCode() != null)
            existingFiliere.setCode(newFiliere.getCode());

        if (newFiliere.getLibelle() != null)
            existingFiliere.setLibelle(newFiliere.getLibelle());

        Filiere updatedFiliere = filiereRepository.save(existingFiliere);

        return filiereMapper.Entity_To_DTO(updatedFiliere);
    }
    @Override
    public ResponseFiliereDto deleteFiliere(int id) {
        filiereRepository.deleteById(id);
        return null;
    }
}
