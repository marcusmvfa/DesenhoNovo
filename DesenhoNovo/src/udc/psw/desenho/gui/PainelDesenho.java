package udc.psw.desenho.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import udc.psw.desenho.formas.FormaGeometrica;
import udc.psw.desenho.formas.Linha;
import udc.psw.desenho.formas.Circulo;
import udc.psw.desenho.formas.Ponto;
import udc.psw.desenho.formas.Retangulo;
import udc.psw.desenho.formas.Triangulo;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	private JLabel status;
	
	private FormaGeometrica formaAtual;
	
	private int estado;
	
	private List<FormaGeometrica> listaFormas;
	
	public PainelDesenho(JLabel status) {
		this.status = status;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		formaAtual = null;
		listaFormas = new LinkedList<FormaGeometrica>();
	}
	
	public void formaAtual(FormaGeometrica forma) {
		formaAtual = forma;
		estado = 0;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(formaAtual != null)
			formaAtual.getManipulador().desenhar(g);
		
		for(FormaGeometrica f : listaFormas) {
			f.getManipulador().desenhar(g);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		String msg = String.format("Mouse Arrastado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em (" + formaAtual + ")"; 
		status.setText(msg);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (formaAtual != null) {
			formaAtual.getManipulador().mover(e.getX(), e.getY());
				listaFormas.add(formaAtual);
			
			repaint();
		}
		if (formaAtual != null && formaAtual.getClass().equals(Linha.class)) {
			if(estado == 1) {
				((Linha)formaAtual).setB(new Ponto(e.getX(), e.getY()));
				repaint();
			}
		}
		else if (formaAtual != null && formaAtual.getClass().equals(Triangulo.class)) {
			switch(estado) {
			case 1:
				((Triangulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
				((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
				repaint();
			break;
			case 2:
				((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
				repaint();
			break;
			}
		}
		else if (formaAtual != null && formaAtual.getClass().equals(Circulo.class)) {
			if(estado == 1) {
				((Circulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
				repaint();
			}
		}
		else if (formaAtual != null && formaAtual.getClass().equals(Retangulo.class)) {
			switch(estado) {
			case 1:
				((Retangulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
				((Retangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
				((Retangulo)formaAtual).setD(new Ponto(e.getX(), e.getY()));
				repaint();
				break;
				
			case 2:
				((Retangulo)formaAtual).setC(new Ponto(e.getX() - ((Retangulo)formaAtual).getA().getX(), e.getY() - ((Retangulo)formaAtual).getA().getY()));
				((Retangulo)formaAtual).setD(new Ponto(e.getX() - 4, e.getY() - 4));
				repaint();
				break;
			}
			
		}
		
		 String msg = String.format("Mouse Movido em (%d; %d)", e.getX(), e.getY());
		 if(formaAtual != null)
				msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(formaAtual != null) {
			if(formaAtual.getManipulador().clicar(e.getX(), e.getY())) {
				listaFormas.add(formaAtual);
				formaAtual = formaAtual.clone();
			}
			repaint();
		}
		
		String msg = String.format("Mouse Clicado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		String msg = String.format("Mouse Entrou no Painel em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		String msg = String.format("Mouse Saiu do Painel em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		String msg = String.format("Mouse Pressionado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String msg = String.format("Mouse Soltado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
		
	}


}
