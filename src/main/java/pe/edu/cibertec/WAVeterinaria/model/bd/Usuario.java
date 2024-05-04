package pe.edu.cibertec.WAVeterinaria.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;
    private String nomusuario;
    private String email;
    private String password;
    private String nombres;
    private String apellidos;
    private Boolean activo;
   @ManyToMany(cascade = CascadeType.MERGE,
           fetch = FetchType.EAGER)
   @JoinTable(name = "usuario_rol",
          joinColumns = @JoinColumn(name = "idusuario"),
           inverseJoinColumns = @JoinColumn(name = "idrol"))
   private Set<Rol> roles;
}
