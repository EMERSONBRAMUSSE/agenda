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
		String sql = "update contatos set nome = ?";
		String sql2= "update telefones set telefone= ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			
			stmt.execute();
			stmt.close();
			
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt2.setString(1, contato.getTelefone());
			
			stmt2.execute();
			stmt2.close();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(Contato contato) {
		String sql = "delete from contatos where id = ?";
		String sql2 = "delete from telefones where id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, contato.getId());
			stmt.execute();
			stmt.close();
			
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt2.setString(1, contato.getTelefone());
			stmt2.execute();
			stmt2.close();
			
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
			
			String sql2= "insert into telefones " + "(telefone) " + "values (?)";
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt2.setString(1, contato.getTelefone());
			
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
