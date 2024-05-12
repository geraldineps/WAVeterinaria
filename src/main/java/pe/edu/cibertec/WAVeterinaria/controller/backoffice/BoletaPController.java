package pe.edu.cibertec.WAVeterinaria.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.WAVeterinaria.model.bd.*;
import pe.edu.cibertec.WAVeterinaria.model.bd.pk.DetalleBoletaId;
import pe.edu.cibertec.WAVeterinaria.model.dto.response.Mensaje;
import pe.edu.cibertec.WAVeterinaria.service.IBoletaPService;
import pe.edu.cibertec.WAVeterinaria.service.IClienteService;
import pe.edu.cibertec.WAVeterinaria.service.IProductoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/boletaproducto")
public class BoletaPController {

    //Boleta
    @GetMapping("/verBoleta")
    public String verBoleta() {
        return "backoffice/venta/boletaProducto";
    }

    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private IBoletaPService iBoletaPService;
    @Autowired
    private IProductoService iProductoService;

    //Los productos seleccionados
    private List<SeleccionProducto> seleccionados = new ArrayList<SeleccionProducto>();

    //Listar Clientes
    @RequestMapping("/cargaCliente")
    @ResponseBody()
    public List<Cliente> listaCliente(String filtro){
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        List<Cliente> lstSalida = iClienteService.listaCliente("%"+filtro+"%", pageable);
        return lstSalida;
    }
    //Listar Productos
    @RequestMapping("/cargaProducto")
    @ResponseBody()
    public List<Producto> listaProducto(String filtro){
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        List<Producto> lstSalida = iProductoService.listaProductos("%"+filtro+"%", pageable);
        return lstSalida;
    }

    //Seleccion
    @RequestMapping("/listaSeleccion")
    @ResponseBody()
    public List<SeleccionProducto> lista(){
        
        return seleccionados;
    }

    @RequestMapping("/agregarSeleccion")
    @ResponseBody()
    public List<SeleccionProducto> agregar(SeleccionProducto obj){
        seleccionados.add(obj);
        return seleccionados;
    }

    @RequestMapping("/eliminaSeleccion")
    @ResponseBody()
    public List<SeleccionProducto> eliminar(int codigoproducto){
        seleccionados.removeIf( x -> x.getCodigoproducto() == codigoproducto);
        return seleccionados;
    }

    @RequestMapping("/registraBoleta")
    @ResponseBody()
    public Mensaje boleta(Cliente cliente, Usuario usuario) {

        Mensaje objMensaje = new Mensaje();

            List<DetalleBoletaP> detalles = new ArrayList<DetalleBoletaP>();
            for (SeleccionProducto seleccion : seleccionados) {

                DetalleBoletaId pk = new DetalleBoletaId();
                pk.setCodigoproducto(seleccion.getCodigoproducto());

                DetalleBoletaP psb = new DetalleBoletaP();
                psb.setPrecio(seleccion.getPrecio());
                psb.setCantidad(seleccion.getCantidad());
                psb.setDetalleBoletaId(pk);

                detalles.add(psb);
            }

            BoletaP obj = new BoletaP();
            obj.setCliente(cliente);
            obj.setUsuario(usuario);
            obj.setDetallesBoleta(detalles);

            BoletaP objBoleta = iBoletaPService.insertaBoleta(obj);

            String salida = "-1";

            if (objBoleta != null) {
                salida = "Huellas y Más Huellas<hr/>";
                salida += "Se generó la boleta con código N° : " + objBoleta.getCodigoboletap() + "<br><br>";
                salida += "Cliente: " + objBoleta.getCliente().getNombrecli() + " "+ objBoleta.getCliente().getApellidoscli() + "<br><br>";
                salida += "<table class=\"table table-bordered\"><tr><td>Producto</td><td>Precio</td><td>Cantidad</td><td>Subtotal</td></tr>";
                double monto = 0;
                for (SeleccionProducto x : seleccionados) {
                    salida += "<tr><td>" + x.getNombreproducto()
                            + "</td><td>" + x.getPrecio()
                            + "</td><td>" + x.getCantidad()
                            + "</td><td>" + x.getTotalParcial() + "</td></tr>";
                    monto += x.getCantidad() * x.getPrecio();
                }
                salida += "</table><br>";
                salida += "Monto a pagar : S/." + monto;

                seleccionados.clear();
                objMensaje.setTexto(salida);
            }
        return objMensaje;
    }



}
