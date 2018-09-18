package br.com.resource;

import java.sql.Connection;
import java.sql.SQLException;

import com.google.gson.Gson;

import br.com.conexao.ConectaDatabase;

public class Teste {

	public static void main(String[] args) {
		IntegrationService is = new IntegrationService();
		FormandoJson dados = new FormandoJson();
		ConectaDatabase conexao = new ConectaDatabase();
		Gson gson = new Gson();
		
		//System.out.println("Inserindo no banco de dados: "+ is.addDados(null));
		//System.out.println("Busca no banco: "+is.getLista());
		//System.out.println("Deletando: "+is.excluirPessoa("3"));
		//System.out.println("Atualizando no banco de dados: "+ is.atualizaPessoa(null));
		//System.out.println("Busca no banco com parametro: "+is.getListaParametro("3"));
		
		try {
			conexao.criarConexao();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
