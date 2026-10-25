package com.lente.inmobiliario.repository;

import com.lente.inmobiliario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar un usuario exactamente por su documento (Cédula o NIT) para el login o validaciones
    Optional<Usuario> findByDocumento(String documento);

    // Buscar usuarios cuyo nombre contenga el texto ingresado (ideal para el buscador interno de la inmobiliaria)
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}
