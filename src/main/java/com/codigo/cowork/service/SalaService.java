package com.codigo.cowork.service;

import com.codigo.cowork.dto.SalaRequestDTO;
import com.codigo.cowork.dto.SalaResponseDTO;
import com.codigo.cowork.mapper.SalaMapper;
import com.codigo.cowork.model.Reserva;
import com.codigo.cowork.model.Sala;
import com.codigo.cowork.repository.ReservaRepository;
import com.codigo.cowork.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    public SalaService(SalaRepository salaRepository,
                       ReservaRepository reservaRepository) {

        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public List<SalaResponseDTO> listarSalas() {

        List<SalaResponseDTO> lista = new ArrayList<>();

        for (Sala sala : salaRepository.listar()) {

            lista.add(SalaMapper.toDTO(sala));
        }

        return lista;
    }

    public SalaResponseDTO obtenerSalaPorId(Long id) {

        Sala sala = salaRepository.buscarPorId(id);

        return SalaMapper.toDTO(sala);
    }

    public SalaResponseDTO crearSala(SalaRequestDTO dto) {

        Sala sala = SalaMapper.toModel(dto);

        /*
         REGLA:
         Si activa viene null -> true
         */

        if (dto.activa() == null) {
            sala.setActiva(true);
        } else {
            sala.setActiva(dto.activa());
        }

        Sala salaGuardada = salaRepository.guardar(sala);

        return SalaMapper.toDTO(salaGuardada);
    }

    public SalaResponseDTO actualizarSala(Long id,
                                          SalaRequestDTO dto) {

        Sala sala = SalaMapper.toModel(dto);

        sala.setId(id);

        if (dto.activa() == null) {
            sala.setActiva(true);
        } else {
            sala.setActiva(dto.activa());
        }

        Sala salaActualizada =
                salaRepository.actualizar(sala);

        return SalaMapper.toDTO(salaActualizada);
    }

    /*
      ELIMINACION EN CASCADA
     */

    public void eliminarSala(Long id) {

        List<Reserva> reservas =
                reservaRepository.buscarPorSala(id);

        for (Reserva reserva : reservas) {

            reservaRepository.eliminar(reserva.getId());
        }

        salaRepository.eliminar(id);
    }
}
