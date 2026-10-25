package com.lente.inmobiliario.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fotografias")
public class Fotografia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fotografia")
    private Long idFotografia;

    // Relación Muchos a Uno: Muchas fotografías pueden pertenecer a un solo inmueble
    @ManyToOne
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;

    @Column(name = "es_principal")
    private Boolean esPrincipal = false;

    // Constructor vacío obligatorio por JPA
    public Fotografia() {
    }

    // Getters y Setters
    public Long getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(Long idFotografia) {
        this.idFotografia = idFotografia;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
}