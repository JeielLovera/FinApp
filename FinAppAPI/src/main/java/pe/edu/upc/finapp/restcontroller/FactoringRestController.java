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
import pe.edu.upc.finapp.model.entity.Factoring;
import pe.edu.upc.finapp.service.FactoringService;

@RestController
@RequestMapping("/factorings")
@Api(value = "REST para factorings")
public class FactoringRestController {
	
	@Autowired
	private FactoringService fctServ;
	
	@ApiOperation("Fetch all factorings")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Factoring>> fetchAll(){
		try {
			List<Factoring> factorings=fctServ.findAll();
			return new ResponseEntity<List<Factoring>>(factorings,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Factoring>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save factoring")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> save(@Valid @RequestBody Factoring factoring){
		try {
			Factoring tmp=fctServ.save(factoring);
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
	
	@ApiOperation("Update factoring")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> update(@Valid @RequestBody Factoring factoring){
		try {
			Optional<Factoring> buscado=fctServ.findById(factoring.getCfactoring());
			if(buscado.isPresent()) {
				fctServ.update(factoring);
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
	
	@ApiOperation("Remove all factorings")
	@DeleteMapping(produces = "text/plain")
	public ResponseEntity<String> deleteAll(){
		try {
			fctServ.deleteAll();
			return new ResponseEntity<String>("factorings eliminados", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Fetch factoring by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Factoring> fetchById(@PathVariable("id") Integer id){
		try {
			Optional<Factoring> factoring=fctServ.findById(id);
			if(factoring.isPresent()) {
				return new ResponseEntity<Factoring>(factoring.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Factoring>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Factoring>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Remove factoring by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
		try {
			Optional<Factoring> factoring=fctServ.findById(id);
			if(factoring.isPresent()) {
				fctServ.deleteById(id);
				return new ResponseEntity<String>("factoring eliminado", HttpStatus.OK);
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
