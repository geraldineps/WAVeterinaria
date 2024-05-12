package pe.edu.cibertec.WAVeterinaria.model.dto.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UsuarioSecurity extends User {

    private String email;
    private String nombre;
    private Integer idusuario;
    private String nombreusuario;
    private String apellido;
    public UsuarioSecurity(String username, Integer idusuario, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
