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

public class DesenhosDAO {
	Connection connection = null;
	public DesenhosDAO() throws SQLException{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connection = connectionFactory.getDatabaseConnection();		
	}
	
	public List<Desenho> getAllDesenhos() throws SQLException {
		List<Desenho> list = new ArrayList<Desenho>();
		Statement stm = connection.createStatement();
		stm.execute("Select * from desenhos");
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			Desenho d = new Desenho();
			d.setID(rst.getInt("ID"));
			d.setNome(rst.getString("nome"));
			list.add(d);
//			System.out.println(id + " - " + nome);
		}
		stm.close();
		return list;
	}
	
	public int saveDesenho(String nome) throws SQLException{
		Statement stm = connection.createStatement();
		stm.execute("Insert into desenhos (nome) values ('" + nome + "')" );
		int code = stm.SUCCESS_NO_INFO;
		System.out.println(code);
		stm.close();
		return code;
	}
	public Desenho getLastItem() throws SQLException {
		Statement stm = connection.createStatement();
		stm.execute("select * from desenhos order by id desc limit 1");
		ResultSet rst = stm.getResultSet();
		Desenho d = new Desenho();
		while(rst.next()) {
			d.setID(rst.getInt("ID"));
			d.setNome(rst.getString("nome"));		
		}
		return d;
	}
}
