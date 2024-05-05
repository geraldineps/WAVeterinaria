package pe.edu.cibertec.WAVeterinaria.model.dto.request;

import lombok.Data;

@Data
public class ServicioRequest {
    private Integer codigoservicio;
    private String  nombreservicio;
    private Double precio;
}
