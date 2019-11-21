package pe.edu.upc.finapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.finapp.model.entity.TipoInteres;

@Repository
public interface TipoInteresRepository extends JpaRepository<TipoInteres, Integer>{

}
