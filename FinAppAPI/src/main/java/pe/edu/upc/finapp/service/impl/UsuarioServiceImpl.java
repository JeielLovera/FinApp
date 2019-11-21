package pe.edu.upc.finapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finapp.model.entity.Usuario;
import pe.edu.upc.finapp.model.repository.UsuarioRepository;
import pe.edu.upc.finapp.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() throws Exception {
		return usuarioRepo.findAll();
	}

	@Transactional
	@Override
	public Usuario save(Usuario t) throws Exception {
		return usuarioRepo.save(t);
	}

	@Transactional
	@Override
	public Usuario update(Usuario t) throws Exception {
		return usuarioRepo.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Usuario> findById(Integer id) throws Exception {
		return usuarioRepo.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		usuarioRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		usuarioRepo.deleteAll();
	}

	
}
