package pe.edu.cibertec.WAVeterinaria.model.dto.request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private Integer idusuario;
    private String nomusuario;
    private String password;
    private String nombres;
    private String apellidos;
    private Boolean activo;
    private String email;
}
