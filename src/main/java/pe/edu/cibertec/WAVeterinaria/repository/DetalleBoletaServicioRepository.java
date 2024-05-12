package pe.edu.cibertec.WAVeterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.WAVeterinaria.model.bd.DetalleBoletaServicio;
import pe.edu.cibertec.WAVeterinaria.model.bd.pk.DetalleBoletaServicioId;

@Repository
public interface DetalleBoletaServicioRepository extends JpaRepository<DetalleBoletaServicio,
        DetalleBoletaServicioId> {
}
