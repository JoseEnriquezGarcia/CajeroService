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
public class Cantidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcantidad")
    private int IdCantidad;
    
    @JoinColumn(name = "idtipomoneda")
    @ManyToOne
    public TipoMoneda IdTipoMoneda;
    
    @Column(name = "cantidaddinero")
    private int CantidadDinero;
    
    @Column(name = "denominacion")
    private Double Denominacion;
}
