package com.lente.inmobiliario.controller;

import com.lente.inmobiliario.model.Usuario;
import com.lente.inmobiliario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController indica que esta clase es un controlador web que responderá en formato JSON
@RestController
@RequestMapping("/api/usuarios") // Define la ruta base para todos los endpoints de este controlador
public class UsuarioController {

    // Inyectamos el repositorio para poder usar los métodos de la base de datos
    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. PETICIÓN GET: Consultar todos los usuarios
    // Ruta final: http://localhost:8080/api/usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // 2. PETICIÓN POST: Registrar un nuevo usuario
    // Ruta final: http://localhost:8080/api/usuarios
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // NUEVO ENDPOINT: Buscar usuario por Cédula/NIT exacto
    @GetMapping("/documento/{documento}")
    public Optional<Usuario> buscarPorDocumento(@PathVariable String documento) {
        return usuarioRepository.findByDocumento(documento);
    }

    // NUEVO ENDPOINT: Buscar usuarios por coincidencia en el nombre
    @GetMapping("/buscar")
    public List<Usuario> buscarPorNombre(@RequestParam String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
}

