package br.com.sgf.dominios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eduardo
 */
public class Conexao {

    static Connection con = null;

//    private static final String DB_URL = "jdbc:mysql://192.168.0.8:3306/sgf?useLegacyDatetimeCode=false&serverTimezone=America/Sao_Paulo";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb?useLegacyDatetimeCode=false&serverTimezone=America/Sao_Paulo";
    private static final String USER = "admin";
    private static final String PASS = "123";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Sucesso na conexão");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Falha na conexão "+e);
        }
        return con;
    }
    public static void main(String args[]) {
        Connection con = Conexao.conectar();
         
    }
    public static void closeConnection() throws SQLException {

        con.close();
    }
}
