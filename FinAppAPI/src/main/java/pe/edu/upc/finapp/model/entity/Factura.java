package pe.edu.upc.finapp.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "facturas")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cfactura;
	
	@Column(name = "ntitulofactura", length = 100, nullable = false)
	@NotNull(message = "ingrese titulo")
	private String ntitulofactura;
	
	@Column(name = "mvalornominal", nullable = false)
	private Double mvalornominal;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dfirma")
	@NotNull(message = "ingrese fecha firma")
	private Date dfirma;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dvencimiento")
	@NotNull(message = "ingrese fecha vencimiento")
	private Date dvencimiento;
	
	@ManyToOne
	@JoinColumn(name = "cusuario", nullable = false)
	@NotNull(message = "ingrese usuario")
	private Usuario cusuario;
	
	@Column(name = "ttipomoneda", length = 10,nullable = false)
	@NotNull(message = "ingrese tipo moneda")
	private String ttipomoneda;

	public Integer getCfactura() {
		return cfactura;
	}

	public void setCfactura(Integer cfactura) {
		this.cfactura = cfactura;
	}

	public String getNtitulofactura() {
		return ntitulofactura;
	}

	public void setNtitulofactura(String ntitulofactura) {
		this.ntitulofactura = ntitulofactura;
	}

	public Double getMvalornominal() {
		return mvalornominal;
	}

	public void setMvalornominal(Double mvalornominal) {
		this.mvalornominal = mvalornominal;
	}

	public Date getDfirma() {
		return dfirma;
	}

	public void setDfirma(Date dfirma) {
		this.dfirma = dfirma;
	}

	public Date getDvencimiento() {
		return dvencimiento;
	}

	public void setDvencimiento(Date dvencimiento) {
		this.dvencimiento = dvencimiento;
	}

	public Usuario getCusuario() {
		return cusuario;
	}

	public void setCusuario(Usuario cusuario) {
		this.cusuario = cusuario;
	}

	public String getTtipomoneda() {
		return ttipomoneda;
	}

	public void setTtipomoneda(String ttipomoneda) {
		this.ttipomoneda = ttipomoneda;
	}
	
	
}
