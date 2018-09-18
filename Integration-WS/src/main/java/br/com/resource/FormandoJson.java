package br.com.resource;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class FormandoJson {
	
	public List<String> criandoJSon() {
		List<String> lista = new ArrayList<>();
		Pessoa pessoa = new Pessoa();
		Gson gson = new Gson();
		
		pessoa.setId(0001);
		pessoa.setNome("Rafael Martins");
		pessoa.setIdade(29);
		pessoa.setSexo("Masculino");
		pessoa.setAltura(1.85f);
		pessoa.setNacionalidade("Brasileiro");
		pessoa.setRg("44.651.223-0");
		pessoa.setCpf("384.175.898.30");
		
		String dados =  gson.toJson(pessoa);
		lista.add(dados);
		return lista;
	}
	
	public String dadosJson() {
		Pessoa pessoa = new Pessoa();
		Gson gson = new Gson();
		
		pessoa.setId(0001);
		pessoa.setNome("Rafael Martins");
		pessoa.setIdade(29);
		pessoa.setSexo("Masculino");
		pessoa.setAltura(1.85f);
		pessoa.setNacionalidade("Brasileiro");
		pessoa.setRg("44.651.223-0");
		pessoa.setCpf("384.175.898.30");
		
		String dados =  gson.toJson(pessoa);
		return dados;
	}
}
