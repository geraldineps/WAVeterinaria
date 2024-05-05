package pe.edu.cibertec.WAVeterinaria.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigocliente;

    @Column(name = "nombrecli")
    private String nombrecli;

    @Column(name = "apellidoscli")
    private String apellidoscli;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "dni")
    private String dni;
}
