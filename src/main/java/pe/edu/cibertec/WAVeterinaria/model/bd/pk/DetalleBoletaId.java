package pe.edu.cibertec.WAVeterinaria.model.bd.pk;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class DetalleBoletaId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigoboletap;
    private Integer codigoproducto;
}
