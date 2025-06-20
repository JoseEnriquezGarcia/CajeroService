package com.JEnriquez.Crud.RestController;

import com.JEnriquez.Crud.DAO.ICantidadDAO;
import com.JEnriquez.Crud.DAO.ITipoMonedaDAO;
import com.JEnriquez.Crud.JPA.Cantidad;
import com.JEnriquez.Crud.JPA.Result;
import com.JEnriquez.Crud.JPA.TipoMoneda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cajero")
public class CajeroRestController {

    @Autowired
    private ICantidadDAO iCantidadDAO;
    @Autowired
    private ITipoMonedaDAO iTipoMonedaDAO;

    @GetMapping
    public ResponseEntity GetAll() {
        Result<TipoMoneda> result = new Result();
        try {
            result.objects = iTipoMonedaDAO.findAll();
        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/cantidad")
    public ResponseEntity GetAllCantidad() {
        Result<Cantidad> result = new Result<>();
        try {
            result.objects = iCantidadDAO.findAll();
        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.ok(result);
    }

}
