package pe.edu.cibertec.WAVeterinaria.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoservicio;

    @Column(name = "nombreservicio")
    private String  nombreservicio;

    @Column(name = "precio")
    private Double precio;
}
