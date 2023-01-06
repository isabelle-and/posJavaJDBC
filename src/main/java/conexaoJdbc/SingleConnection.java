package conexaoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5433/posjava";
	private static String password = "vonixx@1234";
	private static String user = "postgres";
	private static Connection connection = null; //instancia objeto Connection do sql java e coloca a variável connection nula
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	private static void conectar() { //método privado conectar para ser acessível somente dentro do objeto
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); //a conexão com o BD é feita uma vez só e mantida, o que é aberto
				//e fechado depois são as sessões
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); //não salva automaticamente, é para decidir quando as operações de insert,
				//update ou remoção dos dados do BD serão gravadas efetivamente
				System.out.println("conectou");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	public static Connection getConnection() {
		return connection;
	}
}
//  objetos Connection = connection(pacote java.sql): Um objeto que Connection que implementa a interface Connection gerencia a conexão
//entre o programa Java e o banco de dados. Os objetos Connection permitem aos programas criar instruções de SQL que acessem bancos de dados.
// O programa inicializa connection com o resultado de uma chamada para o método static getConnection da classe DriverManager (pacote java.sql)
//que tenta conectar-se ao banco de daos especificado por seu URL. O método getConncetions aceita 3 argumentos (3 strings que espcifica
// a URL do banco, o nome e a senha do usuário. O método DriverManager getConnection é sobrecarregado com versões que permitem ao programa
// fornecer o nome de usuário e a senha para ganhar acesso.
// Statement para enviar instruções de SQL ao banco de dados.
