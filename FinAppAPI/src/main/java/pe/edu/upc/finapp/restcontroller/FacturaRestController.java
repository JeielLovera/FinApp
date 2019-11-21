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
import pe.edu.upc.finapp.model.entity.Factura;
import pe.edu.upc.finapp.service.FacturaService;

@RestController
@RequestMapping("/facturas")
@Api(value="REST para facturas")
public class FacturaRestController {

	@Autowired
	private FacturaService facturaServ;
	
	@ApiOperation("Fetch all facturas")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Factura>> fetchAll(){
		try {
			List<Factura> facturas = facturaServ.findAll();
			return new ResponseEntity<List<Factura>>(facturas,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<Factura>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save factura")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@Valid @RequestBody Factura factura){
		try {
			Factura tmp= facturaServ.save(factura);
			if(tmp!=null) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else
				return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("update de factura")
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@Valid @RequestBody Factura factura){
		try {
			Optional<Factura> buscado= facturaServ.findById(factura.getCfactura());
			if(buscado.isPresent()) {
				facturaServ.update(factura);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	@ApiOperation("Fetch factura por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Factura> fetchById(@PathVariable("id") Integer id){
		try {
			Optional<Factura> factura = facturaServ.findById(id);
			if(factura.isPresent())
				return new ResponseEntity<Factura>(factura.get(), HttpStatus.OK);
			else
				return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Factura>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Remove factura por id")
	@DeleteMapping(value= "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
		try {
			Optional<Factura> factura= facturaServ.findById(id);
			if(factura.isPresent()) {
				facturaServ.deleteById(id);
				return new ResponseEntity<String>("factura eliminada",HttpStatus.OK);
			}
			else return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Fetch factura por user id")
	@GetMapping(value="/usuario/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Factura>> fetchByLogin(@PathVariable("id") Integer id){
		try {
			List<Factura> facturas = facturaServ.fetchByUserId(id);
			return new ResponseEntity<List<Factura>>(facturas,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Factura>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
