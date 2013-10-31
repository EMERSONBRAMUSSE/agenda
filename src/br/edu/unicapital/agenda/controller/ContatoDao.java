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
	private PreparedStatement stmt;
	private PreparedStatement stmt2;
	private String sql;
	private String sql2;

	public ContatoDao() {
		this.connection = ConnectionFactory.getConnection();
	}

	public void alterar(Contato contato) {
		sql = "update contatos set nome = ?";
		sql2 = "update telefones set telefone= ?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());

			stmt.execute();
			stmt.close();

			stmt2 = connection.prepareStatement(sql2);
			stmt2.setString(1, contato.getTelefone());

			stmt2.execute();
			stmt2.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(Contato contato) {
		sql = "delete from contatos where id = ?";
		sql2 = "delete from telefones where id = ? , ?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, contato.getId());
			stmt.execute();
			stmt.close();

			stmt2 = connection.prepareStatement(sql2);
			stmt2.setInt(1, contato.getId());
			stmt2.setString(2, contato.getTelefone());
			stmt2.execute();
			stmt2.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adicionar(Contato contato) {

		try {
			sql = "insert into contatos " + "(nome) " + "values (?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.execute();
			stmt.close();
			
			PreparedStatement stmtq = connection.prepareStatement("select id_contato from contatos where nome = ?");
			stmtq.setString(1, contato.getNome());
			ResultSet rs = stmtq.executeQuery();
			rs.next();
			contato.setId(rs.getInt("id_contato"));
			rs.close();
			stmtq.close();
			
			sql2 = "insert into telefones " + "(id_contato, telefone) " + "values (? , ?)";
			stmt2 = connection.prepareStatement(sql2);
			stmt2.setInt(1, contato.getId());
			stmt2.setString(2, contato.getTelefone());
			stmt2.execute();
			stmt2.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		

	}

	public List<Contato> getListar() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = connection.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();
			PreparedStatement stmt2 = connection.prepareStatement("select * from telefones");
			ResultSet rs2 = stmt2.executeQuery();
			while (rs.next() && rs2.next()) {
				Contato contato = new Contato();

				contato.setId(rs.getInt("id_contato"));
				contato.setNome(rs.getString("nome"));

				contato.setTelefone(rs2.getString("telefone"));

				contatos.add(contato);
			}
			rs2.close();
			stmt2.close();

			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
