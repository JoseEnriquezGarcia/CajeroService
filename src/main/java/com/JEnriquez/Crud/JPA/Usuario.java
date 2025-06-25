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

@Entity @Getter @Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int IdUsuario;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @Column(name = "username")
    private String Username;
    
    @Column(name = "password")
    private String Password;
    
    @Column(name = "saldo")
    private double Saldo;
    
    @JoinColumn(name = "idrol")
    @ManyToOne
    public Rol rol;
}
