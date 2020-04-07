package udc.psw.desenho.formas.manipulador;

import java.awt.Graphics;

import udc.psw.desenho.formas.Circulo;
import udc.psw.desenho.formas.Linha;
import udc.psw.desenho.formas.Ponto;

public class ManipuladorCirculo implements ManipuladorForma{

	private Circulo circulo;
	private int estado;
	
	public ManipuladorCirculo (Circulo c) {
		circulo = c;
		estado = 0;
	}
	
	@Override
	public void desenhar(Graphics g) {
		g.drawOval(circulo.getA().getX(), circulo.getA().getY(),
				(int) circulo.altura(), (int) circulo.altura());
		
	}

	@Override
	public void arrastar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover(int x, int y) {
		if(estado == 1) {
			circulo.setB(new Ponto(x, y));
		}
		
	}

	@Override
	public boolean clicar(int x, int y) {
		switch(estado) {
		case 0:
			circulo.setA(new Ponto(x, y));
			//circulo.setB(new Ponto(x, y));
			estado = 1;
			return false;
		case 1:
			circulo.setB(new Ponto(x, y));
			estado = 0;
			return true;
		}
		return false;
	}

}
