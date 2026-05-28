package com.codigo.cowork.repository;

import com.codigo.cowork.model.Sala;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SalaRepository {

    private final List<Sala> salas = new ArrayList<>();

    private final AtomicLong contador = new AtomicLong(1);

    public List<Sala> listar() {
        return salas;
    }

    public Sala buscarPorId(Long id) {

        for (Sala sala : salas) {
            if (sala.getId().equals(id)) {
                return sala;
            }
        }

        return null;
    }

    public Sala guardar(Sala sala) {

        sala.setId(contador.getAndIncrement());

        salas.add(sala);

        return sala;
    }

    public Sala actualizar(Sala salaActualizar) {

        for (Sala sala : salas) {

            if (sala.getId().equals(salaActualizar.getId())) {

                sala.setCodigo(salaActualizar.getCodigo());
                sala.setNombre(salaActualizar.getNombre());
                sala.setCapacidad(salaActualizar.getCapacidad());
                sala.setUbicacion(salaActualizar.getUbicacion());
                sala.setActiva(salaActualizar.isActiva());

                return sala;
            }
        }

        return null;
    }

    public void eliminar(Long id) {

        Sala sala = buscarPorId(id);

        salas.remove(sala);
    }
}
