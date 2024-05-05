package pe.edu.cibertec.WAVeterinaria.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAVeterinaria.model.bd.Categoria;
import pe.edu.cibertec.WAVeterinaria.repository.CategoriaRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoriaService implements ICategoriaService{

    private CategoriaRepository categoriaRepository;
    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
}
