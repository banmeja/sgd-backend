package gt.sgd.sgdbackend.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException{
		String token = extractToken(request);
		
		if (token != null && jwtProvider.validateToken(token)) {
			String username = jwtProvider.getUserNameFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities()
				);
				
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
			filterChain.doFilter(request, response);
		
	}
	
		private String extractToken(HttpServletRequest request) {
			String header = request.getHeader("Authorization");
			return (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
		}
									

}
