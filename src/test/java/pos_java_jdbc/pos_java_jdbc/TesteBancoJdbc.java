package pos_java_jdbc.pos_java_jdbc;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.omg.CORBA.PUBLIC_MEMBER;

import conexaoJdbc.SingleConnection;
import dao.UserPosDAO;
import junit.framework.TestCase;
import model.Userposjava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() { // fazendo um método em java para chamar o SingleConnection // INSERT
		UserPosDAO userPosDAO = new UserPosDAO(); // instancia o objeto do DAO com o userPosDAO = a um new objeto,
		// precisa do objeto de modelo userposjava
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("Isaa teste2");
		userposjava.setEmail("isabelle.andreetta@gmail.com");

		userPosDAO.salvar(userposjava); // passando os dados fixos-estáticos do salvar que foi contruído no UserPosDAO
	}

	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();

			for (Userposjava userposjava : list) {

			}

		} catch (Exception e) {
		
			e.printStackTrace();
		}

	}

	@Test
	public void initBuscar() {

		UserPosDAO dao = new UserPosDAO();
						
		try {
			Userposjava userposjava = dao.buscar(6L);
			
			System.out.println(userposjava);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	@Test
	public void initAtualizar () {
		
		try {
			UserPosDAO dao = new UserPosDAO();
			
			Userposjava objetoBanco = dao.buscar(5L);
			
			objetoBanco.setNome("nome alterado com método atualizar");
			
			dao.atualizar(objetoBanco);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	@Test
	public void initDeletar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			
			 dao.deletar(6L);
			
		} catch (Exception e) {
			e.printStackTrace();
					
		}
	}
}
