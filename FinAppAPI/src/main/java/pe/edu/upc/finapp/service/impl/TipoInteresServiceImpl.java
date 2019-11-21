package pe.edu.upc.finapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finapp.model.entity.TipoInteres;
import pe.edu.upc.finapp.model.repository.TipoInteresRepository;
import pe.edu.upc.finapp.service.TipoInteresService;

@Service
public class TipoInteresServiceImpl implements TipoInteresService{

	@Autowired
	private TipoInteresRepository tipointRepo;
	
	@Transactional(readOnly = true)
	@Override
	public List<TipoInteres> findAll() throws Exception {
		return tipointRepo.findAll();
	}

	@Transactional
	@Override
	public TipoInteres save(TipoInteres t) throws Exception {
		return tipointRepo.save(t);
	}

	@Transactional
	@Override
	public TipoInteres update(TipoInteres t) throws Exception {
		return tipointRepo.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<TipoInteres> findById(Integer id) throws Exception {
		return tipointRepo.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		tipointRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		tipointRepo.deleteAll();
	}

}
