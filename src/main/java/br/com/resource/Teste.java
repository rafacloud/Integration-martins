package br.com.resource;
public class Teste {

	public static void main(String[] args) {
		IntegrationService integrationservice = new IntegrationService();
		FormandoJson dados = new FormandoJson(); // cria um json de exemplo para ser usado na chamada

		System.out.println("Inserindo no banco de dados: "+ integrationservice.cadastrarFuncionarios(dados.dadosFuncionarios()));
		//System.out.println("Inserindo no banco de dados: "+ is.addDados(null));
		//System.out.println("Busca no banco: "+is.getLista());
		//System.out.println("Deletando: "+is.excluirPessoa("3"));
		//System.out.println("Atualizando no banco de dados: "+ is.atualizaPessoa(null));
		//System.out.println("Busca no banco com parametro: "+is.getListaParametro("3"));
	}
}
