package pe.edu.cibertec.WAVeterinaria.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.WAVeterinaria.model.bd.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "select * from cliente where nombrecli like :var_filtro or apellidoscli like :var_filtro",
            nativeQuery = true)
    List<Cliente> listaCliente (@Param("var_filtro") String filtro, Pageable pageable);
}
