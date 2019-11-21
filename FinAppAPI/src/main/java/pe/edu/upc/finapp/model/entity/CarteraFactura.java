package pe.edu.upc.finapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name= "carterafacturas")
public class CarteraFactura {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	Integer ccarterafactura;
	
	@Column(name = "ncarterafactura", length = 100, nullable = false)
	@NotEmpty(message="Ingrese nombre")
	String ncarterafactura;
	
	@Column(name = "mtotarecibido", nullable = false)	
	Double mtotarecibido;
	
	@Column(name = "ptotalTcea", nullable = false)	
	Double ptotalTcea;
	
	@Column(name = "pTir", nullable = false)	
	Double pTir;

	public Integer getCcarterafactura() {
		return ccarterafactura;
	}

	public void setCcarterafactura(Integer ccarterafactura) {
		this.ccarterafactura = ccarterafactura;
	}

	public String getNcarterafactura() {
		return ncarterafactura;
	}

	public void setNcarterafactura(String ncarterafactura) {
		this.ncarterafactura = ncarterafactura;
	}

	public Double getMtotarecibido() {
		return mtotarecibido;
	}

	public void setMtotarecibido(Double mtotarecibido) {
		this.mtotarecibido = mtotarecibido;
	}

	public Double getPtotalTcea() {
		return ptotalTcea;
	}

	public void setPtotalTcea(Double ptotalTcea) {
		this.ptotalTcea = ptotalTcea;
	}

	public Double getpTir() {
		return pTir;
	}

	public void setpTir(Double pTir) {
		this.pTir = pTir;
	}
	
	
}
