package org.example.filieretp1.web;

import org.example.filieretp1.dto.RequestFiliereDto;
import org.example.filieretp1.dto.ResponseFiliereDto;
import org.example.filieretp1.service.FiliereServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/filieres")
public class FiliereController {
    private FiliereServiceImpl filiereService;

    public FiliereController(FiliereServiceImpl filiereService) {
        this.filiereService = filiereService;
    }
    @PostMapping
    public ResponseEntity<ResponseFiliereDto> add (@RequestBody RequestFiliereDto requestFiliereDto)
    {
        ResponseFiliereDto responseFiliereDto =  filiereService.addFiliere(requestFiliereDto);
        return ResponseEntity.ok(responseFiliereDto);
    }

    @GetMapping
    public ResponseEntity <List<ResponseFiliereDto>> getall(){
        List<ResponseFiliereDto> filiereDtos = filiereService.getAllFilieres();
        return ResponseEntity.ok(filiereDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity <ResponseFiliereDto> get(@PathVariable int id){
        ResponseFiliereDto responseFiliereDto = filiereService.getFiliereById(id);
        return ResponseEntity.ok(responseFiliereDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseFiliereDto> update(@PathVariable int id, @RequestBody RequestFiliereDto dto) {
        ResponseFiliereDto response = filiereService.updateFiliere(dto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        filiereService.deleteFiliere(id);
        return ResponseEntity.noContent().build();
    }
}
