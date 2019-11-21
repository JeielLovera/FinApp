package pe.edu.upc.finapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finapp.model.entity.Gasto;
import pe.edu.upc.finapp.model.repository.GastoRepository;
import pe.edu.upc.finapp.service.GastoService;

@Service
public class GastoServiceImpl implements GastoService {

	@Autowired
	private GastoRepository gastoRepo;
	
	@Transactional(readOnly = true)
	@Override
	public List<Gasto> findAll() throws Exception {
		return gastoRepo.findAll();
	}

	@Transactional
	@Override
	public Gasto save(Gasto t) throws Exception {
		return gastoRepo.save(t);
	}

	@Transactional
	@Override
	public Gasto update(Gasto t) throws Exception {
		return gastoRepo.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Gasto> findById(Integer id) throws Exception {
		return gastoRepo.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		gastoRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		gastoRepo.deleteAll();
	}

}
