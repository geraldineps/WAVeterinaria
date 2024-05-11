package pe.edu.cibertec.WAVeterinaria.controller.backoffice;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.WAVeterinaria.model.bd.Categoria;
import pe.edu.cibertec.WAVeterinaria.model.bd.Especie;
import pe.edu.cibertec.WAVeterinaria.model.bd.Producto;
import pe.edu.cibertec.WAVeterinaria.model.dto.request.ProductoRequest;
import pe.edu.cibertec.WAVeterinaria.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.WAVeterinaria.service.IProductoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/producto")
public class ProductoController {

    private IProductoService iProductoService;

    @GetMapping("")
    public String formProduct(Model model){
        model.addAttribute("productos", iProductoService.listProductos());
        return "backoffice/producto/formproducto";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Producto> listProducts(){
        return iProductoService.listProductos();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultadoResponse registerProduct(@RequestBody ProductoRequest productRequest){
        String mensaje = "Producto registrado correctamente";
        boolean respuesta = true;
        try{
            Producto product = new Producto();
            if(productRequest.getCodigoproducto() > 0){
                product.setCodigoproducto(productRequest.getCodigoproducto());
            }
            product.setNombreproducto(productRequest.getNombreproducto());
            /*Traer id de categoria*/
            Categoria categoria = new Categoria();
            categoria.setCodigocategoria(productRequest.getCodigocategoria());
            product.setCategoria(categoria);

            product.setMarca(productRequest.getMarca());
            /*Traer id de especie*/
            Especie especie = new Especie();
            especie.setCodigoespecie(productRequest.getCodigoespecie());
            product.setEspecie(especie);

            product.setPrecio(productRequest.getPrecio());
            product.setStock(productRequest.getStock());

            iProductoService.registrarProducto(product);

        }catch (Exception ex){
            mensaje = "Producto no registrado, error en la BD.";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }









}
