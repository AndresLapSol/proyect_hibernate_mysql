package org.example.todoEmpresa;

import jakarta.persistence.*;
import org.example.todoDepartamento.Departamento;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "industria", nullable = false)
    private String industria;

    // Constructor vacío
    public Empresa() {}

    // Constructor con parámetros
    public Empresa(String nombre, String industria) {
        this.nombre = nombre;
        this.industria = industria;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIndustria() {
        return industria;
    }

    public void setIndustria(String industria) {
        this.industria = industria;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", industria='" + industria + '\'' +
                '}';
    }
}


