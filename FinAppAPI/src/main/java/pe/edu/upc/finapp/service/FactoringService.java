package pe.edu.upc.finapp.service;

import java.util.List;

import pe.edu.upc.finapp.model.entity.CarteraFactura;
import pe.edu.upc.finapp.model.entity.Factoring;

public interface FactoringService extends CrudService<Factoring, Integer> {

	List<Factoring>fetchByUserId(Integer id) throws Exception;
	List<Factoring>fetchByCartera(Integer ccartera) throws Exception;
	List<CarteraFactura>fecthCarterasByUserId(Integer id) throws Exception;
}
