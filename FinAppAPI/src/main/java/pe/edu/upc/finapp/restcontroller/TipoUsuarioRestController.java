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
import pe.edu.upc.finapp.model.entity.TipoUsuario;
import pe.edu.upc.finapp.service.TipoUsuarioService;

@RestController
@RequestMapping("/tipousuarios")
@Api(value= "REST para tipousuarios")
public class TipoUsuarioRestController {

	@Autowired
	private TipoUsuarioService tipoUsuarioServ;
	
	@ApiOperation("Fetch all tipousuarios")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoUsuario>> fetchAll(){
		try {
			List<TipoUsuario> tipousuarios= tipoUsuarioServ.findAll();
			return new ResponseEntity<List<TipoUsuario>>(tipousuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TipoUsuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save tipo usuario")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>save(@Valid @RequestBody TipoUsuario tipousuario){
		try {
			TipoUsuario tmp= tipoUsuarioServ.save(tipousuario);
			if(tmp != null) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("update de tipousuario")
	@PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>update(@Valid @RequestBody TipoUsuario tipousuario){
		try {
			Optional<TipoUsuario> buscado = tipoUsuarioServ.findById(tipousuario.getCtipousuario());
			if( buscado.isPresent()) {
				tipoUsuarioServ.update(tipousuario);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Fetch tipousuario por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoUsuario>fetchById(@PathVariable("id") Integer id){
		try {
			Optional<TipoUsuario> tipousuario = tipoUsuarioServ.findById(id);
			if(tipousuario.isPresent())
				return new ResponseEntity<TipoUsuario>(tipousuario.get(), HttpStatus.OK);
			else
				return new ResponseEntity<TipoUsuario>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<TipoUsuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Remove tipousuario por id")
	@DeleteMapping(value= "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>deleteById(@PathVariable("id") Integer id ){
		try {
			Optional<TipoUsuario> tipousuario= tipoUsuarioServ.findById(id);
			if(tipousuario.isPresent()) {
				tipoUsuarioServ.deleteById(id);
				return new ResponseEntity<String>("tipousuario eliminado",HttpStatus.OK);
			}
			else return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
