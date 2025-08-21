package gt.sgd.sgdbackend.security;

import gt.sgd.sgdbackend.entities.Usuario;
import gt.sgd.sgdbackend.entities.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
	
	private final Usuario usuario;
	
	public CustomUserDetails(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return usuario.getRoles().stream()
				.filter(Rol::isActivo)
				.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
				.collect(Collectors.toList());
	}
	
	@Override
	public boolean isEnabled() {
		return usuario.isActivo();
	}
	
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes parametrizar esto si lo deseas
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
