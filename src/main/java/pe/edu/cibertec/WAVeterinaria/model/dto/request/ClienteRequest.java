package pe.edu.cibertec.WAVeterinaria.model.dto.request;

import lombok.Data;

@Data
public class ClienteRequest {
    private Integer codigocliente;
    private String nombrecli;
    private String apellidoscli;
    private String correo;
    private String direccion;
    private String telefono;
    private String dni;
}
