package gt.sgd.sgdbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import java.util.Set;

@Entity
@Table(name = "TC_USUARIO")
public class Usuario {
    
	@Id
	@Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "USERNAME",unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String password;
    
    @Column(name = "ACTIVO", nullable = false)
    private char activo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "TT_USUARIO_ROL",
        joinColumns = @JoinColumn(name = "ID_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "ID_ROL")
    )
    private Set<Rol> roles;

    // Getters y setters
    public Long getId() {
        return id;
    }
    
    public boolean isActivo() {
        return this.activo == 'S' || this.activo == 's';
    }

	public void setActivo(char activo) {
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
