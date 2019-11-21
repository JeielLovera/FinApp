package pe.edu.upc.finapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finapp.model.entity.TipoUsuario;
import pe.edu.upc.finapp.model.repository.TipoUsuarioRepository;
import pe.edu.upc.finapp.service.TipoUsuarioService;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepo; 
	
	@Transactional(readOnly = true)
	@Override
	public List<TipoUsuario> findAll() throws Exception {
		return tipoUsuarioRepo.findAll();
	}

	@Transactional
	@Override
	public TipoUsuario save(TipoUsuario t) throws Exception {
		return tipoUsuarioRepo.save(t);
	}

	@Transactional
	@Override
	public TipoUsuario update(TipoUsuario t) throws Exception {
		return tipoUsuarioRepo.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<TipoUsuario> findById(Integer id) throws Exception {
		return tipoUsuarioRepo.findById(id);
	}
	
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		tipoUsuarioRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		tipoUsuarioRepo.deleteAll();
		
	}

}
