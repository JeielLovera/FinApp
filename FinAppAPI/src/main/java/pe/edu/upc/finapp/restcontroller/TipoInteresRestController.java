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
import pe.edu.upc.finapp.model.entity.TipoInteres;
import pe.edu.upc.finapp.service.TipoInteresService;

@RestController
@RequestMapping("/tipointereses")
@Api(value = "REST para tipo intereses")
public class TipoInteresRestController {
	
	@Autowired
	private TipoInteresService tpServ;
	
	@ApiOperation("Fetch all tipo intereses")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoInteres>> fetchAll(){
		try {
			List<TipoInteres> tps=tpServ.findAll();
			return new ResponseEntity<List<TipoInteres>>(tps,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<TipoInteres>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save tipo interes")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> save(@Valid @RequestBody TipoInteres tp){
		try {
			TipoInteres tmp=tpServ.save(tp);
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
	
	@ApiOperation("Update tipo interes")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> update(@Valid @RequestBody TipoInteres tp){
		try {
			Optional<TipoInteres> buscado=tpServ.findById(tp.getCtipointeres());
			if(buscado.isPresent()) {
				tpServ.update(tp);
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
	
	@ApiOperation("Remove all tipo intereses")
	@DeleteMapping(produces = "text/plain")
	public ResponseEntity<String> deleteAll(){
		try {
			tpServ.deleteAll();
			return new ResponseEntity<String>("intereses eliminados", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Fetch tipo interes by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoInteres> fetchById(@PathVariable("id") Integer id){
		try {
			Optional<TipoInteres> tp=tpServ.findById(id);
			if(tp.isPresent()) {
				return new ResponseEntity<TipoInteres>(tp.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<TipoInteres>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<TipoInteres>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Remove tipo interes by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
		try {
			Optional<TipoInteres> tp=tpServ.findById(id);
			if(tp.isPresent()) {
				tpServ.deleteById(id);
				return new ResponseEntity<String>("tipo interes eliminado", HttpStatus.OK);
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
