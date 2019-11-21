package pe.edu.upc.finapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "gastos")
public class Gasto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cgasto;
	
	@NotEmpty(message = "Ingrese gasto")
	@Column(name = "ngasto", length = 100, nullable = false)
	private String ngasto;

	public Integer getCgasto() {
		return cgasto;
	}

	public void setCgasto(Integer cgasto) {
		this.cgasto = cgasto;
	}

	public String getNgasto() {
		return ngasto;
	}

	public void setNgasto(String ngasto) {
		this.ngasto = ngasto;
	}
}
