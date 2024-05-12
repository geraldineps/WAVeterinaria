package pe.edu.cibertec.WAVeterinaria.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.cibertec.WAVeterinaria.model.bd.pk.DetalleBoletaId;
import pe.edu.cibertec.WAVeterinaria.model.bd.pk.DetalleBoletaServicioId;

@Data
@Entity
@Table(name = "detalleboletaservicio")
public class DetalleBoletaServicio {

    @EmbeddedId
    private DetalleBoletaServicioId detalleBoletaServicioId;

    @Column(name = "precio")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "codigoboletaservicio", nullable = false, insertable = false, updatable = false)
    private BoletaServicio boleta;

    @ManyToOne
    @JoinColumn(name = "codigoservicio", nullable = false, insertable = false, updatable = false)
    private Servicio servicio;
}
