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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "factorings")
public class Factoring {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cfactoring;
	
	//private Factura cfactura
	
	@NotNull(message = "Ingrese fecha de descuento")
	@Temporal(TemporalType.DATE)
	@Column(name = "ddescuento")
	private Date ddescuento;
	
	@NotEmpty(message = "Ingrese banco")
	@Column(name = "tbanco", length = 100, nullable = false)
	private String tbanco;
	
	@Column(name = "pdescuento")
	private double pdescuento;
	
	@Column(name = "mdescuento")
	private double mdescuento;
	
	@Column(name = "mneto")
	private double mneto;
	
	@Column(name = "mrecibido")
	private double mrecibido;
	
	@Column(name = "mentregado")
	private double mentregado;
	
	@Column(name = "ptcea")
	private double ptcea;
	
	@Column(name = "ptasaoriginal")
	private double ptasaoriginal;
	
	@Column(name = "nfrecuenciatasaoriginal")
	private int nfrecuenciatasaoriginal;
	
	@Column(name = "nfrecuenciacapitalizacion")
	private int nfrecuenciacapitalizacion;
	
	@ManyToOne
	@JoinColumn(name = "ctipointeres", nullable = false)
	@NotNull(message = "Ingrese tipo interes")
	private TipoInteres ctipointeres;

	//private CarteraFactura ccarterafactura;
	
	
	public Integer getCfactoring() {
		return cfactoring;
	}

	public void setCfactoring(Integer cfactoring) {
		this.cfactoring = cfactoring;
	}

	public Date getDdescuento() {
		return ddescuento;
	}

	public void setDdescuento(Date ddescuento) {
		this.ddescuento = ddescuento;
	}

	public String getTbanco() {
		return tbanco;
	}

	public void setTbanco(String tbanco) {
		this.tbanco = tbanco;
	}

	public double getPdescuento() {
		return pdescuento;
	}

	public void setPdescuento(double pdescuento) {
		this.pdescuento = pdescuento;
	}

	public double getMdescuento() {
		return mdescuento;
	}

	public void setMdescuento(double mdescuento) {
		this.mdescuento = mdescuento;
	}

	public double getMneto() {
		return mneto;
	}

	public void setMneto(double mneto) {
		this.mneto = mneto;
	}

	public double getMrecibido() {
		return mrecibido;
	}

	public void setMrecibido(double mrecibido) {
		this.mrecibido = mrecibido;
	}

	public double getMentregado() {
		return mentregado;
	}

	public void setMentregado(double mentregado) {
		this.mentregado = mentregado;
	}

	public double getPtcea() {
		return ptcea;
	}

	public void setPtcea(double ptcea) {
		this.ptcea = ptcea;
	}

	public double getPtasaoriginal() {
		return ptasaoriginal;
	}

	public void setPtasaoriginal(double ptasaoriginal) {
		this.ptasaoriginal = ptasaoriginal;
	}

	public int getNfrecuenciatasaoriginal() {
		return nfrecuenciatasaoriginal;
	}

	public void setNfrecuenciatasaoriginal(int nfrecuenciatasaoriginal) {
		this.nfrecuenciatasaoriginal = nfrecuenciatasaoriginal;
	}

	public int getNfrecuenciacapitalizacion() {
		return nfrecuenciacapitalizacion;
	}

	public void setNfrecuenciacapitalizacion(int nfrecuenciacapitalizacion) {
		this.nfrecuenciacapitalizacion = nfrecuenciacapitalizacion;
	}

	public TipoInteres getCtipointeres() {
		return ctipointeres;
	}

	public void setCtipointeres(TipoInteres ctipointeres) {
		this.ctipointeres = ctipointeres;
	}
	
	
	
	
}
