package pe.edu.cibertec.WAVeterinaria.controller.backoffice;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.WAVeterinaria.model.bd.Especie;
import pe.edu.cibertec.WAVeterinaria.service.IEspecieService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/especie")
public class EspecieController {

    private IEspecieService iEspecieService;

    @GetMapping("/list")
    public String listarEspecies(Model model){
        model.addAttribute("especies",
                iEspecieService.listarEspecies());
        return "backoffice/especie/formespecie";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<Especie> listEspecies(){
        return iEspecieService.listarEspecies();
    }
}
