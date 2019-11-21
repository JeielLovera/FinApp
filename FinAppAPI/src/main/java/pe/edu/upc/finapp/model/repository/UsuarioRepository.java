package pe.edu.upc.finapp.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.finapp.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	@Query("SELECT u FROM Usuario u WHERE u.idLogin= :usuario and u.tcontrasenya = :contraseña")
	List<Usuario> fetchByLogin(String usuario, String contraseña);
}
