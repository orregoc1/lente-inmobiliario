package com.lente.inmobiliario.controller;

import com.lente.inmobiliario.model.InmuebleOperacion;
import com.lente.inmobiliario.repository.InmuebleOperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inmueble-operaciones")
public class InmuebleOperacionController {

    @Autowired
    private InmuebleOperacionRepository operacionRepository;

    // 1. GET: Listar todas las operaciones registradas
    @GetMapping
    public List<InmuebleOperacion> listarOperaciones() {
        return operacionRepository.findAll();
    }

    // 2. POST: Registrar una operación comercial para un inmueble
    @PostMapping
    public InmuebleOperacion crearOperacion(@RequestBody InmuebleOperacion operacion) {
        return operacionRepository.save(operacion);
    }

    // 3. GET por Inmueble: Consultar las modalidades de una propiedad específica
    @GetMapping("/inmueble/{idInmueble}")
    public List<InmuebleOperacion> listarPorInmueble(@PathVariable Long idInmueble) {
        return operacionRepository.findByInmueble_IdInmueble(idInmueble);
    }
}