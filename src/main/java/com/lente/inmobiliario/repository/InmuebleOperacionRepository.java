package com.lente.inmobiliario.repository;

import com.lente.inmobiliario.model.InmuebleOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmuebleOperacionRepository extends JpaRepository<InmuebleOperacion, Long> {

    // Método para buscar las operaciones comerciales de un inmueble específico
    List<InmuebleOperacion> findByInmueble_IdInmueble(Long idInmueble);
}