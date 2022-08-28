package br.com.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.resource.Funcionarios;

public class ConectaDatabase {
	/*
	 * static final String URL = "jdbc:postgresql://localhost:5432/pessoas"; //
	 * incica o caminho do banco de dados
	 * static final String USER = "postgres"; // aqui vai o nome usuario que vc quer
	 * acessar
	 * static final String PASS = "admin"; // aqui a senha do seu banco
	 */
	// static final String URL =
	// "jdbc:postgresql://ec2-54-204-23-228.compute-1.amazonaws.com:5432/db6ejj7tc7ljld?sslmode=require";
	// // insira o caminho do banco de dados
	// static final String USER = "sdazktkexfxoaz"; // aqui vai o nome usuario que
	// vc quer acessar
	// static final String PASS =
	// "9d2ff6b17c36f8194560ec267ef04657f0d4ce40f5723a8d5ed52da814445745"; // aqui a
	// senha do seu banco

	static final String URL = "jdbc:postgresql://ec2-34-234-240-121.compute-1.amazonaws.com:5432/d9ni9dufmdqvap?sslmode=require"; // insira
																																	// o
																																	// caminho
																																	// do
																																	// banco
																																	// de
																																	// dados
	static final String USER = "yxhzaesiwfdntb"; // aqui vai o nome usuario que vc quer acessar
	static final String PASS = "696ceae28b4ae322c3d4e05243502d38830a2baefd4cabde82d80f25b7dfce8f"; // aqui a senha do
																									// seu banco

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
	 */
	public Funcionarios executeSQL(Funcionarios funcionario) {
		try {
			String id = "";
			String sql = "INSERT INTO funcionarios (nome, idade, nacionalidade, cpf, rg, id_setor, id_cargo) values(?,?,?,?,?,?,?)";
			PreparedStatement psm = conecta.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			psm.setString(1, funcionario.getNome());
			psm.setInt(2, funcionario.getIdade());
			psm.setString(3, funcionario.getNacionalidade());
			psm.setString(4, funcionario.getCpf());
			psm.setString(5, funcionario.getRg());
			psm.setInt(6, funcionario.getId_setor());
			psm.setInt(7, funcionario.getId_cargo());

			psm.executeUpdate();
			ResultSet rs = psm.getGeneratedKeys();

			if(rs.next()){
				id = rs.getString(1);
				System.out.println("Id: "+ id);
			}

			funcionario.setId(Integer.valueOf(id));

			conecta.close();
			psm.close();

			return funcionario;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param método updateSQL
	 * @return String
	 */
	public int executeUpdateSQL(String sql) {
		try {

			 Statement stmt = conecta.createStatement();
			 int resp = stmt.executeUpdate(sql);

			conecta.close();
			stmt.close();

			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @param método buscarSQL
	 * @return ResultSet
	 */
	public ResultSet getDados(String sql) {

		try {
			Statement stmt = conecta.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(" \n Buscando no banco: " + rs);

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
