package br.com.sgf.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "004categoria")
@NamedQueries({ @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c") })
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "idCategoria")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "categoria")
	private String categoria;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria", fetch = FetchType.LAZY)
	private List<Subcategoria> subcategoriaList;

	public Categoria() {
	}

	public Categoria(Integer idCategoria) {
		this.id = idCategoria;
	}

	public Categoria(Integer idCategoria, String categoria) {
		this.id = idCategoria;
		this.categoria = categoria;
	}

	public Integer getIdCategoria() {
		return id;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.id = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Subcategoria> getSubcategoriaList() {
		return subcategoriaList;
	}

	public void setSubcategoriaList(List<Subcategoria> subcategoriaList) {
		this.subcategoriaList = subcategoriaList;
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
		if (!(object instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.sgf.controler.Categoria[ idCategoria=" + id + " ]";
	}

}
