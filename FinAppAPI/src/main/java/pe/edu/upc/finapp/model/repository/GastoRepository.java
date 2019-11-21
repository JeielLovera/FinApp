package pe.edu.upc.finapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.finapp.model.entity.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {

}
