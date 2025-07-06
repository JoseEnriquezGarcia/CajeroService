package com.JEnriquez.Crud.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "saldo")
    private double saldo;

    @JoinColumn(name = "idrol")
    @ManyToOne
    private Rol rol;
}
