package br.com.resource;

import java.sql.Connection;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.conexao.ConectaDatabase;

@Path("/service")
public class IntegrationService {

	private Gson gson = new Gson();
	ConectaDatabase conexao = new ConectaDatabase();
	int codigo_resposta;
	
	
	/*
	@GET
	@Path("/pessoa/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListaParametro(@PathParam("id") String id) {
		String dados = "";
		Pessoa p = new Pessoa();
		try {
			Connection con = ConectaDatabase.criarConexao();
			String sql = "SELECT id, nome, idade, sexo, nacionalidade, cpf, rg, altura "
						+"FROM pessoa "
						+"WHERE id = '"+id+"'";
			ResultSet rs = conexao.getDados(sql);
			
			while(rs.next()) {
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setIdade(rs.getInt("idade"));
				p.setSexo(rs.getString("sexo"));
				p.setNacionalidade(rs.getString("nacionalidade"));
				p.setCpf(rs.getString("cpf"));
				p.setRg(rs.getString("rg"));
				p.setAltura(rs.getFloat("altura"));
				
				dados = gson.toJson(p);
			}
			return dados;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/pessoas")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLista() {
		List<Pessoa> pessoaLista = new ArrayList<>();
		List<Pessoa> lista = new ArrayList<>();
		
		try {
			Connection con = ConectaDatabase.criarConexao();
			String sql = "SELECT * FROM pessoa ORDER BY id";
			ResultSet rs = conexao.getDados(sql);
			
			while(rs.next()) {
				Pessoa p = new Pessoa();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setIdade(rs.getInt("idade"));
				p.setSexo(rs.getString("sexo"));
				p.setNacionalidade(rs.getString("nacionalidade"));
				p.setCpf(rs.getString("cpf"));
				p.setRg(rs.getString("rg"));
				p.setAltura(rs.getFloat("altura"));
				
				pessoaLista.add(p);
			}
			
			String dados_json = gson.toJson(pessoaLista);
			System.out.println("Lista de Pessoas: "+dados_json);
			
			
		    return dados_json;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public String addDados(String json_dados) {
		try {
			if(json_dados == null || json_dados == "") {
				json_dados = "{\"nome\":\"Rafael\", \"idade\":29, \"sexo\":\"Masculino\", \"nacionalidade\":\"Brasileiro\", \"cpf\":\"12345\", \"rg\":\"54321\", \"altura\":1.85}";
			}
			
			List<Pessoa> listaPessoas = (List<Pessoa>) gson.fromJson(json_dados, Pessoa.class);
			List<Pessoa> listaSucesso = new ArrayList<Pessoa>();
			Connection con = ConectaDatabase.criarConexao();
			
			for(Pessoa p : listaPessoas) {		
				String sql = "INSERT INTO pessoa (nome, idade, sexo, nacionalidade, cpf, rg, altura)"
						+ "values('" + p.getNome() + "'," 
						+ "'" + p.getIdade() + "'," 
						+ "'" + p.getSexo() + "',"
						+ "'" + p.getNacionalidade() + "'," 
						+ "'" + p.getCpf() + "'," 
						+ "'" + p.getRg() + "'," 
						+ "'" + p.getAltura() + "')";

				codigo_resposta = conexao.executeSQL(sql);

				if (codigo_resposta > 0) {
					System.out.println("Inserido com sucesso: "+ gson.toJson(p));
					listaSucesso.add(p);
					return gson.toJson(p);
				} else {
					return "Ocorreu um erro ao cadastrar " + codigo_resposta;
				}	
			}
			String dados = null;
			if(listaSucesso.size() > 0) {
			   dados = gson.toJson(listaSucesso);
			}
			return dados;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
    @DELETE
    @Path("/{id}")
    public String excluirPessoa(final @PathParam("id") String id) {

        try {
        	Connection con = ConectaDatabase.criarConexao();
        	String sql = "DELETE FROM pessoa WHERE id = "+id+"";
        	codigo_resposta = conexao.executeSQL(sql);
        	if(codigo_resposta > 0) {
        		System.out.println("Deletado com sucesso");
        		return "Deletado com sucesso";
        	}else {
        		return "Ocorreu um erro ao deletar";
        	}
        	
		}catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar "+ ", " +e.getMessage().toString();
		}
    }
    
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public String atualizaPessoa(String json_dados) {
        
    	try {
			if(json_dados == null || json_dados == "") {
				json_dados = "{\"id\":2,\"nome\":\"Rafael Teste\", \"idade\":22, \"sexo\":\"Masculino\", \"nacionalidade\":\"Brasileiro\", \"cpf\":\"12345\", \"rg\":\"54321\", \"altura\":1.95}";
			}

			Pessoa p = gson.fromJson(json_dados, Pessoa.class);
			Connection con = ConectaDatabase.criarConexao();
			String sql = "UPDATE pessoa SET "
					+ "nome = '"+p.getNome()+"', "
					+ "idade = '"+p.getIdade()+"', "
					+ "sexo = '"+p.getSexo()+"', "
					+ "nacionalidade = '"+p.getNacionalidade()+"', "
					+ "cpf = '"+p.getCpf()+"', "
					+ "rg = '"+p.getRg()+"', "
					+ "altura = '"+p.getAltura()+"' "
					+ "WHERE id = '"+p.getId()+"'";

			codigo_resposta = conexao.executeSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Atualizado com sucesso: "+ gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao atualizar";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    */
    
    @GET
	@Path("/funcionario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFuncionario(@PathParam("id") String id) {
		String dados = "";
		Funcionarios p = new Funcionarios();
		try {
			Connection con = ConectaDatabase.criarConexao();
			String sql = "SELECT id, nome, idade, nacionalidade, cpf, rg, id_setor, id_cargo"
						+"FROM funcionarios "
						+"WHERE id = '"+id+"'";
			ResultSet rs = conexao.getDados(sql);
			
			while(rs.next()) {
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
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/funcionarios")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFuncionarios() {
		List<Funcionarios> funcionarioLista = new ArrayList<>();
		List<Funcionarios> lista = new ArrayList<>();
		
		try {
			Connection con = ConectaDatabase.criarConexao();
			String sql = "SELECT * FROM funcionarios ORDER BY id";
			ResultSet rs = conexao.getDados(sql);
			
			while(rs.next()) {
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
			System.out.println("Lista de Pessoas: "+dados_json);
			
			
		    return dados_json;
		}catch(Exception e) {
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
			Connection con = ConectaDatabase.criarConexao();
			String sql = "SELECT id, cargo, salario "
						+"FROM cargos "
						+"WHERE id = '"+id+"'";
			ResultSet rs = conexao.getDados(sql);
			
			while(rs.next()) {
				p.setId(rs.getInt("id"));
				p.setCargo(rs.getString("cargo"));
				p.setSalario(rs.getFloat("salario"));
				
				dados = gson.toJson(p);
			}
			return dados;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/cargos")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCargos() {
		List<Cargos> cargoLista = new ArrayList<>();
		List<Cargos> lista = new ArrayList<>();
		
		try {
			Connection con = ConectaDatabase.criarConexao();
			String sql = "SELECT * FROM cargos ORDER BY id";
			ResultSet rs = conexao.getDados(sql);
			
			while(rs.next()) {
				Cargos p = new Cargos();
				p.setId(rs.getInt("id"));
				p.setCargo(rs.getString("cargo"));
				p.setSalario(rs.getFloat("salario"));
				
				cargoLista.add(p);
			}
			
			String dados_json = gson.toJson(cargoLista);
			System.out.println("Lista de Pessoas: "+dados_json);
			
			
		    return dados_json;
		}catch(Exception e) {
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
			Connection con = ConectaDatabase.criarConexao();
			String sql = "SELECT id, setor, coordenador, gerente "
						+"FROM setores "
						+"WHERE id = '"+id+"'";
			ResultSet rs = conexao.getDados(sql);
			
			while(rs.next()) {
				p.setId(rs.getInt("id"));
				p.setSetor(rs.getString("nome"));
				p.setCoordenador(rs.getString("idade"));
				p.setGerente(rs.getString("sexo"));

				
				dados = gson.toJson(p);
			}
			return dados;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/setores")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSetores() {
		List<Setores> pessoaLista = new ArrayList<>();
		List<Setores> lista = new ArrayList<>();
		
		try {
			Connection con = ConectaDatabase.criarConexao();
			String sql = "SELECT * FROM setores ORDER BY id";
			ResultSet rs = conexao.getDados(sql);
			
			while(rs.next()) {
				Setores p = new Setores();
				p.setId(rs.getInt("id"));
				p.setSetor(rs.getString("nome"));
				p.setCoordenador(rs.getString("idade"));
				p.setGerente(rs.getString("sexo"));
				
				pessoaLista.add(p);
			}
			
			String dados_json = gson.toJson(pessoaLista);
			System.out.println("Lista de Pessoas: "+dados_json);
			
			
		    return dados_json;
		}catch(Exception e) {
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
			if(json_dados == null || json_dados == "") {
				json_dados = "{\"nome\":\"Enviou nulo\", \"idade\":00, \"nacionalidade\":\"Brasileiro\", \"cpf\":\"12345\", \"rg\":\"54321\", \"id_setor\":\"1\", \"id_cargo\":1}";
			}
			
			Funcionarios p = gson.fromJson(json_dados, Funcionarios.class);

			Connection con = ConectaDatabase.criarConexao();
					
				String sql = "INSERT INTO funcionarios (nome, idade, nacionalidade, cpf, rg, id_setor, id_cargo)"
						+ "values('" + p.getNome() + "'," 
						+ "'" + p.getIdade() + "'," 
						+ "'" + p.getNacionalidade() + "'," 
						+ "'" + p.getCpf() + "'," 
						+ "'" + p.getRg() + "'," 
						+ "'" + p.getId_setor() + "',"
						+ "'" + p.getId_cargo() + "')";

				codigo_resposta = conexao.executeSQL(sql);

				if (codigo_resposta > 0) {
					System.out.println("Inserido com sucesso: "+ gson.toJson(p));
					return gson.toJson(p);
				} else {
					return "Ocorreu um erro ao cadastrar " + codigo_resposta;
				}
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
			if(json_dados == null || json_dados == "") {
				json_dados = "{\"nome\":\"Rafael\", \"idade\":29, \"sexo\":\"Masculino\", \"nacionalidade\":\"Brasileiro\", \"cpf\":\"12345\", \"rg\":\"54321\", \"altura\":1.85}";
			}
			
			Cargos p =  gson.fromJson(json_dados, Cargos.class);
			Connection con = ConectaDatabase.criarConexao();
					
				String sql = "INSERT INTO cargos (cargo, salario)"
						+ "values('" + p.getCargo() + "',"  
						+ "'" + p.getSalario() + "')";

				codigo_resposta = conexao.executeSQL(sql);

				if (codigo_resposta > 0) {
					System.out.println("Inserido com sucesso: "+ gson.toJson(p));
					return gson.toJson(p);
				} else {
					return "Ocorreu um erro ao cadastrar " + codigo_resposta;
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
			if(json_dados == null || json_dados == "") {
				json_dados = "{\"nome\":\"Rafael\", \"idade\":29, \"sexo\":\"Masculino\", \"nacionalidade\":\"Brasileiro\", \"cpf\":\"12345\", \"rg\":\"54321\", \"altura\":1.85}";
			}
			
			Setores p =  gson.fromJson(json_dados, Setores.class);

			Connection con = ConectaDatabase.criarConexao();

				String sql = "INSERT INTO setores (setor, coordenador, gerente)"
						+ "values('" + p.getSetor() + "'," 
						+ "'" + p.getCoordenador() + "'," 
						+ "'" + p.getGerente() + "')";

				codigo_resposta = conexao.executeSQL(sql);

				if (codigo_resposta > 0) {
					System.out.println("Inserido com sucesso: "+ gson.toJson(p));
					return gson.toJson(p);
				} else {
					return "Ocorreu um erro ao cadastrar " + codigo_resposta;
				}	

		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
    @DELETE
    @Path("/funcionario/{id}")
    public String excluirFuncionario(final @PathParam("id") String id) {

        try {
        	Connection con = ConectaDatabase.criarConexao();
        	String sql = "DELETE FROM funcionarios WHERE id = "+id+"";
        	codigo_resposta = conexao.executeSQL(sql);
        	if(codigo_resposta > 0) {
        		System.out.println("Deletado com sucesso");
        		return "Deletado com sucesso";
        	}else {
        		return "Ocorreu um erro ao deletar";
        	}
        	
		}catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar "+ ", " +e.getMessage().toString();
		}
    }
    
    @PUT
    @Path("/atualizar_funcionario/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public String atualizaFuncionario(String json_dados) {
        
    	try {

			Funcionarios p = gson.fromJson(json_dados, Funcionarios.class);
			Connection con = ConectaDatabase.criarConexao();
			String sql = "UPDATE funcionarios SET "
					+ "nome = '"+p.getNome()+"', "
					+ "idade = '"+p.getIdade()+"', "
					+ "nacionalidade = '"+p.getNacionalidade()+"', "
					+ "cpf = '"+p.getCpf()+"', "
					+ "rg = '"+p.getRg()+"', "
					+ "id_setor = '"+p.getId_setor()+"', "
					+ "id_cargo = '"+p.getId_cargo()+"', "
					+ "WHERE id = '"+p.getId()+"'";
					

			codigo_resposta = conexao.executeSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Atualizado com sucesso: "+ gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao atualizar";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
    }

    @PUT
    @Path("/atualizar_cargo/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public String atualizaCargo(String json_dados) {
        
    	try {

			Cargos p = gson.fromJson(json_dados, Cargos.class);
			Connection con = ConectaDatabase.criarConexao();
			String sql = "UPDATE cargos SET "			
			+ "cargo = '"+p.getCargo()+"', "
			+ "salario = '"+p.getSalario()+"', "
			+ "WHERE id = '"+p.getId()+"'";

			codigo_resposta = conexao.executeSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Atualizado com sucesso: "+ gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao atualizar";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    @PUT
    @Path("/atualizar_setor/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public String atualizaSetor(String json_dados) {
        
    	try {

			Setores p = gson.fromJson(json_dados, Setores.class);
			Connection con = ConectaDatabase.criarConexao();
			String sql = "UPDATE setores SET "
						+ "setor = '"+p.getSetor()+"', "
						+ "coordenador = '"+p.getCoordenador()+"', "
						+ "gerente = '"+p.getGerente()+"', "
						+ "WHERE id = '"+p.getId()+"'";

			codigo_resposta = conexao.executeSQL(sql);

			if (codigo_resposta > 0) {
				System.out.println("Atualizado com sucesso: "+ gson.toJson(p));
				return gson.toJson(p);
			} else {
				return "Ocorreu um erro ao atualizar";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
    }
}
