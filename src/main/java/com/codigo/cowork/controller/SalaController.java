package com.codigo.cowork.controller;

import com.codigo.cowork.dto.SalaRequestDTO;
import com.codigo.cowork.dto.SalaResponseDTO;
import com.codigo.cowork.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    /*
      GET ALL
     */

    @GetMapping
    public List<SalaResponseDTO> listarSalas() {

        return salaService.listarSalas();
    }

    /*
      GET BY ID
     */

    @GetMapping("/{id}")
    public SalaResponseDTO obtenerSalaPorId(
            @PathVariable Long id) {

        return salaService.obtenerSalaPorId(id);
    }

    /*
      POST
     */

    @PostMapping
    public ResponseEntity<SalaResponseDTO> crearSala(
            @RequestBody SalaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salaService.crearSala(dto));
    }

    /*
      PUT
     */

    @PutMapping("/{id}")
    public SalaResponseDTO actualizarSala(
            @PathVariable Long id,
            @RequestBody SalaRequestDTO dto) {

        return salaService.actualizarSala(id, dto);
    }

    /*
      DELETE
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSala(
            @PathVariable Long id) {

        salaService.eliminarSala(id);

        return ResponseEntity.noContent().build();
    }
}
