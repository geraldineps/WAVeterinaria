package pe.edu.cibertec.WAVeterinaria.service;

import pe.edu.cibertec.WAVeterinaria.model.bd.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> listarClientes();
    void registrarCliente(Cliente cliente);
}
