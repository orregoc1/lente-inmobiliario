package com.lente.inmobiliario.repository;

import com.lente.inmobiliario.model.SolicitudContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudContactoRepository extends JpaRepository<SolicitudContacto, Long> {

    // Método para obtener los mensajes enviados a un inmueble específico
    List<SolicitudContacto> findByInmueble_IdInmueble(Long idInmueble);
}