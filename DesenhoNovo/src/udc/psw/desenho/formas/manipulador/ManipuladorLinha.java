package udc.psw.desenho.formas.manipulador;

import java.awt.Graphics;

import udc.psw.desenho.formas.Linha;
import udc.psw.desenho.formas.Ponto;

public class ManipuladorLinha implements ManipuladorForma{

	private Linha linha;
	private int estado;
	
	public ManipuladorLinha (Linha l) {
		linha = l;
		estado = 0;
	}
	
	@Override
	public void desenhar(Graphics g) {
		g.drawLine(linha.getA().getX(), linha.getA().getY(), linha.getB().getX(), linha.getB().getY());
		
	}

	@Override
	public void arrastar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover(int x, int y) {
		if(estado == 1) {
			linha.setB(new Ponto(x, y));
		}
		
	}

	@Override
	public boolean clicar(int x, int y) {
		switch(estado) {
		case 0:
			linha.setA(new Ponto(x, y));
			linha.setB(new Ponto(x, y));
			estado = 1;
			return false;
		case 1:
			linha.setB(new Ponto(x, y));
			estado = 0;
			return true;
		}
		return false;
	}

}
