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

import io.swagger.annotations.ApiOperation;
import pe.edu.upc.finapp.model.entity.GastoFactoring;
import pe.edu.upc.finapp.service.GastoFactoringService;

@RestController
@RequestMapping("/gasto_factorings")
public class GastoFactoringRestController {

	@Autowired
	private GastoFactoringService gfServ;
	
	@ApiOperation("Fetch all gasto_factorings")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GastoFactoring>> fetchAll(){
		try {
			List<GastoFactoring> gfs=gfServ.findAll();
			return new ResponseEntity<List<GastoFactoring>>(gfs,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<GastoFactoring>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save gasto_factorings")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> save(@Valid @RequestBody GastoFactoring gf){
		try {
			GastoFactoring tmp=gfServ.save(gf);
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
	
	@ApiOperation("Update gasto_factorings")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> update(@Valid @RequestBody GastoFactoring gf){
		try {
			Optional<GastoFactoring> buscado=gfServ.findById(gf.getCgasto_factoring());
			if(buscado.isPresent()) {
				gfServ.update(gf);
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
	
	@ApiOperation("Remove all gasto_factorings")
	@DeleteMapping(produces = "text/plain")
	public ResponseEntity<String> deleteAll(){
		try {
			gfServ.deleteAll();
			return new ResponseEntity<String>("gasto_factorings eliminados", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Fetch gasto_factoring by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GastoFactoring> fetchById(@PathVariable("id") Integer id){
		try {
			Optional<GastoFactoring> gf=gfServ.findById(id);
			if(gf.isPresent()) {
				return new ResponseEntity<GastoFactoring>(gf.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<GastoFactoring>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<GastoFactoring>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Remove gasto_factoring by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
		try {
			Optional<GastoFactoring> gf=gfServ.findById(id);
			if(gf.isPresent()) {
				gfServ.deleteById(id);
				return new ResponseEntity<String>("gasto_factoring eliminado", HttpStatus.OK);
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
