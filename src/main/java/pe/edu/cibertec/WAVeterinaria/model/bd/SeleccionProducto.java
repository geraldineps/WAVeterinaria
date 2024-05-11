package pe.edu.cibertec.WAVeterinaria.model.bd;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeleccionProducto {


    private Integer codigoproducto;
    private String nombreproducto;
    private Double precio;
    private Integer cantidad;
    private Double totalParcial;

    public Double getTotalParcial() {
        totalParcial = precio * cantidad;
        return totalParcial;
    }




}
