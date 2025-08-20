package gt.sgd.sgdbackend.services;

import gt.sgd.sgdbackend.dto.JwtAuthenticationResponse;
import gt.sgd.sgdbackend.dto.LoginRequest;
import gt.sgd.sgdbackend.security.JwtProvider;
import gt.sgd.sgdbackend.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public JwtAuthenticationResponse login(LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
				);
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		String token = jwtProvider.generateToken(userDetails);
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		
		return new JwtAuthenticationResponse(token, userDetails.getUsername(),roles);
	}

}
