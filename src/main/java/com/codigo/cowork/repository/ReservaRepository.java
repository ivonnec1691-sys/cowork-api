package com.codigo.cowork.repository;

import com.codigo.cowork.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservaRepository {

    private final List<Reserva> reservas = new ArrayList<>();

    private final AtomicLong contador = new AtomicLong(1);

    public Reserva guardar(Reserva reserva) {

        reserva.setId(contador.getAndIncrement());

        reservas.add(reserva);

        return reserva;
    }

    public Reserva buscarPorId(Long id) {

        for (Reserva reserva : reservas) {

            if (reserva.getId().equals(id)) {
                return reserva;
            }
        }

        return null;
    }

    public List<Reserva> listar() {
        return reservas;
    }

    public void eliminar(Long id) {

        Reserva reserva = buscarPorId(id);

        reservas.remove(reserva);
    }

    public List<Reserva> buscarPorSala(Long salaId) {

        List<Reserva> lista = new ArrayList<>();

        for (Reserva reserva : reservas) {

            if (reserva.getSalaId().equals(salaId)) {
                lista.add(reserva);
            }
        }

        return lista;
    }
}
