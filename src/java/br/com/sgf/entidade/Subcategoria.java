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
@Table(name = "005subcategoria")
@NamedQueries({ @NamedQuery(name = "Subcategoria.findAll", query = "SELECT s FROM Subcategoria s") })
public class Subcategoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "idSubCategoria")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "subCategoria")
	private String subCategoria;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubCategoria", fetch = FetchType.LAZY)
	private List<Movimentacao> movimentacaoList;
	@JoinColumn(name = "id_categoria", referencedColumnName = "idCategoria")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Categoria idCategoria;

	public Subcategoria() {
	}

	public Subcategoria(Integer idSubCategoria) {
		this.id = idSubCategoria;
	}

	public Subcategoria(Integer idSubCategoria, String subCategoria) {
		this.id = idSubCategoria;
		this.subCategoria = subCategoria;
	}

	public Integer getIdSubCategoria() {
		return id;
	}

	public void setIdSubCategoria(Integer idSubCategoria) {
		this.id = idSubCategoria;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public List<Movimentacao> getMovimentacaoList() {
		return movimentacaoList;
	}

	public void setMovimentacaoList(List<Movimentacao> movimentacaoList) {
		this.movimentacaoList = movimentacaoList;
	}

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
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
		if (!(object instanceof Subcategoria)) {
			return false;
		}
		Subcategoria other = (Subcategoria) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.sgf.controler.Subcategoria[ idSubCategoria=" + id + " ]";
	}

}
