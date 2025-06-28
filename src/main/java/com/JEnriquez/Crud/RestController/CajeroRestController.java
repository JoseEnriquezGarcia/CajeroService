package com.JEnriquez.Crud.RestController;

import com.JEnriquez.Crud.DAO.ICantidadDAO;
import com.JEnriquez.Crud.DAO.ITipoMonedaDAO;
import com.JEnriquez.Crud.DAO.IUsuarioDAO;
import com.JEnriquez.Crud.JPA.Cantidad;
import com.JEnriquez.Crud.JPA.Result;
import com.JEnriquez.Crud.JPA.TipoMoneda;
import com.JEnriquez.Crud.JPA.Usuario;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cajero")
public class CajeroRestController {

    @Autowired
    private ICantidadDAO iCantidadDAO;
    @Autowired
    private ITipoMonedaDAO iTipoMonedaDAO;
    @Autowired
    private IUsuarioDAO iUsuarioDAO;

    @GetMapping
    public ResponseEntity GetAllMonedas() {
        Result<TipoMoneda> result = new Result();
        try {
            result.objects = iTipoMonedaDAO.findAll();
        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
        }

        if (result.correct = true) {
            if (result.objects.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(result);
            }
        } else {
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }

    }

    @GetMapping("/cantidad")
    public ResponseEntity GetAllCantidad() {
        Result<Cantidad> result = new Result<>();
        try {
            result.objects = iCantidadDAO.findAll();
        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
        }

        if (result.correct = true) {
            if (result.objects.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(result);
            }
        } else {
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }

    @GetMapping("/usuario/{Username}")
    public ResponseEntity UsuarioByUsername(@PathVariable String Username) {
        Result result = new Result();
        try {
            result.object = iUsuarioDAO.findByUsername(Username);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        if (result.correct = true) {
            if (result.object == null) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(result);
            }
        } else {
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }

    }

    @GetMapping("/cantidadTotal")
    public ResponseEntity MostrarCantidad() {
        Result result = new Result();
        try {
            result.object = iCantidadDAO.cantidadTotal();
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        if (result.correct = true) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }

    @GetMapping("/llenarCajero")
    public ResponseEntity LlenarCajero() {
        Result result = new Result();
        try {
            iCantidadDAO.llenarCajero();
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        if (result.correct = true) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }

    }
    
    @PostMapping("/deposito/{Cantidad}/{Username}")
    public ResponseEntity depositar(@PathVariable double Cantidad, @PathVariable String Username){
        Result result = new Result();
        try {
            Usuario usuario = iUsuarioDAO.findByUsername(Username).orElseThrow();
            double saldo = usuario.getSaldo() + Cantidad;
            usuario.setSaldo(saldo);
            iUsuarioDAO.save(usuario);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        if(result.correct = true){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/retirar/{monto}/{Username}")
    public ResponseEntity retirarEfectivo(@PathVariable Double monto, @PathVariable String Username) {
        Result<Map<Double, Integer>> result = new Result<>();

        try {
            Usuario usuario = iUsuarioDAO.findByUsername(Username).orElseThrow();
            if (monto <= usuario.getSaldo()) {
                double saldo = usuario.getSaldo() - monto;
                usuario.setSaldo(saldo);
                iUsuarioDAO.save(usuario);

                if (monto <= 0) {
                    result.correct = false;
                    return ResponseEntity.badRequest().body(result);
                }

                if (monto > 12550) {
                    result.correct = false;
                    return ResponseEntity.badRequest().body(result);
                }

                if (monto < iCantidadDAO.cantidadTotal()) {

                    List<Cantidad> lista = iCantidadDAO.findAll();

                    lista.sort((a, b) -> Double.compare(b.getDenominacion(), a.getDenominacion()));

                    double restante = monto;

                    Map<Double, Integer> resultado = new HashMap<>();

                    for (Cantidad cantidadEfectivo : lista) {
                        double denominacion = cantidadEfectivo.getDenominacion();
                        int disponibles = cantidadEfectivo.getCantidadDinero();

                        int cantidadUsar = (int) (restante / denominacion);

                        if (cantidadUsar > 0 && disponibles > 0) {
                            int entregar = Math.min(cantidadUsar, disponibles);

                            restante -= denominacion * entregar;

                            resultado.put(denominacion, entregar);

                            cantidadEfectivo.setCantidadDinero(disponibles - entregar);
                            iCantidadDAO.save(cantidadEfectivo);
                        }

                        if (restante == 0.0) {
                            break;
                        }
                    }

                    result.correct = true;
                    result.objects = List.of(resultado);
                    return ResponseEntity.ok(result);
                } else {
                    result.correct = false;
                    return ResponseEntity.badRequest().body(result.errorMessage);
                }
            } else {
                result.correct = false;
                result.errorMessage = "No tienes saldo suficiente";
                return ResponseEntity.badRequest().body(result.errorMessage);
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.internalServerError().body(result);
        }
    }
}
