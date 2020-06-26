package udc.psw.desenho.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import udc.psw.desenho.AplicacaoDesenho;
import udc.psw.desenho.formas.FormaGeometrica;

public class Server implements Runnable {

	private ObjectOutputStream saida;
	private ObjectInputStream entrada;
	private Socket cliente;

	public Server() {
//		try {
//		// Instancia o ServerSocket ouvindo a porta 12345
//	      ServerSocket servidor = new ServerSocket(12345);
//	      System.out.println("Servidor ouvindo a porta 12345");
//	      while(true) {
//	        // o método accept() bloqueia a execução até que
//	        // o servidor receba um pedido de conexão
//	        Socket cliente = servidor.accept();
//	        System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
//	        ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
//	        saida.flush();
//	        
//	        List<String> list = new ArrayList<String>();
//	        list.add("teste 1");
//	        list.add("teste 2");
//	        list.add("teste 3");
//	        
//	        //saida.writeObject(new Date());
//	        saida.writeObject(list);
//	        //saida.close();
//	        //cliente.close();
//	      }
//	    }
//	    catch(Exception e) {
//	       System.out.println("Erro: " + e.getMessage());
//	    }
	}

	public static void main(String[] args) {

		try {
			// Instancia o ServerSocket ouvindo a porta 12345
//			ServerSocket servidor = new ServerSocket(12345);
//			System.out.println("Servidor ouvindo a porta 12345");
//			while (true) {
//				// o método accept() bloqueia a execução até que
//				// o servidor receba um pedido de conexão
//				Socket cliente = servidor.accept();
//				System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
//				saida = new ObjectOutputStream(cliente.getOutputStream());
//				saida.flush();
//
//				List<String> list = new ArrayList<String>();
//				list.add("teste 1");
//				list.add("teste 2");
//				list.add("teste 3");
//
//				// saida.writeObject(new Date());
//				saida.writeObject(list);
//				saida.close();
//				// cliente.close();
			// }
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void sendData(ObjectOutputStream saida) {
		try {
			saida.flush();

			List<String> list = new ArrayList<String>();
			list.add("teste 1");
			list.add("teste 2");
			list.add("teste 3");

			// saida.writeObject(new Date());
			saida.writeObject(list);
			saida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			// Instancia o ServerSocket ouvindo a porta 12345
			ServerSocket servidor = new ServerSocket(12345);
			System.out.println("Servidor ouvindo a porta 12345");
			// o método accept() bloqueia a execução até que
			// o servidor receba um pedido de conexão
			while(true) {
				cliente = servidor.accept();
				System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
				saida = new ObjectOutputStream(cliente.getOutputStream());
				saida.flush();
				
				entrada = new ObjectInputStream(cliente.getInputStream());
				do {
					FormaGeometrica forma = (FormaGeometrica) entrada.readObject();		//Quando recebe o objeto do Cliente atualiza o Server
					if (forma != null) {
						AplicacaoDesenho.getAplicacao().getDocumento().novaFormaServer(forma);
						System.out.println(forma);
						forma = null;
					}
				} while (true);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		} 

	}

	public void atualizaCliente(FormaGeometrica f) {
		try {
			saida.flush();
			saida.writeObject(f);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
