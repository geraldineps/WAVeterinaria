package pe.edu.cibertec.WAVeterinaria.repository;

import pe.edu.cibertec.WAVeterinaria.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nombrerol);

}
