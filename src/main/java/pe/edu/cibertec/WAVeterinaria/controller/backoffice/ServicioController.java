package pe.edu.cibertec.WAVeterinaria.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.WAVeterinaria.model.bd.Servicio;
import pe.edu.cibertec.WAVeterinaria.model.dto.request.ServicioRequest;
import pe.edu.cibertec.WAVeterinaria.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.WAVeterinaria.service.IServicioService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/servicio")
public class ServicioController {
    private IServicioService iServicioService;
    @GetMapping("")
    public String formServicio(Model model)
    {
        model.addAttribute("listservicio", iServicioService.listarServicios());
        return "backoffice/servicio/frmservicio";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Servicio> listServicios() {return iServicioService.listarServicios();}

    @PostMapping("/register")
    @ResponseBody
    public ResultadoResponse registrarServicios(@RequestBody ServicioRequest servicioRequest)
    {
        String mensaje = "Servicio registrado correctamente";
        boolean respuesta = true;
        try {
            Servicio servicio= new Servicio();
            if(servicioRequest.getCodigoservicio() > 0) {
                servicio.setCodigoservicio(servicioRequest.getCodigoservicio());
            }
            servicio.setNombreservicio(servicioRequest.getNombreservicio());
            servicio.setPrecio(servicioRequest.getPrecio());
            iServicioService.registrarServicio(servicio);
        } catch (Exception ex) {
            mensaje = "Servicio no registrado, error en la BD.";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

}
