package gt.sgd.sgdbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.util.Set;

@Entity
@Table(name = "TC_ROL")
public class Rol {
	
	@Id
	@Column(name = "ID_ROL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMBRE_ROL", unique = true, nullable = false)
	private String nombre;  //EJ "ADMIN", "OPERADOR"
	
	@Column(name = "DESCRIPCION", unique = true, nullable = false)
	private String descripcion;  //EJ "ADMIN", "OPERADOR"
	
	@Column(name = "ACTIVO", unique = true, nullable = false)
	private char activo;  //EJ "ADMIN", "OPERADOR"
			
	@ManyToMany(mappedBy = "roles")
	private Set<Usuario> usuarios;

	public Long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public char getActivo() {
	    return activo;
	}

	public void setActivo(char activo) {
	    this.activo = activo;
	}
 
	public boolean isActivo() {
	    return this.activo == 'S' || this.activo == 's';
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
