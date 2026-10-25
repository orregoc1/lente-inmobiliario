package com.lente.inmobiliario.repository;

import com.lente.inmobiliario.model.InmuebleFavorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmuebleFavoritoRepository extends JpaRepository<InmuebleFavorito, Long> {

    // Método para obtener la lista de favoritos guardados por un usuario
    List<InmuebleFavorito> findByUsuario_IdUsuario(Long idUsuario);
}