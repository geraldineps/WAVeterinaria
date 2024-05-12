package pe.edu.cibertec.WAVeterinaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAVeterinaria.model.bd.BoletaP;
import pe.edu.cibertec.WAVeterinaria.model.bd.BoletaServicio;
import pe.edu.cibertec.WAVeterinaria.model.bd.DetalleBoletaP;
import pe.edu.cibertec.WAVeterinaria.model.bd.DetalleBoletaServicio;
import pe.edu.cibertec.WAVeterinaria.repository.BoletaServicioRepository;
import pe.edu.cibertec.WAVeterinaria.repository.DetalleBoletaServicioRepository;
import pe.edu.cibertec.WAVeterinaria.repository.ServicioRepository;

@Service
public class BoletaServicioService implements IBoletaServicioService{

    @Autowired
    private BoletaServicioRepository boletaServicioRepository;

    @Autowired
    private DetalleBoletaServicioRepository detalleBoletaServicioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public BoletaServicio insertaBoletaServicio(BoletaServicio obj) {
        BoletaServicio objCabecera = boletaServicioRepository.save(obj);
        for (DetalleBoletaServicio detalle: obj.getDetallesBoletaServicio()) {
            detalle.getDetalleBoletaServicioId().setCodigoboletaservicio(objCabecera.getCodigoboletaservicio());
            detalleBoletaServicioRepository.save(detalle);
        }
        return objCabecera;
    }
}
