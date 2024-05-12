package pe.edu.cibertec.WAVeterinaria.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAVeterinaria.model.bd.Servicio;
import pe.edu.cibertec.WAVeterinaria.repository.ServicioRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ServicioService implements IServicioService{

    private ServicioRepository servicioRepository;
    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public void registrarServicio(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    public List<Servicio> listaServicio(String filtro, Pageable pageable) {
        return servicioRepository.listaServicio(filtro,pageable);
    }
}
