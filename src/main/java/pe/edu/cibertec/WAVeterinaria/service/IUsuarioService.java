package pe.edu.cibertec.WAVeterinaria.service;

import pe.edu.cibertec.WAVeterinaria.model.bd.Usuario;
import pe.edu.cibertec.WAVeterinaria.model.dto.request.PasswordRequest;
import pe.edu.cibertec.WAVeterinaria.model.dto.request.UsuarioRequest;

import java.util.List;

public interface IUsuarioService {
    Usuario findUserByNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario obtenerUsuarioxId(int id);

    void actualizarUsuario(Usuario usuario);

    void cambiarPassword(Usuario usuario);
    Usuario obtenerPasswordxId(int id);

}
