package pe.edu.upc.finapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finapp.model.entity.GastoFactoring;
import pe.edu.upc.finapp.model.repository.GastoFactoringRepository;
import pe.edu.upc.finapp.service.GastoFactoringService;

@Service
public class GastoFactoringServiceImpl implements GastoFactoringService{

	@Autowired
	private GastoFactoringRepository gfRepo;
	
	@Transactional(readOnly = true)
	@Override
	public List<GastoFactoring> findAll() throws Exception {
		return gfRepo.findAll();
	}

	@Transactional
	@Override
	public GastoFactoring save(GastoFactoring t) throws Exception {
		return gfRepo.save(t);
	}

	@Transactional
	@Override
	public GastoFactoring update(GastoFactoring t) throws Exception {
		return gfRepo.save(t);
	}

	@Transactional
	@Override
	public Optional<GastoFactoring> findById(Integer id) throws Exception {
		return gfRepo.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public void deleteById(Integer id) throws Exception {
		gfRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		gfRepo.deleteAll();
	}

}
