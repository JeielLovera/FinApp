package pe.edu.upc.finapp.service;

import java.util.List;

import pe.edu.upc.finapp.model.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario, Integer>{
	List<Usuario> fetchByLogin(String usuario, String contraseña) throws Exception;
}
