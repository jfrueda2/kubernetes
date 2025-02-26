package com.espe.micro_cursos.models.entities;

import com.espe.micro_cursos.models.Usuario;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int creditos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CursoUsuario> cursoUsuarios;

    @Transient
    private List<Usuario> usuarios;

    public Curso() {
        cursoUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void addCursoUsuario(CursoUsuario cursoUsuario) {
        if (cursoUsuario == null) {
            throw new IllegalArgumentException("CursoUsuario no puede ser vacío");
        }
        cursoUsuarios.add(cursoUsuario);
    }

    public void removeCursoUsuario(CursoUsuario cursoUsuario) {
        if (cursoUsuario == null) {
            throw new IllegalArgumentException("CursoUsuario no puede ser vacío");
        }
        if (!cursoUsuarios.contains(cursoUsuario)) {
            throw new IllegalStateException("El CursoUsuario no está asociado con este curso");
        }
        cursoUsuarios.remove(cursoUsuario);
    }

    public List<Usuario> getUsuarios(List<Usuario> allUsuarios) {
        if (allUsuarios == null) {
            throw new IllegalArgumentException("La lista de usuarios no puede estar vacía");
        }

        List<Usuario> usuariosMatriculados = new ArrayList<>();
        for (CursoUsuario cursoUsuario : cursoUsuarios) {
            if (cursoUsuario == null) {
                continue;
            }
            for (Usuario usuario : allUsuarios) {
                if (usuario == null) {
                    continue;
                }
                if (Objects.equals(usuario.getId(), cursoUsuario.getUsuarioId())) {
                    usuariosMatriculados.add(usuario);
                }
            }
        }
        return usuariosMatriculados;
    }

    public List<CursoUsuario> getCursoUsuarios() {
        return cursoUsuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del curso debe ser positivo y no vacío-");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción del curso no puede estar vacía");
        }
        this.descripcion = descripcion;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        if (creditos <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser mayores que 0");
        }
        this.creditos = creditos;
    }
}
