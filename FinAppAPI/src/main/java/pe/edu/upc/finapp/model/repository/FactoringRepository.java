package pe.edu.upc.finapp.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.finapp.model.entity.Factoring;

@Repository
public interface FactoringRepository extends JpaRepository<Factoring, Integer>{
	@Query("SELECT f FROM Factoring f , Factura fa WHERE f.cfactura.cfactura=fa.cfactura.cfactura AND fa.cusuario.cusuario=?1")
	List<Factoring>fetchByUserId(Integer id);
	
	@Query("SELECT f FROM Factoring f, CarteraFactura c, Factura fa WHERE f.ccarterafactura.ccarterafactura = c.ccarterafactura AND fa.cfactura=f.cfactura.cfactura AND fa.cusuario.cusuario=?1" )
	List<Factoring>fecthCarteraByUserId(Integer id);
}
