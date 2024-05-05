package pe.edu.cibertec.WAVeterinaria.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.WAVeterinaria.model.bd.Cliente;
import pe.edu.cibertec.WAVeterinaria.model.dto.request.ClienteRequest;
import pe.edu.cibertec.WAVeterinaria.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.WAVeterinaria.service.IClienteService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/cliente")
public class ClienteController {
    private IClienteService iClienteService;

    @GetMapping("")
    public String formCliente(Model model)
    {
        model.addAttribute("listcliente", iClienteService.listarClientes());
        return "backoffice/cliente/frmcliente";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Cliente> listClientes() {return iClienteService.listarClientes();}

    @PostMapping("/register")
    @ResponseBody
    public ResultadoResponse registrarClientes(@RequestBody ClienteRequest clienteRequest)
    {
        String mensaje = "Cliente registrado correctamente";
        boolean respuesta = true;
        try {
            Cliente cliente= new Cliente();
            if(clienteRequest.getCodigocliente() > 0) {
                cliente.setCodigocliente(clienteRequest.getCodigocliente());
            }
            cliente.setNombrecli(clienteRequest.getNombrecli());
            cliente.setApellidoscli(clienteRequest.getApellidoscli());
            cliente.setCorreo(clienteRequest.getCorreo());
            cliente.setDireccion(clienteRequest.getDireccion());
            cliente.setTelefono(clienteRequest.getTelefono());
            cliente.setDni(clienteRequest.getDni());
            iClienteService.registrarCliente(cliente);
        } catch (Exception ex){
            mensaje = "Cliente no registrado, error en la BD.";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
