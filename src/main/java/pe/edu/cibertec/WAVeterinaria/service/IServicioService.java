package pe.edu.cibertec.WAVeterinaria.service;

import pe.edu.cibertec.WAVeterinaria.model.bd.Servicio;

import java.util.List;

public interface IServicioService {
    List<Servicio> listarServicios();
    void registrarServicio(Servicio servicio);
}
