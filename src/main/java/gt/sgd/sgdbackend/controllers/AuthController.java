package gt.sgd.sgdbackend.controllers;

import gt.sgd.sgdbackend.dto.JwtAuthenticationResponse;
import gt.sgd.sgdbackend.dto.LoginRequest;
import gt.sgd.sgdbackend.dto.LoginResponse;
import gt.sgd.sgdbackend.dto.RespuestaMsg;
import gt.sgd.sgdbackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
	    try {
	        JwtAuthenticationResponse jwtResponse = authService.login(request);
	        return ResponseEntity.ok(jwtResponse);
	    } catch (BadCredentialsException | UsernameNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(new RespuestaMsg("Usuario o contraseña incorrectos"));
	    }
	}

}
