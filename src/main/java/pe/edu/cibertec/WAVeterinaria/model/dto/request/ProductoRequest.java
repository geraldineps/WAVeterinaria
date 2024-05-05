package pe.edu.cibertec.WAVeterinaria.model.dto.request;

import lombok.Data;

@Data
public class ProductoRequest {

    private Integer codigoproducto;

    private String nombreproducto;

    private Integer codigocategoria;

    private String marca;

    private Integer codigoespecie;

    private Double precio;
}
