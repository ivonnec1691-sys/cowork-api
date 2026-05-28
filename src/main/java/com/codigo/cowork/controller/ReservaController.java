package com.codigo.cowork.controller;

import com.codigo.cowork.dto.ReservaRequestDTO;
import com.codigo.cowork.dto.ReservaResponseDTO;
import com.codigo.cowork.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(
            ReservaService reservaService) {

        this.reservaService = reservaService;
    }

    /*
      POST
     */

    @PostMapping
    public ResponseEntity<ReservaResponseDTO>
    crearReserva(
            @RequestBody ReservaRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        reservaService.crearReserva(dto)
                );
    }

    /*
      GET BY ID
     */

    @GetMapping("/{id}")
    public ReservaResponseDTO obtenerReserva(
            @PathVariable Long id
    ) {

        return reservaService.obtenerReservaPorId(id);
    }

    /*
      GET + FILTROS
     */

    @GetMapping
    public List<ReservaResponseDTO> listarReservas(

            @RequestParam(required = false)
            String estado,

            @RequestParam(required = false)
            LocalDate fecha,

            @RequestParam(required = false)
            Long salaId
    ) {

        return reservaService.listarReservas(
                estado,
                fecha,
                salaId
        );
    }

    /*
      GET RESERVAS POR SALA
     */

    @GetMapping("/sala/{salaId}")
    public List<ReservaResponseDTO>
    listarPorSala(
            @PathVariable Long salaId
    ) {

        return reservaService.listarPorSala(salaId);
    }

    /*
      PUT ESTADO
     */

    @PutMapping("/{id}/estado")
    public ReservaResponseDTO cambiarEstado(

            @PathVariable Long id,

            @RequestParam
            String nuevoEstado
    ) {

        return reservaService.cambiarEstado(
                id,
                nuevoEstado
        );
    }

    /*
      DELETE
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminarReserva(
            @PathVariable Long id
    ) {

        reservaService.eliminarReserva(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    /*
      MULTIPART PDF
     */

    @PostMapping(
            value = "/{id}/comprobante",
            consumes =
                    MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> subirComprobante(

            @PathVariable Long id,

            @RequestParam("archivo")
            MultipartFile archivo,

            @RequestHeader("X-Cliente-Id")
            String clienteId
    ) {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "reservaId",
                id
        );

        response.put(
                "clienteId",
                clienteId
        );

        response.put(
                "nombreArchivo",
                archivo.getOriginalFilename()
        );

        response.put(
                "tamanio",
                archivo.getSize()
        );

        return ResponseEntity.ok(response);
    }
}
