package com.lente.inmobiliario.controller;

import com.lente.inmobiliario.model.InmuebleFavorito;
import com.lente.inmobiliario.repository.InmuebleFavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class InmuebleFavoritoController {

    @Autowired
    private InmuebleFavoritoRepository favoritoRepository;

    // 1. GET: Listar todos los favoritos guardados en la plataforma
    @GetMapping
    public List<InmuebleFavorito> listarTodos() {
        return favoritoRepository.findAll();
    }

    // 2. POST: Marcar un inmueble como favorito para un usuario
    @PostMapping
    public InmuebleFavorito agregarFavorito(@RequestBody InmuebleFavorito favorito) {
        return favoritoRepository.save(favorito);
    }

    // 3. GET por Usuario: Ver la lista de inmuebles favoritos de un usuario específico
    @GetMapping("/usuario/{idUsuario}")
    public List<InmuebleFavorito> listarPorUsuario(@PathVariable Long idUsuario) {
        return favoritoRepository.findByUsuario_IdUsuario(idUsuario);
    }

    // 4. DELETE: Eliminar un inmueble de favoritos por su ID de favorito
    @DeleteMapping("/{idFavorito}")
    public void eliminarFavorito(@PathVariable Long idFavorito) {
        favoritoRepository.deleteById(idFavorito);
    }
}
