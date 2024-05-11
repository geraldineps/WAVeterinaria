package pe.edu.cibertec.WAVeterinaria.model.bd;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "boletap")
public class BoletaP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoboletap;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JoinColumn(name = "fechapago", nullable = false)
    private Date fechapago = new Date();

    @ManyToOne(optional = false)
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigocliente", nullable = false)
    private Cliente cliente;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boleta")
    private List<DetalleBoletaP> detallesBoleta;

}
