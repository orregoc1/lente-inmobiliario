package com.lente.inmobiliario.repository;

import com.lente.inmobiliario.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {

    // Método personalizado para buscar todos los inmuebles que pertenecen a un usuario específico (por su ID)
    // Muy útil más adelante para el panel "Mis Inmuebles" del anunciante
    List<Inmueble> findByUsuario_IdUsuario(Long idUsuario);

}
