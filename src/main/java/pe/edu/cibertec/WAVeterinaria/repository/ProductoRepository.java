package pe.edu.cibertec.WAVeterinaria.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.WAVeterinaria.model.bd.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "select * from producto where nombreproducto like :var_filtro",
            nativeQuery = true)
    List<Producto> listaProducto(@Param("var_filtro") String filtro, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update producto set stock = stock - ?2 where codigoproducto = ?1 ",
            nativeQuery = true)
    void actualizaStock(Integer codigoproducto, Integer cantidad);
}
