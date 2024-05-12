package pe.edu.cibertec.WAVeterinaria.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.WAVeterinaria.model.bd.Producto;
import pe.edu.cibertec.WAVeterinaria.model.bd.Servicio;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    @Query(value = "select * from servicio where nombreservicio like :var_filtro",
            nativeQuery = true)
    List<Servicio> listaServicio(@Param("var_filtro") String filtro, Pageable pageable);
}
