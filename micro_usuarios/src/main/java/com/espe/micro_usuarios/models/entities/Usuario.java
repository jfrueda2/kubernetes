package com.espe.micro_usuarios.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nombre obligatorio.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "Apellido es obligatorio.")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres.")
    @Column(nullable = false)
    private String apellido;

    @NotNull(message = "Email es obligatorio.")
    @Email(message = "Email debe tener un formato válido.")
    @Size(max = 100, message = "Email no debe exceder los 100 caracteres.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Teléfono es obligatorio.")
    @Pattern(regexp = "\\d{10}", message = "Teléfono debe tener exactamente 10 dígitos.")
    @Column(nullable = false)
    private String telefono;

    @NotNull(message = "Fecha de nacimiento es obligatoria.")
    @Past(message = "Fecha de nacimiento debe ser una fecha pasada.")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @NotNull(message = "Fecha de creación es obligatoria.")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creado_en", nullable = false, updatable = false)
    private Date creadoEn;

    public Usuario() {
        // Constructor vacío
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }
}
