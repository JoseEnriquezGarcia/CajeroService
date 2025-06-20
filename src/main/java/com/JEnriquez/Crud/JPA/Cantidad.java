package com.JEnriquez.Crud.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Cantidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcantidad")
    private int IdCantidad;
    
    @JoinColumn(name = "idtipomoneda")
    @ManyToOne
    public TipoMoneda IdTipoMoneda;
    
    @Column(name = "cantidad")
    private int Cantidad;
    
    @Column(name = "denominacion")
    private String Denominacion;
}
