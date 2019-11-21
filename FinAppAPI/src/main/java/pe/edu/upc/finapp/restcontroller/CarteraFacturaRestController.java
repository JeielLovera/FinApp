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
import pe.edu.upc.finapp.model.entity.CarteraFactura;
import pe.edu.upc.finapp.service.CarteraFacturaService;

@RestController
@RequestMapping("/carterafacturas")
@Api(value="REST para carterafacturas")
public class CarteraFacturaRestController {

	@Autowired
	private CarteraFacturaService carteraServ;
	
	@ApiOperation("Fetch all carterafacturas")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CarteraFactura>> fetchAll(){
		try {
			List<CarteraFactura> carteras = carteraServ.findAll();
			return new ResponseEntity<List<CarteraFactura>>(carteras,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<CarteraFactura>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save cartera")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@Valid @RequestBody CarteraFactura cartera){
		try {
			CarteraFactura tmp= carteraServ.save(cartera);
			if(tmp!=null) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else
				return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("update de cartera")
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@Valid @RequestBody CarteraFactura cartera){
		try {
			Optional<CarteraFactura> buscado= carteraServ.findById(cartera.getCcarterafactura());
			if(buscado.isPresent()) {
				carteraServ.update(cartera);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}	
	
	@ApiOperation("Fetch cartera por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarteraFactura> fetchById(@PathVariable("id") Integer id){
		try {
			Optional<CarteraFactura> cartera = carteraServ.findById(id);
			if(cartera.isPresent())
				return new ResponseEntity<CarteraFactura>(cartera.get(), HttpStatus.OK);
			else
				return new ResponseEntity<CarteraFactura>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<CarteraFactura>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Remove cartera por id")
	@DeleteMapping(value= "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
		try {
			Optional<CarteraFactura> cartera= carteraServ.findById(id);
			if(cartera.isPresent()) {
				carteraServ.deleteById(id);
				return new ResponseEntity<String>("cartera eliminada",HttpStatus.OK);
			}
			else return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
