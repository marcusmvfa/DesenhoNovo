package udc.psw.desenho.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

import javax.swing.JLabel;
import javax.swing.JPanel;

import udc.psw.desenho.formas.FormaGeometrica;
import udc.psw.desenho.formas.Linha;
import udc.psw.desenho.AplicacaoDesenho;
import udc.psw.desenho.formas.Circulo;
import udc.psw.desenho.formas.FabricaFormas;
import udc.psw.desenho.formas.Ponto;
import udc.psw.desenho.formas.Retangulo;
import udc.psw.desenho.formas.TipoPainel;
import udc.psw.desenho.formas.Triangulo;

public class PainelDesenho extends JPanel implements TipoPainel, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private JLabel status;

	private FormaGeometrica formaAtual;

	private int estado;

	

	public PainelDesenho(JLabel status) {
		this.status = status;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		formaAtual = null;
	}

	public void formaAtual(FormaGeometrica forma) {
		formaAtual = forma;
		estado = 0;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (formaAtual != null)
			formaAtual.getManipulador().desenhar(g);

		Iterator<FormaGeometrica> it;
		try {
			it = AplicacaoDesenho.getAplicacao().getDocumento().getIterator();
			while(it.hasNext()) {
				it.next().getManipulador().desenhar(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		String msg = String.format("Mouse Arrastado em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em (" + formaAtual + ")";
		status.setText(msg);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (formaAtual != null) {
			formaAtual.getManipulador().mover(e.getX(), e.getY());
			

			repaint();
		}
		if (formaAtual != null && formaAtual.getClass().equals(Linha.class)) {
			if (estado == 1) {
				((Linha) formaAtual).setB(new Ponto(e.getX(), e.getY()));
				repaint();
			}
		} else if (formaAtual != null && formaAtual.getClass().equals(Triangulo.class)) {
			switch (estado) {
			case 1:
				((Triangulo) formaAtual).setB(new Ponto(e.getX(), e.getY()));
				((Triangulo) formaAtual).setC(new Ponto(e.getX(), e.getY()));
				repaint();
				break;
			case 2:
				((Triangulo) formaAtual).setC(new Ponto(e.getX(), e.getY()));
				repaint();
				break;
			}
		} else if (formaAtual != null && formaAtual.getClass().equals(Circulo.class)) {
			if (estado == 1) {
				((Circulo) formaAtual).setB(new Ponto(e.getX(), e.getY()));
				repaint();
			}
		} else if (formaAtual != null && formaAtual.getClass().equals(Retangulo.class)) {
			switch (estado) {
			case 1:
				((Retangulo) formaAtual).setB(new Ponto(e.getX(), e.getY()));
				((Retangulo) formaAtual).setC(new Ponto(e.getX(), e.getY()));
				((Retangulo) formaAtual).setD(new Ponto(e.getX(), e.getY()));
				repaint();
				break;

			case 2:
				((Retangulo) formaAtual).setC(new Ponto(e.getX() - ((Retangulo) formaAtual).getA().getX(),
						e.getY() - ((Retangulo) formaAtual).getA().getY()));
				((Retangulo) formaAtual).setD(new Ponto(e.getX() - 4, e.getY() - 4));
				repaint();
				break;
			}

		}

		
		String msg = String.format("Mouse Movido em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (formaAtual != null) {
			if (formaAtual.getManipulador().clicar(e.getX(), e.getY())) {
				try {
					AplicacaoDesenho.getAplicacao().getDocumento().novaForma(formaAtual);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				formaAtual = formaAtual.clone();
			}
			repaint();
		}

		String msg = String.format("Mouse Clicado em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		String msg = String.format("Mouse Entrou no Painel em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		String msg = String.format("Mouse Saiu do Painel em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		String msg = String.format("Mouse Pressionado em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String msg = String.format("Mouse Soltado em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void atualizar(FormaGeometrica forma) {
		this.repaint();		
	}
}
