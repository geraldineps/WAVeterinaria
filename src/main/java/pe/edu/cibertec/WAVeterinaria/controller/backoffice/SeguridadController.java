package pe.edu.cibertec.WAVeterinaria.controller.backoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.edu.cibertec.WAVeterinaria.model.bd.Usuario;
import pe.edu.cibertec.WAVeterinaria.model.dto.request.PasswordRequest;
import pe.edu.cibertec.WAVeterinaria.model.dto.request.UsuarioRequest;
import pe.edu.cibertec.WAVeterinaria.model.dto.response.ResultadoResponse;
import pe.edu.cibertec.WAVeterinaria.service.IUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/seguridad")
public class SeguridadController {

    private IUsuarioService iUsuarioService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/usuario")
    public String frmUsuario(Model model){
        model.addAttribute("listaUsuarios",
                iUsuarioService.listarUsuarios());
        return "backoffice/seguridad/formusuario";
    }

    @GetMapping("/usuario/{id}")
    @ResponseBody
    public Usuario obtenerUsuarioxId(@PathVariable("id") int id){

        return iUsuarioService.obtenerUsuarioxId(id);
    }
    @PostMapping("/usuario/registrar")
    @ResponseBody
    public ResultadoResponse registrarUsuario(@RequestBody
                                              UsuarioRequest usuarioRequest){
        String mensaje = "Usuario registrado correctamente";
        boolean respuesta = true;
        try{
            Usuario usuario = new Usuario();
            usuario.setNombres(usuarioRequest.getNombres());
            usuario.setApellidos(usuarioRequest.getApellidos());
            if(usuarioRequest.getIdusuario() > 0){
                usuario.setIdusuario(usuarioRequest.getIdusuario());
                usuario.setActivo(usuarioRequest.getActivo());
                iUsuarioService.actualizarUsuario(usuario);
            }else{
                usuario.setNomusuario(usuarioRequest.getNomusuario());
                usuario.setEmail(usuarioRequest.getEmail());
                iUsuarioService.guardarUsuario(usuario);
            }
        }catch (Exception ex){
            mensaje = "Usuario no registrado, error en la BD";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje)
                .respuesta(respuesta).build();
    }

    @GetMapping("/usuario/lista")
    @ResponseBody
    public List<Usuario> listarUsuarios(){
        return iUsuarioService.listarUsuarios();
    }


    @GetMapping("/password")
    public String formPassword(){
        return "backoffice/seguridad/formpassword";
    }

    @PostMapping("/usuario/password")
    @ResponseBody
    public ResultadoResponse cambiarPassword(@RequestBody
                                             PasswordRequest passwordRequest){
        String mensaje = "Contraseña actualizada correctamente";
        boolean respuesta = true;

        try{
            Usuario usuario = iUsuarioService.obtenerPasswordxId(passwordRequest.getIdusuario());
            if(bCryptPasswordEncoder.matches(passwordRequest.getPasswordActual() , usuario.getPassword())){

                if(passwordRequest.getPasswordNuevo().equals(passwordRequest.getPasswordConfirmacion())) {
                    usuario.setPassword(bCryptPasswordEncoder.encode(passwordRequest.getPasswordNuevo()));
                    iUsuarioService.cambiarPassword(usuario);
                } else {
                    mensaje = "La contraseña nueva no coincide";
                    respuesta = false;
                }
            } else {
                mensaje = "La contraseña actual incorrecta";
                respuesta = false;
            }

        }catch (Exception ex){
            mensaje = "Contraseña no registrado, error en la BD";
            respuesta = false;
        }

        return ResultadoResponse.builder().mensaje(mensaje)
                .respuesta(respuesta).build();
    }

    @GetMapping("/perfil")
    public String showprofile(){

        return "backoffice/auth/formperfil";
    }
}
