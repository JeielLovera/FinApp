package pe.edu.upc.finapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finapp.model.entity.Factura;
import pe.edu.upc.finapp.model.repository.FacturaRepository;
import pe.edu.upc.finapp.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaRepository facturaRepo;
	
	@Transactional(readOnly = true)
	@Override
	public List<Factura> findAll() throws Exception {
		return facturaRepo.findAll();
	}
	
	@Transactional
	@Override
	public Factura save(Factura t) throws Exception {
		return facturaRepo.save(t);
	}

	@Transactional
	@Override
	public Factura update(Factura t) throws Exception {
		return facturaRepo.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Factura> findById(Integer id) throws Exception {
		return facturaRepo.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		facturaRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		facturaRepo.deleteAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Factura> fetchByUserId(Integer id) throws Exception {
		return facturaRepo.fetchByUserId(id);
	}

}
