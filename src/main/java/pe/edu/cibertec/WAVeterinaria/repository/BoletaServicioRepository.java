package pe.edu.cibertec.WAVeterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.WAVeterinaria.model.bd.BoletaServicio;

@Repository
public interface BoletaServicioRepository extends JpaRepository<BoletaServicio, Integer> {
}
