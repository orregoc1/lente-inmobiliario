package com.lente.inmobiliario.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_contacto")
public class SolicitudContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    // =========================================================================
    // CAMBIO 1: Mapeo el nombre del cliente con la columna 'nombre_cliente' de mi BD
    // =========================================================================
    @Column(name = "nombre_cliente", nullable = false, length = 150)
    private String nombreInteresado;

    // =========================================================================
    // CAMBIO 2: Mapeo el correo del cliente con la columna 'correo_cliente' de mi BD
    // =========================================================================
    @Column(name = "correo_cliente", nullable = false, length = 150)
    private String correoInteresado;

    // =========================================================================
    // CAMBIO 3: Mapeo el teléfono del cliente con la columna 'telefono_cliente' de mi BD
    // =========================================================================
    @Column(name = "telefono_cliente", length = 20)
    private String telefonoInteresado;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String mensaje;

    // =========================================================================
    // CAMBIO 4: Utilizo el hook @PrePersist para asignar la fecha actual automáticamente
    // y evitar problemas de nulos en la inserción de MySQL.
    // =========================================================================
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @PrePersist
    public void asignarFechaActual() {
        this.fechaSolicitud = LocalDateTime.now();
    }

    // Constructor vacío obligatorio por JPA
    public SolicitudContacto() {
    }

    // Getters y Setters
    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getNombreInteresado() {
        return nombreInteresado;
    }

    public void setNombreInteresado(String nombreInteresado) {
        this.nombreInteresado = nombreInteresado;
    }

    public String getCorreoInteresado() {
        return correoInteresado;
    }

    public void setCorreoInteresado(String correoInteresado) {
        this.correoInteresado = correoInteresado;
    }

    public String getTelefonoInteresado() {
        return telefonoInteresado;
    }

    public void setTelefonoInteresado(String telefonoInteresado) {
        this.telefonoInteresado = telefonoInteresado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
}