/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgf.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Eduardo
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Usuario.findByEmailSenha", query = "SELECT c FROM Categoria c"	
+ "WHERE c.email = :email AND c.senha = :senha") })

@Table(name = "001usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7863065988635487972L;

	
	@Transient
	public static final String FIND_BY_EMAIL_SENHA = "Usuario.findByEmailSenha";
	
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idUsuario")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "nome")
	private String nome;

	@Basic(optional = false)
	@Column(name = "email")
	private String email;

	@Basic(optional = false)
	@Column(name = "dataNascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Basic(optional = false)
	@Column(name = "senha")
	private String senha;

	@Basic(optional = false)
	@Column(name = "primeiroAcesso")
	private boolean primeiroAcesso;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
	private List<Movimentacao> movimentacaoList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
	private List<Grupofinanceiro> grupofinanceiroList;

	public Usuario() {
	}

	public Usuario(Integer idUsuario) {
		this.id = idUsuario;
	}

	public Usuario(Integer idUsuario, String nome, String email, Date dataNascimento, String senha,
			boolean primeiroAcesso) {
		this.id = idUsuario;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.primeiroAcesso = primeiroAcesso;
	}

	public Integer getIdUsuario() {
		return id;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.id = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean getPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public void setPrimeiroAcesso(boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}

	public List<Movimentacao> getMovimentacaoList() {
		return movimentacaoList;
	}

	public void setMovimentacaoList(List<Movimentacao> movimentacaoList) {
		this.movimentacaoList = movimentacaoList;
	}

	public List<Grupofinanceiro> getGrupofinanceiroList() {
		return grupofinanceiroList;
	}

	public void setGrupofinanceiroList(List<Grupofinanceiro> grupofinanceiroList) {
		this.grupofinanceiroList = grupofinanceiroList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result +((id ==null)?0:id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}
		if(object == null) {
			return false;
		}
		if(getClass() != object.getClass())
			return false;
		
		return (object instanceof AbstractBean) ? (this.getIdUsuario() == null ? this == object :
            this.getIdUsuario().equals(((AbstractBean)object).getIdUsuario())):false;
	}

	@Override
	public String toString() {
		return "br.com.sgf.controler.Usuario[ idUsuario=" + id + " ]";
	}

}
