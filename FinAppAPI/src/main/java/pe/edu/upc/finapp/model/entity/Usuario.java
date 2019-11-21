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
import javax.validation.constraints.Size;

@Entity
@Table(name= "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cusuario;	
	
	@Column(name = "idLogin", length =100, nullable =false)
	@NotNull(message = "ingrese id login")
	String idLogin;
	
	@Column(name = "tcontrasenya", length = 16, nullable=false)
	@Size(max = 16)
	String tcontrasenya;
	
	@Column(name = "nusuario", length = 100, nullable = false)
	@NotNull(message = "ingrese nombre")
	String nusuario;
	
	@Column(name ="truc", length = 11, nullable = false)
	@Size(max =11, min = 11)
	@NotNull(message = "ingrese ruc")
	String truc;
	
	@Column(name = "tdni", length = 8, nullable = false)
	@Size(max =8, min =8 )
	@NotNull(message = "ingrese dni")
	String tdni;
	
	@Column(name = "tcorreo", length = 40, nullable = false)
	@NotNull(message = "ingrese correo")
	String tcorreo;
	
	@ManyToOne
	@JoinColumn(name = "ctipousuario", nullable = false)
	@NotNull(message = "ingrese tipo usuario")
	TipoUsuario ctipousuario;

	public Integer getCusuario() {
		return cusuario;
	}

	public void setCusuario(Integer cusuario) {
		this.cusuario = cusuario;
	}

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

	public String getTcontrasenya() {
		return tcontrasenya;
	}

	public void setTcontrasenya(String tcontrasenya) {
		this.tcontrasenya = tcontrasenya;
	}

	public String getNusuario() {
		return nusuario;
	}

	public void setNusuario(String nusuario) {
		this.nusuario = nusuario;
	}

	public String getTruc() {
		return truc;
	}

	public void setTruc(String truc) {
		this.truc = truc;
	}

	public String getTdni() {
		return tdni;
	}

	public void setTdni(String tdni) {
		this.tdni = tdni;
	}

	public String getTcorreo() {
		return tcorreo;
	}

	public void setTcorreo(String tcorreo) {
		this.tcorreo = tcorreo;
	}

	public TipoUsuario getCtipousuario() {
		return ctipousuario;
	}

	public void setCtipousuario(TipoUsuario ctipousuario) {
		this.ctipousuario = ctipousuario;
	}
	
	
	
}
