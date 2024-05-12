package pe.edu.cibertec.WAVeterinaria.service;

import org.springframework.data.domain.Pageable;
import pe.edu.cibertec.WAVeterinaria.model.bd.Producto;
import pe.edu.cibertec.WAVeterinaria.model.bd.Servicio;

import java.util.List;

public interface IServicioService {
    List<Servicio> listarServicios();
    void registrarServicio(Servicio servicio);

    List<Servicio> listaServicio (String filtro, Pageable pageable);
}
