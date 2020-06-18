package udc.psw.desenho;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import udc.psw.desenho.database.CirculoDAO;
import udc.psw.desenho.database.DesenhosDAO;
import udc.psw.desenho.database.LinhaDAO;
import udc.psw.desenho.database.PontoDAO;
import udc.psw.desenho.database.TrianguloDAO;
import udc.psw.desenho.formas.FabricaFormas;
import udc.psw.desenho.formas.FormaGeometrica;
import udc.psw.desenho.formas.TipoPainel;
import udc.psw.desenho.gui.PainelDesenho;
import udc.psw.desenho.gui.PainelTexto;

public class Documento {
	private List<FormaGeometrica> listaFormas;
	private List<TipoPainel> listaPaineis;
	private PainelDesenho painel;
	private PainelTexto painelTexto;

	public Documento() {
		painel = null;
		painelTexto = null;
		listaFormas = new LinkedList<FormaGeometrica>();
		listaPaineis = new LinkedList<TipoPainel>();
	}

	public void setPainel(PainelDesenho painel) {
		this.painel = painel;
	}

	public void novaForma(FormaGeometrica forma) {
		listaFormas.add(forma);
		atualizarPainel(forma);
	}

	public void atualizarPainel(FormaGeometrica forma) {
//		if(painel != null)
		if(listaPaineis.size() > 0) {
			for (TipoPainel tipoPainel : listaPaineis) {
				tipoPainel.atualizar(forma);
			}
		}
			//painel.repaint();
	}

	public Iterator<FormaGeometrica> getIterator() {
		return listaFormas.iterator();
	}
	
	public void addPainel(TipoPainel painel2) {
		this.listaPaineis.add(painel2);
	}
	
	public void salvar(File file) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			
			for(FormaGeometrica f : listaFormas) {
				oos.writeObject(f);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void ler(File file) {
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			listaFormas.clear();
			FormaGeometrica formaAux = null;
			while (true) {
				formaAux = (FormaGeometrica) ois.readObject();
				listaFormas.add(formaAux);
			}
		} catch (EOFException endOfFileException) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			atualizarPainel(null);
			
	}

	public void salvarTxt(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			
			for (FormaGeometrica f : listaFormas) {
				fw.append(f.getClass().getSimpleName() + " " + f.toString() + "\n");
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void lerTxt(File file) {
		try {
			Scanner sc = new Scanner(file);
			
			listaFormas.clear();
			
			FormaGeometrica formaAux = null;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				formaAux = FabricaFormas.fabricarForma(line);
				listaFormas.add(formaAux);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		atualizarPainel(null);
	}
	
	public void salvarNoBanco(int id) throws SQLException {
		for(FormaGeometrica f: listaFormas) {
			if(f.getNome() == "Ponto") {
				PontoDAO dao = new PontoDAO();
				dao.savePontos(f, id);
			}
			else if(f.getNome() == "Linha") {
				LinhaDAO dao = new LinhaDAO();
				dao.saveLinhas(f, id);
			}
			else if(f.getNome() == "Triangulo") {
				TrianguloDAO dao = new TrianguloDAO();
				dao.saveTriangulos(f, id);
			}
			else if(f.getNome() == "Circulo") {
				CirculoDAO dao = new CirculoDAO();
				dao.saveCirculos(f, id);
			}
		}
	}
	
	public void lerBanco(int id) throws SQLException {
		listaFormas.clear();
		PontoDAO pontoDAO = new PontoDAO();
		LinhaDAO linhaDAO = new LinhaDAO();
		TrianguloDAO trianguloDAO = new TrianguloDAO();
		CirculoDAO circuloDAO = new CirculoDAO();
		for(FormaGeometrica f : pontoDAO.getAllPontosById(id)) {
			listaFormas.add(f);
		}
		for(FormaGeometrica f : linhaDAO.getAllLinhasById(id)) {
			listaFormas.add(f);
		}
		for(FormaGeometrica f : trianguloDAO.getAllTriangulosById(id)) {
			listaFormas.add(f);
		}
		for(FormaGeometrica f : circuloDAO.getAllCirculosById(id)) {
			listaFormas.add(f);
		}
		for(FormaGeometrica f : listaFormas) {
			System.out.println(f);
		}
	}

}
