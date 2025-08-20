package gt.sgd.sgdbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private boolean activo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;

    // Getters y setters
    public Long getId() {
        return id;
    }
    
	public boolean isActivo() {
	    return activo;
	}

	public void setActivo(boolean activo) {
	    this.activo = activo;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
    /*
     @Column(unique = true, nullable = false) en username: garantiza que no se repitan usuarios y que el campo sea obligatorio.

@JoinTable(...): define explícitamente la tabla intermedia para la relación ManyToMany, lo cual es buena práctica y evita ambigüedades.

Se agregó el getter para id y para roles, que son necesarios si luego vas a mapear a DTOs o usar en UserDetails.
     
     * */
}
