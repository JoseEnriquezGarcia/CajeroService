package com.JEnriquez.Crud.DAO;

import com.JEnriquez.Crud.JPA.Cantidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICantidadDAO extends JpaRepository<Cantidad, Integer>{
    
}
