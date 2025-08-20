package gt.sgd.sgdbackend.dto;

public class LoginResponse {
    private String token;
    private String nombreUsuario;
    private Long idRol;
    private String nombreRol;
    
    public LoginResponse() {}

    public LoginResponse(String token, String nombreUsuario, Long idRol, String nombreRol) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
