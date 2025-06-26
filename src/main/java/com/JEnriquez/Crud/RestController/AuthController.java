//package com.JEnriquez.Crud.RestController;
//
//import com.JEnriquez.Crud.Configuration.Jwt;
//import com.JEnriquez.Crud.JPA.Usuario;
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private Jwt jwt;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            usuario.getUsername(),
//                            usuario.getPassword()
//                    )
//            );
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String token = jwt.generateToken(userDetails);
//
//            Map<String, String> response = new HashMap<>();
//            response.put("token", token);
//            return ResponseEntity.ok(response);
//
//        } catch (AuthenticationException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("Credenciales inválidas");
//        }
//    }
//}
