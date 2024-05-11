package pe.edu.cibertec.WAVeterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.WAVeterinaria.model.bd.DetalleBoletaP;
import pe.edu.cibertec.WAVeterinaria.model.bd.pk.DetalleBoletaId;

@Repository
public interface DetalleBoletaPRepository extends JpaRepository<DetalleBoletaP,
        DetalleBoletaId> {
}
