package com.JEnriquez.Crud.DAO;

import com.JEnriquez.Crud.JPA.Cantidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ICantidadDAO extends JpaRepository<Cantidad, Integer> {

    @Procedure(name = "llenarCajero")
    void llenarCajero();
    
    @Query("SELECT SUM(c.CantidadDinero * c.Denominacion) FROM Cantidad c")
    Integer cantidadTotal();
}
