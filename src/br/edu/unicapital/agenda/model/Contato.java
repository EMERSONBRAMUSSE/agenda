package br.edu.unicapital.agenda.model;

import java.sql.Connection;

public class Contato {
	int id;
	String nome;
	String telefone1;
	String telefone2;

	public Contato() {
	};

	public Contato(String id, String nome, String telefone1,String telefone2) {
		this.id = new Integer(id);
		this.nome = nome;
		this.telefone1 = telefone1;
		this.telefone2= telefone2;
	}

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

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public Connection getConexao() {
		// TODO Auto-generated method stub
		return null;
	}

}
