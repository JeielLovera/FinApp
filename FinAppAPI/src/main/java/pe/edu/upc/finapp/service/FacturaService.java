package pe.edu.upc.finapp.service;

import java.util.List;

import pe.edu.upc.finapp.model.entity.Factura;

public interface FacturaService extends CrudService<Factura, Integer>{
	List<Factura> fetchByUserId(Integer id) throws Exception;
}
