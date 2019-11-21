package pe.edu.upc.finapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tipointereses")
public class TipoInteres {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ctipointeres;
	
	@NotEmpty(message = "Ingrese tipo de interes")
	@Column(name = "ntipointeres", length = 100, nullable = false)
	private String ntipointeres;

	public Integer getCtipointeres() {
		return ctipointeres;
	}

	public void setCtipointeres(Integer ctipointeres) {
		this.ctipointeres = ctipointeres;
	}

	public String getNtipointeres() {
		return ntipointeres;
	}

	public void setNtipointeres(String ntipointeres) {
		this.ntipointeres = ntipointeres;
	}
	
}
