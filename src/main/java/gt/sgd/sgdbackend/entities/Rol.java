package gt.sgd.sgdbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Column;

import java.util.Set;

@Entity
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String nombre;  //EJ "ADMIN", "OPERADOR"
	
	@Column(nullable = false)
	private boolean activo;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Usuario> usuarios;

	public Long getId() {
		return id;
	}
	
	public boolean isActivo() {
	    return activo;
	}

	public void setActivo(boolean activo) {
	    this.activo = activo;
	}
 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
