package org.example.filieretp1.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.example.filieretp1.dto.RequestFiliereDto;
import org.example.filieretp1.dto.ResponseFiliereDto;
import org.example.filieretp1.service.FiliereServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "API de gestion des Filières",
                version = "1.0",
                description = "Cette API permet de gérer les filières"
        ),
        servers = @Server(url = "http://localhost:8080")
)
@RestController
@RequestMapping("/v1/filieres")
public class FiliereController {
    private FiliereServiceImpl filiereService;

    public FiliereController(FiliereServiceImpl filiereService) {
        this.filiereService = filiereService;
    }
    @Operation(
            summary = "Créer une nouvelle filière",
            description = "Ajoute une nouvelle filière dans la base de données",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière créée avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide")
            }
    )
    @PostMapping
    public ResponseEntity<ResponseFiliereDto> add (@RequestBody RequestFiliereDto requestFiliereDto)
    {
        ResponseFiliereDto responseFiliereDto =  filiereService.addFiliere(requestFiliereDto);
        return ResponseEntity.ok(responseFiliereDto);
    }
    @Operation(
            summary = "Récupérer toutes les filières",
            description = "Retourne la liste complète des filières",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class))),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    @GetMapping
    public ResponseEntity <List<ResponseFiliereDto>> getall(){
        List<ResponseFiliereDto> filiereDtos = filiereService.getAllFilieres();
        return ResponseEntity.ok(filiereDtos);
    }

    @Operation(
            summary = "Récupérer une filière par ID",
            description = "Retourne les détails d'une filière à partir de son identifiant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière trouvée",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class))),
                    @ApiResponse(responseCode = "404", description = "Filière introuvable")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity <ResponseFiliereDto> get(@PathVariable int id){
        ResponseFiliereDto responseFiliereDto = filiereService.getFiliereById(id);
        return ResponseEntity.ok(responseFiliereDto);
    }

    @Operation(
            summary = "Mettre à jour une filière",
            description = "Modifie les informations d'une filière existante",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière mise à jour avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class))),
                    @ApiResponse(responseCode = "404", description = "Filière introuvable")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseFiliereDto> update(@PathVariable int id, @RequestBody RequestFiliereDto dto) {
        ResponseFiliereDto response = filiereService.updateFiliere(dto, id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Supprimer une filière",
            description = "Supprime une filière existante à partir de son identifiant",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Filière supprimée avec succès"),
                    @ApiResponse(responseCode = "404", description = "Filière introuvable")
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        filiereService.deleteFiliere(id);
        return ResponseEntity.noContent().build();
    }
}
