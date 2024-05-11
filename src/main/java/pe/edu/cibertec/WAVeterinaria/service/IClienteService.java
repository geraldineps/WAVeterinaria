package pe.edu.cibertec.WAVeterinaria.service;

import org.springframework.data.domain.Pageable;
import pe.edu.cibertec.WAVeterinaria.model.bd.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> listarClientes();
    void registrarCliente(Cliente cliente);

    List<Cliente> listaCliente (String filtro, Pageable pageable);
}
