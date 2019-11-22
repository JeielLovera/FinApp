package pe.edu.upc.finapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finapp.model.entity.Factoring;
import pe.edu.upc.finapp.model.repository.FactoringRepository;
import pe.edu.upc.finapp.service.FactoringService;

@Service
public class FactoringServiceImpl implements FactoringService{

	@Autowired
	private FactoringRepository factRepo;
	
	@Transactional(readOnly = true)
	@Override
	public List<Factoring> findAll() throws Exception {
		return factRepo.findAll();
	}

	@Transactional
	@Override
	public Factoring save(Factoring t) throws Exception {
		return factRepo.save(t);
	}

	@Transactional
	@Override
	public Factoring update(Factoring t) throws Exception {
		return factRepo.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Factoring> findById(Integer id) throws Exception {
		return factRepo.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		factRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		factRepo.deleteAll();
	}
	@Transactional(readOnly = true)
	@Override
	public List<Factoring> fetchByUserId(Integer id) throws Exception {
		return factRepo.fetchByUserId(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Factoring> fecthCarterasByUserId(Integer id) throws Exception {
		return factRepo.fecthCarteraByUserId(id);
	}

}
