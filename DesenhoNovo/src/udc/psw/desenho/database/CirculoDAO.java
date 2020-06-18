package udc.psw.desenho.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import udc.psw.desenho.formas.Circulo;
import udc.psw.desenho.formas.Desenho;
import udc.psw.desenho.formas.FormaGeometrica;
import udc.psw.desenho.formas.Linha;
import udc.psw.desenho.formas.Ponto;

public class CirculoDAO {
	Connection connection = null;
	public CirculoDAO() throws SQLException{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connection = connectionFactory.getDatabaseConnection();		
	}
	
	public void getAllCirculo() throws SQLException {
		Statement stm = connection.createStatement();
		stm.execute("Select * from circulos");
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			Integer id = rst.getInt("ID");
			Integer x1 = rst.getInt("x1");
			Integer y1 = rst.getInt("y1");
			Integer x2 = rst.getInt("x2");
			Integer y2 = rst.getInt("y2");
			
			System.out.println(id + " - (" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ")");
//			System.out.println(nome);
//			System.out.println(descricao);
		}
	}
	
	public void saveCirculos(FormaGeometrica p, int idDesenho) throws SQLException{
		Statement stm = connection.createStatement();
		if(p.getID() == null) {
			stm.execute("Insert into circulos (x1, y1, x2, y2, idDesenho) values (" 
					+ p.toStringBanco() + ", " + idDesenho + ");");			
		}
	}
	
	public List<FormaGeometrica> getAllCirculosById (int id) throws SQLException{
		List<FormaGeometrica> list = new ArrayList<FormaGeometrica>();
		
		
		Statement stm = connection.createStatement();
		stm.execute("select * from circulos where idDesenho = " + id);
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {
			Circulo circulo = new Circulo();
			Ponto ponto1 = new Ponto();
			Ponto ponto2 = new Ponto();
			circulo.setID(rst.getInt("ID"));
			
			ponto1.setX(rst.getInt("x1"));
			ponto1.setY(rst.getInt("y1"));
			circulo.setA(ponto1);
			
			ponto2.setX(rst.getInt("x2"));
			ponto2.setY(rst.getInt("y2"));
			circulo.setB(ponto2);
			
			list.add(circulo);
		}
		
		return list;
	}

}
