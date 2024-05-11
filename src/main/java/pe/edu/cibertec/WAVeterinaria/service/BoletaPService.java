package pe.edu.cibertec.WAVeterinaria.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAVeterinaria.model.bd.BoletaP;
import pe.edu.cibertec.WAVeterinaria.model.bd.DetalleBoletaP;
import pe.edu.cibertec.WAVeterinaria.repository.BoletaPRespository;
import pe.edu.cibertec.WAVeterinaria.repository.DetalleBoletaPRepository;
import pe.edu.cibertec.WAVeterinaria.repository.ProductoRepository;

@Service
public class BoletaPService implements IBoletaPService{

    @Autowired
    private BoletaPRespository boletaPRespository;
    @Autowired
    private DetalleBoletaPRepository detalleBoletaPRepository;
    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public BoletaP insertaBoleta(BoletaP obj) {
        BoletaP objCabecera = boletaPRespository.save(obj);
        for (DetalleBoletaP detalle: obj.getDetallesBoleta()) {
            detalle.getDetalleBoletaId().setCodigoboletap(objCabecera.getCodigoboletap());
            detalleBoletaPRepository.save(detalle);
            productoRepository.actualizaStock(detalle.getDetalleBoletaId().getCodigoproducto(), detalle.getCantidad());
        }
        return objCabecera;
    }
}
