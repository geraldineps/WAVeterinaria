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
import pe.edu.cibertec.WAVeterinaria.model.bd.pk.DetalleBoletaServicioId;
import pe.edu.cibertec.WAVeterinaria.model.dto.response.Mensaje;
import pe.edu.cibertec.WAVeterinaria.service.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/boletaservicio")
public class BoletaServicioController {

    //Boleta
    @GetMapping("/verBoleta")
    public String verBoleta() {
        return "backoffice/venta/boletaServicio";
    }

    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private IBoletaServicioService iBoletaServicioService;
    @Autowired
    private IServicioService iServicioService;

    //Los productos seleccionados
    private List<SeleccionServicio> seleccionados = new ArrayList<SeleccionServicio>();

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
    @RequestMapping("/cargaServicio")
    @ResponseBody()
    public List<Servicio> listaServicio(String filtro){
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        List<Servicio> lstSalida = iServicioService.listaServicio("%"+filtro+"%", pageable);
        return lstSalida;
    }

    //Seleccion
    @RequestMapping("/listaSeleccion")
    @ResponseBody()
    public List<SeleccionServicio> lista(){
        return seleccionados;
    }

    @RequestMapping("/agregarSeleccion")
    @ResponseBody()
    public List<SeleccionServicio> agregar(SeleccionServicio obj){
        seleccionados.add(obj);
        return seleccionados;
    }

    @RequestMapping("/eliminaSeleccion")
    @ResponseBody()
    public List<SeleccionServicio> eliminar(int codigoservicio){
        seleccionados.removeIf( x -> x.getCodigoservicio() == codigoservicio);
        return seleccionados;
    }


    @RequestMapping("/registraBoleta")
    @ResponseBody()
    public Mensaje boleta(Cliente cliente, Usuario usuario) {

        Mensaje objMensaje = new Mensaje();

        List<DetalleBoletaServicio> detalles = new ArrayList<DetalleBoletaServicio>();
        for (SeleccionServicio seleccion : seleccionados) {

            DetalleBoletaServicioId pk = new DetalleBoletaServicioId();
            pk.setCodigoservicio(seleccion.getCodigoservicio());

            DetalleBoletaServicio psb = new DetalleBoletaServicio();
            psb.setPrecio(seleccion.getPrecio());
            psb.setDetalleBoletaServicioId(pk);

            detalles.add(psb);
        }

        BoletaServicio obj = new BoletaServicio();
        obj.setCliente(cliente);
        obj.setUsuario(usuario);
        obj.setDetallesBoletaServicio(detalles);

        BoletaServicio objBoleta = iBoletaServicioService.insertaBoletaServicio(obj);

        String salida = "-1";

        if (objBoleta != null) {
            salida = "Huellas y Más Huellas<hr/>";
            salida += "Se generó la boleta con código N° : " + objBoleta.getCodigoboletaservicio() + "<br><br>";
            salida += "Cliente: " + objBoleta.getCliente().getNombrecli() + " "+ objBoleta.getCliente().getApellidoscli() + "<br><br>";
            salida += "<table class=\"table table-bordered\"><tr><td>Servicio</td><td>Precio</td></tr>";
            double monto = 0;
            for (SeleccionServicio x : seleccionados) {
                salida += "<tr><td>" + x.getNombreservicio()
                        + "</td><td>" + x.getPrecio() + "</td></tr>";
                monto += x.getPrecio();
            }
            salida += "</table><br>";
            salida += "Monto a pagar : S/." + monto;

            seleccionados.clear();
            objMensaje.setTexto(salida);
        }
        return objMensaje;
    }









}
