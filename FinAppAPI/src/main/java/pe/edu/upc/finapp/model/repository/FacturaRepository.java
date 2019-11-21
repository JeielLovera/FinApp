package pe.edu.upc.finapp.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.finapp.model.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{
	@Query("SELECT f FROM Factura f WHERE f.cusuario.cusuario=?1")
	List<Factura> fetchByUserId(Integer id);
}
