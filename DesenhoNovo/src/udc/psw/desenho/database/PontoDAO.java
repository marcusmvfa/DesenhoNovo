package udc.psw.desenho.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import udc.psw.desenho.formas.Desenho;
import udc.psw.desenho.formas.FormaGeometrica;
import udc.psw.desenho.formas.Ponto;

public class PontoDAO {
	Connection connection = null;
	public PontoDAO() throws SQLException{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connection = connectionFactory.getDatabaseConnection();		
	}
	
	public void getAllPontos() throws SQLException {
		Statement stm = connection.createStatement();
		stm.execute("Select * from pontos");
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			Integer id = rst.getInt("ID");
			Integer nome = rst.getInt("X");
			Integer descricao = rst.getInt("Y");
			System.out.println(id + " (" + nome + ", " + descricao + ")");
//			System.out.println(nome);
//			System.out.println(descricao);
		}
	}
	
	public void savePontos(FormaGeometrica p, int idDesenho) throws SQLException{
		Statement stm = connection.createStatement();
		if(p.getID() == null) {
			stm.execute("Insert into pontos (X, Y, idDesenho) values " + p.toStringBanco() + idDesenho + ");");			
		}
	}
	
	public List<FormaGeometrica> getAllPontosById (int id) throws SQLException{
		List<FormaGeometrica> list = new ArrayList<FormaGeometrica>();
		
		
		Statement stm = connection.createStatement();
		stm.execute("select * from pontos where idDesenho = " + id);
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {	
			Ponto ponto = new Ponto();
			ponto.setID(rst.getInt("ID"));
			ponto.setX(rst.getInt("x"));
			ponto.setY(rst.getInt("y"));
			list.add(ponto);
		}
		
		return list;
	}

}
