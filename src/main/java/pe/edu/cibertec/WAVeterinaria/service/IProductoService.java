package pe.edu.cibertec.WAVeterinaria.service;

import org.springframework.data.domain.Pageable;
import pe.edu.cibertec.WAVeterinaria.model.bd.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> listProductos();

    void registrarProducto(Producto producto);

    List<Producto> listaProductos (String filtro, Pageable pageable);
}
