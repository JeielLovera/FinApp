package pe.edu.upc.finapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name= "tipousuarios")
public class TipoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ctipousuario;
	
	@Column(name="ntipousuario", length = 40,nullable=false)
	@NotEmpty(message="ingrese nombre")
	private String ntipousuario;

	public Integer getCtipousuario() {
		return ctipousuario;
	}

	public void setCtipousuario(Integer ctipousuario) {
		this.ctipousuario = ctipousuario;
	}

	public String getNtipousuario() {
		return ntipousuario;
	}

	public void setNtipousuario(String ntipousuario) {
		this.ntipousuario = ntipousuario;
	}
	
	
}
