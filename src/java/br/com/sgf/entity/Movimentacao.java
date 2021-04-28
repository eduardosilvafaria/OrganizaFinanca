/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgf.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "003movimentacao")
@NamedQueries({
    @NamedQuery(name = "Movimentacao.findAll", query = "SELECT m FROM Movimentacao m")})
public class Movimentacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLancamento")
    private Integer idLancamento;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;
    @Basic(optional = false)
    @Column(name = "reincidencia")
    private int reincidencia;
    @JoinColumn(name = "id_subCategoria", referencedColumnName = "idSubCategoria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Subcategoria idsubCategoria;
    @JoinColumn(name = "id_grupo", referencedColumnName = "idGrupo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grupofinanceiro idGrupo;
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;

    public Movimentacao() {
    }

    public Movimentacao(Integer idLancamento) {
        this.idLancamento = idLancamento;
    }

    public Movimentacao(Integer idLancamento, String descricao, String valor, Date dataVencimento, int reincidencia) {
        this.idLancamento = idLancamento;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.reincidencia = reincidencia;
    }

    public Integer getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Integer idLancamento) {
        this.idLancamento = idLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public int getReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(int reincidencia) {
        this.reincidencia = reincidencia;
    }

    public Subcategoria getIdsubCategoria() {
        return idsubCategoria;
    }

    public void setIdsubCategoria(Subcategoria idsubCategoria) {
        this.idsubCategoria = idsubCategoria;
    }

    public Grupofinanceiro getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupofinanceiro idGrupo) {
        this.idGrupo = idGrupo;
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
        hash += (idLancamento != null ? idLancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimentacao)) {
            return false;
        }
        Movimentacao other = (Movimentacao) object;
        if ((this.idLancamento == null && other.idLancamento != null) || (this.idLancamento != null && !this.idLancamento.equals(other.idLancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgf.controler.Movimentacao[ idLancamento=" + idLancamento + " ]";
    }
    
}
