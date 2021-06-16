package br.com.sgf.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sgf.dominios.Conexao;
import br.com.sgf.entidade.Usuario;
	
public class UsuarioDao {

	private String sql;
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;

	public void inserir(Usuario usuario) {
		sql = "INSERT INTO 001usuario (nome, email, data_nascimento, senha, primeiroAcesso) "
				+ "VALUES (?,?,?,?, '0')";
		con= Conexao.conectar();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setDate(3, (Date) usuario.getDataNascimento());
			ps.setString(4, usuario.getSenha());
			ps.setInt(5, 0);
			
			
			
		}catch (SQLException ex) {
			System.out.println(ex);
		}
		
		
		
	}

}
