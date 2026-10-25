package com.lente.inmobiliario.controller;

import com.lente.inmobiliario.model.SolicitudContacto;
import com.lente.inmobiliario.repository.SolicitudContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudContactoController {

    @Autowired
    private SolicitudContactoRepository solicitudRepository;

    // 1. GET: Listar todas las solicitudes registradas
    @GetMapping
    public List<SolicitudContacto> listarTodas() {
        return solicitudRepository.findAll();
    }

    // 2. POST: Registrar una nueva solicitud de contacto
    @PostMapping
    public SolicitudContacto crearSolicitud(@RequestBody SolicitudContacto solicitud) {
        return solicitudRepository.save(solicitud);
    }

    // 3. GET por Inmueble: Consultar los mensajes recibidos por una propiedad
    @GetMapping("/inmueble/{idInmueble}")
    public List<SolicitudContacto> listarPorInmueble(@PathVariable Long idInmueble) {
        return solicitudRepository.findByInmueble_IdInmueble(idInmueble);
    }
}
