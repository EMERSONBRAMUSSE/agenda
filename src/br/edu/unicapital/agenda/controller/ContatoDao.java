package br.edu.unicapital.agenda.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicapital.agenda.model.Contato;

public class ContatoDao {
	private Connection connection;
	
	public ContatoDao() {
		this.connection = ConnectionFactory.getConnection();
	}

	public void alterar(Contato contato) {
		String sql = "update contatos set nome = ?, telefone = ? where id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setInt(3, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(Contato contato) {
		String sql = "delete from contatos where id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adicionar(Contato contato) {
		String sql = "insert into contatos " +
				"(nome, telefone) " +
				"values (?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());


			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> getListar() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = connection.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contato contato = new Contato();

				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));

				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
