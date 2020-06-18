package udc.psw.desenho.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import udc.psw.desenho.formas.Desenho;
import udc.psw.desenho.formas.FormaGeometrica;
import udc.psw.desenho.formas.Linha;
import udc.psw.desenho.formas.Ponto;
import udc.psw.desenho.formas.Triangulo;

public class TrianguloDAO {
	Connection connection = null;
	public TrianguloDAO() throws SQLException{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connection = connectionFactory.getDatabaseConnection();		
	}
	
	public void getAllTriangulos() throws SQLException {
		Statement stm = connection.createStatement();
		stm.execute("Select * from triangulos");
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			Integer id = rst.getInt("ID");
			Integer x1 = rst.getInt("x1");
			Integer y1 = rst.getInt("y1");
			Integer x2 = rst.getInt("x2");
			Integer y2 = rst.getInt("y2");
			Integer x3 = rst.getInt("x3");
			Integer y3 = rst.getInt("y3");
			
			System.out.println(id + " [(" + x1 + ", " + y1 + "),(" + x2 + ", " + y2 +"),(" + x3 + ", " + y3 + ")]");
//			System.out.println(nome);
//			System.out.println(descricao);
		}
	}
	
	public void saveTriangulos(FormaGeometrica p, int idDesenho) throws SQLException{
		Statement stm = connection.createStatement();
		if(p.getID() == null) {
			stm.execute("Insert into triangulos (x1, y1, x2, y2, x3, y3, idDesenho) values (" 
					+ p.toStringBanco() + ", " + idDesenho + ");");	
		}
	}
	
	public List<FormaGeometrica> getAllTriangulosById (int id) throws SQLException{
		List<FormaGeometrica> list = new ArrayList<FormaGeometrica>();
		
		
		Statement stm = connection.createStatement();
		stm.execute("select * from triangulos where idDesenho = " + id);
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {
			Triangulo triangulo = new Triangulo();
			Ponto ponto1 = new Ponto();
			Ponto ponto2 = new Ponto();
			Ponto ponto3 = new Ponto();
			triangulo.setID(rst.getInt("ID"));
			
			ponto1.setX(rst.getInt("x1"));
			ponto1.setY(rst.getInt("y1"));
			triangulo.setA(ponto1);
			
			ponto2.setX(rst.getInt("x2"));
			ponto2.setY(rst.getInt("y2"));
			triangulo.setB(ponto2);
			
			ponto3.setX(rst.getInt("x3"));
			ponto3.setY(rst.getInt("y3"));
			triangulo.setC(ponto3);
			
			list.add(triangulo);
		}
		
		return list;
	}

}
