package pe.edu.upc.finapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.finapp.model.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{

}
