package com.lente.inmobiliario.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inmuebles_favoritos")
public class InmuebleFavorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito")
    private Long idFavorito;

    // Relación Muchos a Uno con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Relación Muchos a Uno con Inmueble
    @ManyToOne
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @Column(name = "fecha_agregado", updatable = false, insertable = false)
    private LocalDateTime fechaAgregado;

    // Constructor vacío obligatorio por JPA
    public InmuebleFavorito() {
    }

    // Getters y Setters
    public Long getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Long idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public LocalDateTime getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(LocalDateTime fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
}
