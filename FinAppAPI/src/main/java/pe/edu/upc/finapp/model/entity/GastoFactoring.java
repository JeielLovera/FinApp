package pe.edu.upc.finapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gasto_factorings")
public class GastoFactoring {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cgasto_factoring;
	
	@ManyToOne
	@JoinColumn(name = "cfactoring", nullable = false)
	@NotNull(message = "Ingrese factoring")
	private Factoring cfactoring;
	
	@ManyToOne
	@JoinColumn(name = "cgasto", nullable = false)
	@NotNull(message = "Ingrese gasto")
	private Gasto cgasto;
	
	@Column(name = "ftipogasto")
	private boolean ftipogasto;
	
	@Column(name = "mgasto")
	private double mgasto;

	public Integer getCgasto_factoring() {
		return cgasto_factoring;
	}

	public void setCgasto_factoring(Integer cgasto_factoring) {
		this.cgasto_factoring = cgasto_factoring;
	}

	public Factoring getCfactoring() {
		return cfactoring;
	}

	public void setCfactoring(Factoring cfactoring) {
		this.cfactoring = cfactoring;
	}

	public Gasto getCgasto() {
		return cgasto;
	}

	public void setCgasto(Gasto cgasto) {
		this.cgasto = cgasto;
	}

	public boolean isFtipogasto() {
		return ftipogasto;
	}

	public void setFtipogasto(boolean ftipogasto) {
		this.ftipogasto = ftipogasto;
	}

	public double getMgasto() {
		return mgasto;
	}

	public void setMgasto(double mgasto) {
		this.mgasto = mgasto;
	}
	
	
}
