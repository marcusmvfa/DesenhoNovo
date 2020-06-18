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

public class LinhaDAO {
	Connection connection = null;
	public LinhaDAO() throws SQLException{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connection = connectionFactory.getDatabaseConnection();		
	}
	
	public void getAllLinhas() throws SQLException {
		Statement stm = connection.createStatement();
		stm.execute("Select * from linhas");
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			Integer id = rst.getInt("ID");
			Integer x1 = rst.getInt("x1");
			Integer y1 = rst.getInt("y1");
			Integer x2 = rst.getInt("x2");
			Integer y2 = rst.getInt("y2");
			
			System.out.println(id + " [(" + x1 + ", " + y1 + "),(" + x2 + ", " + "y2)]");
//			System.out.println(nome);
//			System.out.println(descricao);
		}
	}
	
	public void saveLinhas(FormaGeometrica p, int idDesenho) throws SQLException{
		Statement stm = connection.createStatement();
		if(p.getID() == null) {
			stm.execute("Insert into linhas (x1, y1, x2, y2, idDesenho) values (" 
					+ p.toStringBanco() + ", " + idDesenho + ");");			
		}
	}
	
	public List<FormaGeometrica> getAllLinhasById (int id) throws SQLException{
		List<FormaGeometrica> list = new ArrayList<FormaGeometrica>();
		
		
		Statement stm = connection.createStatement();
		stm.execute("select * from linhas where idDesenho = " + id);
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {
			Linha linha = new Linha();
			Ponto ponto1 = new Ponto();
			Ponto ponto2 = new Ponto();
			linha.setID(rst.getInt("ID"));
			
			ponto1.setX(rst.getInt("x1"));
			ponto1.setY(rst.getInt("y1"));
			linha.setA(ponto1);
			
			ponto2.setX(rst.getInt("x2"));
			ponto2.setY(rst.getInt("y2"));
			linha.setB(ponto2);
			
			list.add(linha);
		}
		
		return list;
	}

}
