package pe.edu.cibertec.WAVeterinaria.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAVeterinaria.model.bd.Especie;
import pe.edu.cibertec.WAVeterinaria.repository.EspecieRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class EspecieService implements IEspecieService{

    private EspecieRepository especieRepository;

    @Override
    public List<Especie> listarEspecies() {
        return especieRepository.findAll();
    }
}
