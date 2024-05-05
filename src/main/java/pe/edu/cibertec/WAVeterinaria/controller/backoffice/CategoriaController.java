package pe.edu.cibertec.WAVeterinaria.controller.backoffice;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.WAVeterinaria.model.bd.Categoria;
import pe.edu.cibertec.WAVeterinaria.service.ICategoriaService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private ICategoriaService iCategoriaService;

    @GetMapping("/list")
    public String listarCategorias(Model model){
        model.addAttribute("categorias",
                iCategoriaService.listarCategorias());
        return "backoffice/categoria/formcategoria";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<Categoria> listCategory(){
        return iCategoriaService.listarCategorias();
    }
}
