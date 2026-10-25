package com.lente.inmobiliario.repository;

import com.lente.inmobiliario.model.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {

    // Método para buscar todas las fotografías asociadas a un inmueble por su ID
    List<Fotografia> findByInmueble_IdInmueble(Long idInmueble);
}
