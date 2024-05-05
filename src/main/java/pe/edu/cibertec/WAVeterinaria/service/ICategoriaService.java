package pe.edu.cibertec.WAVeterinaria.service;

import pe.edu.cibertec.WAVeterinaria.model.bd.Categoria;
import pe.edu.cibertec.WAVeterinaria.model.bd.Especie;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> listarCategorias();
}
