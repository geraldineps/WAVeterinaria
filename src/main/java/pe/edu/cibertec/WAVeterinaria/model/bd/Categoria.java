package pe.edu.cibertec.WAVeterinaria.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigocategoria;

    @Column(name = "nombrecategoria")
    private String nombrecategoria;

}
