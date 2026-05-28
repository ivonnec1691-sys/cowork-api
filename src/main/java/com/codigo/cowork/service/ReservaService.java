package com.codigo.cowork.service;

import com.codigo.cowork.dto.ReservaRequestDTO;
import com.codigo.cowork.dto.ReservaResponseDTO;
import com.codigo.cowork.mapper.ReservaMapper;
import com.codigo.cowork.model.Reserva;
import com.codigo.cowork.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /*
      CREAR RESERVA
     */

    public ReservaResponseDTO crearReserva(
            ReservaRequestDTO dto) {

        Reserva reserva = ReservaMapper.toModel(dto);

        /*
          REGLA:
          estado inicial SIEMPRE PENDIENTE
         */

        reserva.setEstado("PENDIENTE");

        Reserva reservaGuardada =
                reservaRepository.guardar(reserva);

        return ReservaMapper.toDTO(reservaGuardada);
    }

    // BUSCAR POR ID

    public ReservaResponseDTO obtenerReservaPorId(Long id) {

        Reserva reserva =
                reservaRepository.buscarPorId(id);

        return ReservaMapper.toDTO(reserva);
    }


    // LISTAR + FILTROS


    public List<ReservaResponseDTO> listarReservas(
            String estado,
            LocalDate fecha,
            Long salaId
    ) {

        List<ReservaResponseDTO> lista = new ArrayList<>();

        for (Reserva reserva : reservaRepository.listar()) {

            boolean cumple = true;

            /*
              FILTRO ESTADO
             */

            if (estado != null &&
                    !reserva.getEstado().equalsIgnoreCase(estado)) {

                cumple = false;
            }

            /*
              FILTRO FECHA
             */

            if (fecha != null &&
                    !reserva.getFecha().equals(fecha)) {

                cumple = false;
            }

            /*
              FILTRO SALA
             */

            if (salaId != null &&
                    !reserva.getSalaId().equals(salaId)) {

                cumple = false;
            }

            /*
              INTERSECCION DE FILTROS
             */

            if (cumple) {

                lista.add(
                        ReservaMapper.toDTO(reserva)
                );
            }
        }

        return lista;
    }

    /*
      RESERVAS POR SALA
     */

    public List<ReservaResponseDTO> listarPorSala(
            Long salaId) {

        List<ReservaResponseDTO> lista =
                new ArrayList<>();

        List<Reserva> reservas =
                reservaRepository.buscarPorSala(salaId);

        for (Reserva reserva : reservas) {

            lista.add(
                    ReservaMapper.toDTO(reserva)
            );
        }

        return lista;
    }

    /*
      CAMBIO DE ESTADO
     */

    public ReservaResponseDTO cambiarEstado(
            Long id,
            String nuevoEstado
    ) {

        /*
          VALIDACION
         */

        if (!nuevoEstado.equals("PENDIENTE")
                &&
                !nuevoEstado.equals("CONFIRMADA")
                &&
                !nuevoEstado.equals("CANCELADA")) {

            throw new RuntimeException(
                    "Estado invalido"
            );
        }

        Reserva reserva =
                reservaRepository.buscarPorId(id);

        reserva.setEstado(nuevoEstado);

        return ReservaMapper.toDTO(reserva);
    }

    /*
      ELIMINAR
     */

    public void eliminarReserva(Long id) {

        reservaRepository.eliminar(id);
    }
}
