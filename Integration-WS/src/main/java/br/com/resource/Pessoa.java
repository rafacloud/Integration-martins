package br.com.resource;

public class Pessoa {
	
	private int id;
	private String nome;
	private int idade;
	private String sexo;
	private String nacionalidade;
	private String cpf;
	private String rg;
	private float altura;
	private int id_setor;
	private int id_cargo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}

    /**
     * @return int return the id_setor
     */
    public int getId_setor() {
        return id_setor;
    }

    /**
     * @param id_setor the id_setor to set
     */
    public void setId_setor(int id_setor) {
        this.id_setor = id_setor;
    }

    /**
     * @return int return the id_cargo
     */
    public int getId_cargo() {
        return id_cargo;
    }

    /**
     * @param id_cargo the id_cargo to set
     */
    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

}
