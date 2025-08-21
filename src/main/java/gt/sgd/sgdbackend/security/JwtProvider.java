package gt.sgd.sgdbackend.security;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

@Component
public class JwtProvider {
	
	//private final String jwtSecret = "";
	@Value("${jwt.secret}")
    private String jwtSecret;
	
	@PostConstruct
	public void init() {
	    System.out.println("Clave JWT cargada: " + jwtSecret);
	}
	
	private final long jwtExpirationMs = 86400000;
	
	public String generateToken(UserDetails userDetails) {
	    return Jwts.builder()
	        .setSubject(userDetails.getUsername())
	        .claim("roles", userDetails.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.toList()))
	        .setIssuedAt(new Date())
	        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // 1 día
	        .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes()) // clave válida
	        .compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
	}
	
	public String getUserNameFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

}
