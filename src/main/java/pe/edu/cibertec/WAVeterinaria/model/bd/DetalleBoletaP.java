package pe.edu.cibertec.WAVeterinaria.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.cibertec.WAVeterinaria.model.bd.pk.DetalleBoletaId;

@Data
@Entity
@Table(name = "detalleboletap")
public class DetalleBoletaP {

    @EmbeddedId
    private DetalleBoletaId detalleBoletaId;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "codigoboletap", nullable = false, insertable = false, updatable = false)
    private BoletaP boleta;

    @ManyToOne
    @JoinColumn(name = "codigoproducto", nullable = false, insertable = false, updatable = false)
    private Producto producto;


}
