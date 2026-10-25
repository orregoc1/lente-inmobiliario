package com.lente.inmobiliario.controller;

import com.lente.inmobiliario.model.Fotografia;
import com.lente.inmobiliario.repository.FotografiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fotografias")
public class FotografiaController {

    @Autowired
    private FotografiaRepository fotografiaRepository;

    // 1. GET: Listar todas las fotografías registradas
    @GetMapping
    public List<Fotografia> listarFotografias() {
        return fotografiaRepository.findAll();
    }

    // 2. POST: Registrar una nueva fotografía asociada a un inmueble
    @PostMapping
    public Fotografia crearFotografia(@RequestBody Fotografia fotografia) {
        return fotografiaRepository.save(fotografia);
    }

    // 3. GET por Inmueble: Consultar las fotos de una propiedad específica
    @GetMapping("/inmueble/{idInmueble}")
    public List<Fotografia> listarPorInmueble(@PathVariable Long idInmueble) {
        return fotografiaRepository.findByInmueble_IdInmueble(idInmueble);
    }
}
