package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoJdbc.SingleConnection;
import model.Userposjava;

//criando a parte de persistência
public class UserPosDAO {
	
	 //estabelecer a conexão, chama o Connection do sql
	private Connection connection;
	
	//um construtor que vai injetar para dentro do objeto connection o SingleConnections
	public UserPosDAO() { 
		connection = SingleConnection.getConnection();
		}
	//metodo que recebe o Userposjava com os dados
	public void salvar (Userposjava userposjava) {
		try {
		String sql = "INSERT INTO userposjava (nome, email) VALUES (?,?)"; //parametros com interrogação que vai ser resgatado do objeto
		PreparedStatement insert = connection.prepareStatement(sql);//instancia o PreparedStatement que irá fazer o insert/dentro do objeto connection pega uma instrução prepared passando o sql como parâmetro
		insert.setString(1, userposjava.getNome());
		insert.setString(2, userposjava.getEmail());
		insert.execute();
		connection.commit(); //salvar no banco
		
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		}
	
	public List<Userposjava> listar() throws Exception{   //método que retorna uma lista do tipo Userposjava
		List<Userposjava> list = new ArrayList<Userposjava>();//instancia a lista e retorna a lista
		
		String sql = "SELECT * FROM userposjava";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
			
			list.add(userposjava);
		}
		
		return list;
	}
	
	public Userposjava buscar (Long id) throws Exception{   //método que retorna uma lista do tipo Userposjava
		Userposjava retorno = new Userposjava();//instancia a lista e retorna a lista
		
		String sql = "SELECT * FROM userposjava WHERE id = " + id;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {   //retorna um ou nenhum
			
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
			
			}
		
		return retorno;
	}
	
	public void atualizar (Userposjava userposjava) {
		
		try {
			String sql = "update userposjava set nome = 'nome atualizado' where id = " + userposjava.getId();
			
			PreparedStatement statement = connection.prepareStatement(sql);
						statement.setString(1, userposjava.getNome());
			
			statement.execute();
			connection.commit();
						
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
	}
	public void deletar(Long id) {
		try {
			String sql = "DELETE FROM userposjava WHERE id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
	
	//comentario


