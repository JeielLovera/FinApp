package pe.edu.upc.finapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.finapp.model.entity.CarteraFactura;
import pe.edu.upc.finapp.model.repository.CarteraFacturaRepository;
import pe.edu.upc.finapp.service.CarteraFacturaService;

@Service
public class CarteraFacturaServiceImpl implements CarteraFacturaService{

	@Autowired
	private CarteraFacturaRepository carteraRepo;
	
	@Transactional(readOnly = true)
	@Override
	public List<CarteraFactura> findAll() throws Exception {
		return carteraRepo.findAll();
	}

	@Transactional
	@Override
	public CarteraFactura save(CarteraFactura t) throws Exception {
		return carteraRepo.save(t);
	}

	@Transactional
	@Override
	public CarteraFactura update(CarteraFactura t) throws Exception {
		return carteraRepo.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<CarteraFactura> findById(Integer id) throws Exception {
		return carteraRepo.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		carteraRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		carteraRepo.deleteAll();
	}

}
