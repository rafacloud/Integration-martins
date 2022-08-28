package br.com.resource;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class FormandoJson {
	
	public List<String> criandoJSon() {
		List<String> lista = new ArrayList<>();
		Pessoa pessoa = new Pessoa();
		Gson gson = new Gson();
		
		//pessoa.setId(0001);
		pessoa.setNome("Rafael Martins");
		pessoa.setIdade(29);
		pessoa.setSexo("Masculino");
		pessoa.setNacionalidade("Brasileiro");
		pessoa.setRg("44.651.223-0");
		pessoa.setCpf("384.175.898.30");
		pessoa.setId_setor(1);
		pessoa.setId_cargo(1);
		
		String dados =  gson.toJson(pessoa);
		lista.add(dados);
		return lista;
	}
	
	public String dadosJson() {
		Pessoa pessoa = new Pessoa();
		Gson gson = new Gson();
		
		//pessoa.setId(0001);
		pessoa.setNome("Rafael Martins");
		pessoa.setIdade(29);
		pessoa.setSexo("Masculino");
		pessoa.setNacionalidade("Brasileiro");
		pessoa.setRg("44.651.223-0");
		pessoa.setCpf("384.175.898.30");
		pessoa.setId_setor(1);
		pessoa.setId_cargo(1);
		
		String dados =  gson.toJson(pessoa);
		return dados;
	}

	public String dadosFuncionarios() {
		Funcionarios funcionario = new Funcionarios();
		Gson gson = new Gson();
		
		//pessoa.setId(0001);
		funcionario.setNome("Rafael Martins");
		funcionario.setIdade(29);
		funcionario.setNacionalidade("Brasileiro");
		funcionario.setRg("44.651.223-0");
		funcionario.setCpf("384.175.898.30");
		funcionario.setId_setor(1);
		funcionario.setId_cargo(1);
		
		String dados =  gson.toJson(funcionario);
		return dados;
	}
}
