package pe.edu.cibertec.WAVeterinaria.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAVeterinaria.model.bd.Cliente;
import pe.edu.cibertec.WAVeterinaria.repository.ClienteRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService implements IClienteService{

    private ClienteRepository clienteRepository;
    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }
}
