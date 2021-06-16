/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgf.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "002grupofinanceiro")
public class Grupofinanceiro implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "idGrupo")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "administrador")
	private boolean administrador;
	@Basic(optional = false)
	@Column(name = "visualizarTudo")
	private boolean visualizarTudo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo", fetch = FetchType.LAZY)
	private List<Movimentacao> movimentacaoList;
	@JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Usuario idUsuario;

	public Grupofinanceiro() {
	}

	public Grupofinanceiro(Integer idGrupo) {
		this.id = idGrupo;
	}

	public Grupofinanceiro(Integer idGrupo, boolean administrador, boolean visualizarTudo) {
		this.id = idGrupo;
		this.administrador = administrador;
		this.visualizarTudo = visualizarTudo;
	}

	public Integer getIdGrupo() {
		return id;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.id = idGrupo;
	}

	public boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public boolean getVisualizarTudo() {
		return visualizarTudo;
	}

	public void setVisualizarTudo(boolean visualizarTudo) {
		this.visualizarTudo = visualizarTudo;
	}

	public List<Movimentacao> getMovimentacaoList() {
		return movimentacaoList;
	}

	public void setMovimentacaoList(List<Movimentacao> movimentacaoList) {
		this.movimentacaoList = movimentacaoList;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Grupofinanceiro)) {
			return false;
		}
		Grupofinanceiro other = (Grupofinanceiro) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.sgf.controler.Grupofinanceiro[ idGrupo=" + id + " ]";
	}

}
