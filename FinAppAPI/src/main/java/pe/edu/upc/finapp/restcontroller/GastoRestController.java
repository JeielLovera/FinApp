package pe.edu.upc.finapp.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.edu.upc.finapp.model.entity.Gasto;
import pe.edu.upc.finapp.service.GastoService;

@RestController
@RequestMapping("/gastos")
@Api(value = "REST para gastos")
public class GastoRestController {

	@Autowired
	private GastoService gastoServ;
	
	@ApiOperation("Fetch all gastos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Gasto>> fetchAll(){
		try {
			List<Gasto> gastos=gastoServ.findAll();
			return new ResponseEntity<List<Gasto>>(gastos,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Gasto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save gasto")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> save(@Valid @RequestBody Gasto gasto){
		try {
			Gasto tmp=gastoServ.save(gasto);
			if(tmp!=null) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Update gasto")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> update(@Valid @RequestBody Gasto gasto){
		try {
			Optional<Gasto> buscado=gastoServ.findById(gasto.getCgasto());
			if(buscado.isPresent()) {
				gastoServ.update(gasto);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Remove all gastos")
	@DeleteMapping(produces = "text/plain")
	public ResponseEntity<String> deleteAll(){
		try {
			gastoServ.deleteAll();
			return new ResponseEntity<String>("gastos eliminados", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Fetch gasto by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gasto> fetchById(@PathVariable("id") Integer id){
		try {
			Optional<Gasto> gasto=gastoServ.findById(id);
			if(gasto.isPresent()) {
				return new ResponseEntity<Gasto>(gasto.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Gasto>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Gasto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Remove gasto by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
		try {
			Optional<Gasto> gasto=gastoServ.findById(id);
			if(gasto.isPresent()) {
				gastoServ.deleteById(id);
				return new ResponseEntity<String>("gasto eliminado", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
