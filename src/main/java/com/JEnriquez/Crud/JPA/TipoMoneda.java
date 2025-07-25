package com.JEnriquez.Crud.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter @Setter
@Table(name = "tipomoneda")
public class TipoMoneda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmoneda")
    private int IdMoneda;
    
    @Column(name = "nombre")
    private String Nombre;
}
