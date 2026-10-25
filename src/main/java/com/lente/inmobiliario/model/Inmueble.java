package com.lente.inmobiliario.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// Aquí indico que esta clase es una entidad de JPA y se mapea con la tabla "inmuebles" de la base de datos
@Entity
@Table(name = "inmuebles")
public class Inmueble {

    // Aquí defino la llave primaria autoincrementable de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inmueble")
    private Long idInmueble;

    // Aquí establezco una relación Many-to-One: muchos inmuebles pertenecen a un solo usuario/inmobiliaria
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Aquí defino la variable de texto para el título del anuncio con un límite de 150 caracteres
    @Column(nullable = false, length = 150)
    private String titulo;

    // AQUÍ LLAMO AL ENUM: Uso @Enumerated(EnumType.STRING) para guardar el texto exacto del enum en la BD
    // y enlazo el campo con el enum TipoInmueble que creamos en un archivo separado
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_inmueble", nullable = false)
    private TipoInmueble tipoInmueble;

    // Aquí defino el precio usando BigDecimal para asegurar precisión exacta en los valores monetarios
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    // Aquí defino la ubicación básica del inmueble (ciudad o sector)
    @Column(nullable = false, length = 150)
    private String ubicacion;

    // Aquí defino la dirección exacta (es opcional, por eso no lleva nullable = false)
    @Column(length = 200)
    private String direccion;

    // Aquí defino el área en metros cuadrados con decimales exactos
    @Column(name = "area_m2", precision = 8, scale = 2)
    private BigDecimal areaM2;

    // Aquí defino las habitaciones inicializando el valor por defecto en 0
    @Column(nullable = false)
    private Integer habitaciones = 0;

    // Aquí defino los baños inicializando el valor por defecto en 0
    @Column(nullable = false)
    private Integer banos = 0;

    // Aquí defino si tiene garaje usando un booleano (verdadero/falso) por defecto en falso
    @Column(name = "tiene_garaje")
    private Boolean tieneGaraje = false;

    // Aquí defino si tiene balcón usando un booleano por defecto en falso
    @Column(name = "tiene_balcon")
    private Boolean tieneBalcon = false;

    // Aquí defino la descripción larga del inmueble mapeada como tipo TEXT en SQL
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // AQUÍ LLAMO AL SEGUNDO ENUM: Asocio el estado del inmueble (Disponible, Vendido, etc.) usando el enum EstadoInmueble
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoInmueble estado = EstadoInmueble.DISPONIBLE;

    // Aquí defino la fecha de creación que se generará automáticamente en la base de datos
    @Column(name = "fecha_creacion", updatable = false, insertable = false)
    private LocalDateTime fechaCreacion;

    // 1. Constructor vacío obligatorio que exige Hibernate para poder instanciar la clase por debajo
    public Inmueble() {
    }

    // 2. Getters y Setters: Los métodos que uso para obtener y modificar los valores de mis variables de forma segura
    public Long getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Long idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(BigDecimal areaM2) {
        this.areaM2 = areaM2;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Integer getBanos() {
        return banos;
    }

    public void setBanos(Integer banos) {
        this.banos = banos;
    }

    public Boolean getTieneGaraje() {
        return tieneGaraje;
    }

    public void setTieneGaraje(Boolean tieneGaraje) {
        this.tieneGaraje = tieneGaraje;
    }

    public Boolean getTieneBalcon() {
        return tieneBalcon;
    }

    public void setTieneBalcon(Boolean tieneBalcon) {
        this.tieneBalcon = tieneBalcon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoInmueble getEstado() {
        return estado;
    }

    public void setEstado(EstadoInmueble estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
