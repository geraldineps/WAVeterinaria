package pe.edu.cibertec.WAVeterinaria.service;

import pe.edu.cibertec.WAVeterinaria.model.bd.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> listProductos();

    void registrarProducto(Producto producto);
}
