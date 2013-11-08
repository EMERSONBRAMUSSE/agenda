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
		String queryUpdateContato = "update contatos set nome = ? where id_contato = ?";
		String queryDelTel = "delete from telefones where id_contato = ?";
		String queryInsertTel = "insert into telefones (id_contato, telefone) values (?, ?)";
		try {


			PreparedStatement stmt = connection.prepareStatement(queryUpdateContato);
			stmt.setString(1, contato.getNome());
			stmt.setInt(2, contato.getId());
			stmt.execute();
			stmt.close();
			
			stmt = connection.prepareStatement(queryDelTel);
			stmt.setInt(1, contato.getId());
			stmt.execute();
			stmt.close();
			
			stmt = connection.prepareStatement(queryInsertTel);
			stmt.setInt(1, contato.getId());
			stmt.setString(2, contato.getTelefone1());
			stmt.execute();
			stmt.close();

			stmt = connection.prepareStatement(queryInsertTel);
			stmt.setInt(1, contato.getId());
			stmt.setString(2, contato.getTelefone2());
			stmt.execute();
			stmt.close();



		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(Contato contato) {
		
		String sql = "delete from contatos where id_contato = ?";
		
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

		try {
			String sql = "insert into contatos " + "(nome) " + "values (?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
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
			
			String sql2 = "insert into telefones " + "(id_contato, telefone) " + "values (? , ?)";
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt2.setInt(1, contato.getId());
			stmt2.setString(2, contato.getTelefone1());
			stmt2.execute();
			stmt2.close();
			
			PreparedStatement stmtq2 = connection.prepareStatement("select id_contato from contatos where nome = ?");
			stmtq2.setString(1, contato.getNome());
			ResultSet rs2 = stmtq2.executeQuery();
			rs2.next();
			contato.setId(rs2.getInt("id_contato"));
			rs2.close();
			stmtq2.close();
			
			String sql3 = "insert into telefones " + "(id_contato, telefone) " + "values (? , ?)";
			PreparedStatement stmt3 = connection.prepareStatement(sql3);
			stmt3.setInt(1, contato.getId());
			stmt3.setString(2, contato.getTelefone2());
			stmt3.execute();
			stmt3.close();

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

				contato.setTelefone1(rs2.getString("telefone"));
				contato.setTelefone2(rs2.getString("telefone"));

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
