package com.lente.inmobiliario.controller;

import com.lente.inmobiliario.model.Inmueble;
import com.lente.inmobiliario.repository.InmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inmuebles")
public class InmuebleController {

    @Autowired
    private InmuebleRepository inmuebleRepository;

    // 1. PETICIÓN GET: Listar todos los inmuebles del portal público
    // Ruta: http://localhost:8080/api/inmuebles
    @GetMapping
    public List<Inmueble> listarInmuebles() {
        return inmuebleRepository.findAll();
    }

    // 2. PETICIÓN POST: Registrar un nuevo inmueble
    // Ruta: http://localhost:8080/api/inmuebles
    @PostMapping
    public Inmueble crearInmueble(@RequestBody Inmueble inmueble) {
        return inmuebleRepository.save(inmueble);
    }

    // 3. PETICIÓN GET por Usuario: Listar las propiedades de un anunciante específico
    // Ruta: http://localhost:8080/api/inmuebles/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public List<Inmueble> listarPorUsuario(@PathVariable Long idUsuario) {
        return inmuebleRepository.findByUsuario_IdUsuario(idUsuario);
    }
}
