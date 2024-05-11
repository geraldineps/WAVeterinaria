package pe.edu.cibertec.WAVeterinaria.model.bd;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoproducto;

    @Column(name = "nombreproducto")
    private String nombreproducto;

    @ManyToOne
    @JoinColumn(name = "codigocategoria")
    private Categoria categoria;

    @Column(name = "marca")
    private String marca;

    @ManyToOne
    @JoinColumn(name = "codigoespecie")
    private Especie especie;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "stock")
    private int stock;




}
