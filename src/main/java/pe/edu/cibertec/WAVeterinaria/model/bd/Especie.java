package pe.edu.cibertec.WAVeterinaria.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "especie")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoespecie;

    @Column(name = "descripcion")
    private String descripcion;
}
