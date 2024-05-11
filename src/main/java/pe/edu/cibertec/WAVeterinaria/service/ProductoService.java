package pe.edu.cibertec.WAVeterinaria.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAVeterinaria.model.bd.Producto;
import pe.edu.cibertec.WAVeterinaria.repository.ProductoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductoService implements IProductoService {

    private ProductoRepository productoRepository;
    @Override
    public List<Producto> listProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void registrarProducto(Producto producto) {
        productoRepository.save(producto);

    }

    @Override
    public List<Producto> listaProductos(String filtro, Pageable pageable) {
        return productoRepository.listaProducto(filtro,pageable);
    }
}
