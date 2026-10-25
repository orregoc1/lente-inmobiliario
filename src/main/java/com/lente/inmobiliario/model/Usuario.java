package com.lente.inmobiliario.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// @Entity le indica a Spring Boot que esta clase es una entidad JPA (una tabla en la BD)
@Entity
// @Table especifica el nombre exacto de la tabla en MySQL tal como lo esta en mi script DDL
@Table(name = "usuarios")
public class Usuario {

    // @Id define este campo como la Llave Primaria (Primary Key)
    // @GeneratedValue configura el autoincremento (AUTO_INCREMENT) compatible con MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    // =========================================================================
    // NUEVO ATRIBUTO: Documento de identidad (Cédula o NIT) único por usuario
    // =========================================================================
    @Column(name = "documento", nullable = false, unique = true, length = 20)
    private String documento;

    // @Column define las restricciones del campo según tu documento técnico
    @Column(nullable = false, length = 100)
    private String nombre;

    // @Enumerated(EnumType.STRING) guarda el texto exacto del ENUM en la base de datos
    // ('INMOBILIARIA', 'PERSONA_NATURAL', 'CLIENTE')
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    // unique = true asegura que no se repitan los correos en la base de datos
    @Column(nullable = false, unique = true, length = 120)
    private String correo;

    @Column(nullable = false, length = 255)
    private String contrasena;

    @Column(length = 20)
    private String telefono;

    // updatable = false evita que la fecha de registro cambie si se actualiza el perfil
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();



    // 1. Constructor vacío obligatorio por JPA para que Hibernate pueda instanciar la clase
    public Usuario() {}

    // 2. Constructor
    public Usuario(String nombre, TipoUsuario tipoUsuario, String correo, String contrasena, String telefono) {
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.fechaRegistro = LocalDateTime.now();
    }

    // 3. Getters y Setters (Necesarios para acceder y modificar los atributos de forma segura)

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
