package pe.edu.cibertec.WAVeterinaria.model.dto.request;

import lombok.Data;

@Data
public class PasswordRequest {

    private String passwordActual;
    private String passwordNuevo;
    private String passwordConfirmacion;
}
