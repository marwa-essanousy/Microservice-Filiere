package org.example.filieretp1.service;

import org.example.filieretp1.dto.RequestFiliereDto;
import org.example.filieretp1.dto.ResponseFiliereDto;

import java.util.List;

public interface FiliereService {
    public ResponseFiliereDto addFiliere(RequestFiliereDto dto);
    List<ResponseFiliereDto> getAllFilieres();
    public ResponseFiliereDto getFiliereById(int id);
    ResponseFiliereDto updateFiliere(RequestFiliereDto requestFiliereDto, int id);

    public ResponseFiliereDto deleteFiliere(int id);
}
