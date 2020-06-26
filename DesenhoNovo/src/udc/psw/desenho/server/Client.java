package udc.psw.desenho.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import udc.psw.desenho.AplicacaoDesenho;
import udc.psw.desenho.formas.FormaGeometrica;

public class Client implements Runnable {

	private ObjectInputStream entrada;
	private ObjectOutputStream saida;

	public Client() {
	}

	public static void main(String[] args) {
	}

	@Override
	public void run() {

		Socket cliente;
		try {
			cliente = new Socket("127.0.0.1", 12345);

			entrada = new ObjectInputStream(cliente.getInputStream());
			saida = new ObjectOutputStream(cliente.getOutputStream());
			saida.flush();
			do {
				FormaGeometrica forma = (FormaGeometrica) entrada.readObject();		//Quando recebe o objeto do Servidor atualiza o Cliente
				if (forma != null) {
					AplicacaoDesenho.getAplicacao().getDocumento().novaFormaClient(forma);
					System.out.println(forma);
					forma = null;
				}
			} while (true);
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
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaServer(FormaGeometrica f) {
		try {
			saida.flush();
			saida.writeObject(f);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
