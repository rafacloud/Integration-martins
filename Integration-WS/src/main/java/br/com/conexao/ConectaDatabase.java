package br.com.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectaDatabase {
	/*
	static final String URL = "jdbc:postgresql://localhost:5432/pessoas"; // incica o caminho do banco de dados
	static final String USER = "postgres"; // aqui vai o nome usuario que vc quer acessar
	static final String PASS = "admin"; // aqui a senha do seu banco
	*/
	//static final String URL = "jdbc:postgresql://ec2-54-204-23-228.compute-1.amazonaws.com:5432/db6ejj7tc7ljld?sslmode=require"; // insira o caminho do banco de dados
	//static final String USER = "sdazktkexfxoaz"; // aqui vai o nome usuario que vc quer acessar
	//static final String PASS = "9d2ff6b17c36f8194560ec267ef04657f0d4ce40f5723a8d5ed52da814445745"; // aqui a senha do seu banco
	
	static final String URL = "jdbc:postgresql://ec2-54-163-245-44.compute-1.amazonaws.com:5432/daf556dmoqptmf?sslmode=require"; // insira o caminho do banco de dados
	static final String USER = "hujiljxcjxozll"; // aqui vai o nome usuario que vc quer acessar
	static final String PASS = "eb6527d2c2d7d53cc5e10a86006147f50898e75959032d7970e1126156ee464c"; // aqui a senha do seu banco
	
	
	static Connection conecta;

	public static Connection criarConexao() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		conecta = DriverManager.getConnection(URL, USER, PASS);
		if (conecta != null) {
			System.out.print("Conexão efetuada com sucesso...");
			return conecta;
		}
		return null;
	}
	
	/**
	 * @param método insertSQL
	 * @return int
	 * */
	public int executeSQL(String sql) {
		try {
			Statement stmt = conecta.createStatement();
			int resp = stmt.executeUpdate(sql);
			conecta.close();
			stmt.close();
			System.out.println("Inserindo no banco ");
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @param método buscarSQL
	 * @return ResultSet
	 * */
	public ResultSet getDados(String sql) {

		try {
			Statement stmt = conecta.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(" \n Buscando no banco: "+rs);

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
