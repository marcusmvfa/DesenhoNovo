package udc.psw.desenho.formas.manipulador;

import java.awt.Graphics;

import udc.psw.desenho.formas.Ponto;

public class ManipuladorPonto implements ManipuladorForma{

	Ponto ponto;
	
	public ManipuladorPonto(Ponto p) {
		ponto = p;
	}

	@Override
	public void desenhar(Graphics g) {
		g.drawOval(ponto.getX(), ponto.getY(), 4, 4);
		
	}

	@Override
	public void arrastar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean clicar(int x, int y) {
		ponto.setX(x);
		ponto.setY(y);
		return true;
	}

}
