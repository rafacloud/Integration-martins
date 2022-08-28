package br.com.resource;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.conexao.ConectaDatabase;

@Path("/service")
public class IntegrationService {

	private Gson gson = new Gson();
	ConectaDatabase conexao = new ConectaDatabase();
	int codigo_resposta;
	ResultSet rs;

	@GET
	@Path("/funcionario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFuncionario(@PathParam("id") String id) {
		String dados = "";
		Funcionarios p = new Funcionarios();
		try {
			ConectaDatabase.criarConexao();
			String sql = "SELECT id, nome, idade, nacionalidade, cpf, rg, id_setor, id_cargo "
					+ "FROM funcionarios "
					+ "WHERE id = '" + id + "'";
			ResultSet rs = conexao.getDados(sql);

			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setIdade(rs.getInt("idade"));
				p.setNacionalidade(rs.getString("nacionalidade"));
				p.setCpf(rs.getString("cpf"));
				p.setRg(rs.getString("rg"));
				p.setId_setor(rs.getInt("id_setor"));
				p.setId_cargo(rs.getInt("id_cargo"));

				dados = gson.toJson(p);
			}
			return dados;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/funcionarios")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFuncionarios() {
		List<Funcionarios> funcionarioLista = new ArrayList<>();

		try {
			ConectaDatabase.criarConexao();
			String sql = "SELECT * FROM funcionarios ORDER BY id";
			ResultSet rs = conexao.getDados(sql);

			while (rs.next()) {
				Funcionarios p = new Funcionarios();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setIdade(rs.getInt("idade"));
				p.setNacionalidade(rs.getString("nacionalidade"));
				p.setCpf(rs.getString("cpf"));
				p.setRg(rs.getString("rg"));
				p.setId_setor(rs.getInt("id_setor"));
				p.setId_cargo(rs.getInt("id_cargo"));

				funcionarioLista.add(p);
			}

			String dados_json = gson.toJson(funcionarioLista);
			System.out.println("Lista de Pessoas: " + dados_json);

			return dados_json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/cargo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCargo(@PathParam("id") String id) {
		String dados = "";
		Cargos p = new Cargos();
		try {
			ConectaDatabase.criarConexao();
			String sql = "SELECT id, cargo, salario "
					+ "FROM cargos "
					+ "WHERE id = '" + id + "'";
			ResultSet rs = conexao.getDados(sql);

			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setCargo(rs.getString("cargo"));
				p.setSalario(rs.getFloat("salario"));

				dados = gson.toJson(p);
			}
			return dados;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/cargos")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCargos() {
		List<Cargos> cargoLista = new ArrayList<>();

		try {
			ConectaDatabase.criarConexao();
			String sql = "SELECT * FROM cargos ORDER BY id";
			ResultSet rs = conexao.getDados(sql);

			while (rs.next()) {
				Cargos p = new Cargos();
				p.setId(rs.getInt("id"));
				p.setCargo(rs.getString("cargo"));
				p.setSalario(rs.getFloat("salario"));

				cargoLista.add(p);
			}

			String dados_json = gson.toJson(cargoLista);
			System.out.println("Lista de Pessoas: " + dados_json);

			return dados_json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/setor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSetor(@PathParam("id") String id) {
		String dados = "";
		Setores p = new Setores();
		try {
			ConectaDatabase.criarConexao();
			String sql = "SELECT id, setor, coordenador, gerente "
					+ "FROM setores "
					+ "WHERE id = '" + id + "'";
			ResultSet rs = conexao.getDados(sql);

			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setSetor(rs.getString("setor"));
				p.setCoordenador(rs.getString("coordenador"));
				p.setGerente(rs.getString("gerente"));

				dados = gson.toJson(p);
			}
			return dados;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/setores")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSetores() {
		List<Setores> pessoaLista = new ArrayList<>();

		try {
			ConectaDatabase.criarConexao();
			String sql = "SELECT * FROM setores ORDER BY id";
			ResultSet rs = conexao.getDados(sql);

			while (rs.next()) {
				Setores p = new Setores();
				p.setId(rs.getInt("id"));
				p.setSetor(rs.getString("setor"));
				p.setCoordenador(rs.getString("coordenador"));
				p.setGerente(rs.getString("gerente"));

				pessoaLista.add(p);
			}

			String dados_json = gson.toJson(pessoaLista);
			System.out.println("Lista de Pessoas: " + dados_json);

			return dados_json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/cadastrar_funcionario")
	@Produces("application/json")
	@Consumes("application/json")
	public String cadastrarFuncionarios(String json_dados) {

		try {
			if (json_dados == null || json_dados == "")
				return null;

			Funcionarios funcionarios = gson.fromJson(json_dados, Funcionarios.class);

			ConectaDatabase.criarConexao();
			funcionarios = conexao.executeSQL(funcionarios);

			System.out.println("Funcionarios: " + funcionarios);

			return gson.toJson(funcionarios);

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@POST
	@Path("/cadastrar_cargo")
	@Produces("application/json")
	@Consumes("application/json")
	public String cadastrarCargo(String json_dados) {
		try {

			Cargos p = gson.fromJson(json_dados, Cargos.class);
			ConectaDatabase.criarConexao();

			String sql = "INSERT INTO cargos (cargo, salario)"
					+ "values('" + p.getCargo() + "',"
					+ "'" + p.getSalario() + "')";

			codigo_resposta = conexao.executeUpdateSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Inserido com sucesso: " + gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao cadastrar " + rs;
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@POST
	@Path("/cadastrar_setor")
	@Produces("application/json")
	@Consumes("application/json")
	public String cadastrarSetor(String json_dados) {
		try {

			Setores p = gson.fromJson(json_dados, Setores.class);

			ConectaDatabase.criarConexao();

			String sql = "INSERT INTO setores (setor, coordenador, gerente)"
					+ "values('" + p.getSetor() + "',"
					+ "'" + p.getCoordenador() + "',"
					+ "'" + p.getGerente() + "')";

			codigo_resposta = conexao.executeUpdateSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Inserido com sucesso: " + gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao cadastrar " + rs;
			}

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@DELETE
	@Path("/funcionario/{id}")
	public String excluirFuncionario(final @PathParam("id") String id) {

		try {
			ConectaDatabase.criarConexao();
			String sql = "DELETE FROM funcionarios WHERE id = " + id + "";
			codigo_resposta = conexao.executeUpdateSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Deletado com sucesso");
				return "Deletado com sucesso";
			} else {
				return "Ocorreu um erro ao deletar";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar " + ", " + e.getMessage().toString();
		}
	}

	@PUT
	@Path("/atualizar_funcionario")
	@Produces("application/json")
	@Consumes("application/json")
	public String atualizaFuncionario(String json_dados) {

		try {

			Funcionarios p = gson.fromJson(json_dados, Funcionarios.class);
			ConectaDatabase.criarConexao();
			String sql = "UPDATE funcionarios SET "
					+ "nome = '" + p.getNome() + "', "
					+ "idade = '" + p.getIdade() + "', "
					+ "nacionalidade = '" + p.getNacionalidade() + "', "
					+ "cpf = '" + p.getCpf() + "', "
					+ "rg = '" + p.getRg() + "', "
					+ "id_setor = '" + p.getId_setor() + "', "
					+ "id_cargo = '" + p.getId_cargo() + "' "
					+ "WHERE id = '" + p.getId() + "'";

			codigo_resposta = conexao.executeUpdateSQL(sql);

			System.out.println("Response: " + rs);

			if (codigo_resposta > 0) {
				System.out.println("Atualizado com sucesso: " + gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao atualizar";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PUT
	@Path("/atualizar_cargo")
	@Produces("application/json")
	@Consumes("application/json")
	public String atualizaCargo(String json_dados) {

		try {

			Cargos p = gson.fromJson(json_dados, Cargos.class);
			ConectaDatabase.criarConexao();
			String sql = "UPDATE cargos SET "
					+ "cargo = '" + p.getCargo() + "', "
					+ "salario = '" + p.getSalario() + "' "
					+ "WHERE id = '" + p.getId() + "'";

			codigo_resposta = conexao.executeUpdateSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Atualizado com sucesso: " + gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao atualizar";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PUT
	@Path("/atualizar_setor")
	@Produces("application/json")
	@Consumes("application/json")
	public String atualizaSetor(String json_dados) {

		try {

			Setores p = gson.fromJson(json_dados, Setores.class);
			ConectaDatabase.criarConexao();
			String sql = "UPDATE setores SET "
					+ "setor = '" + p.getSetor() + "', "
					+ "coordenador = '" + p.getCoordenador() + "', "
					+ "gerente = '" + p.getGerente() + "' "
					+ "WHERE id = '" + p.getId() + "'";

			codigo_resposta = conexao.executeUpdateSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Atualizado com sucesso: " + gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao atualizar";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
