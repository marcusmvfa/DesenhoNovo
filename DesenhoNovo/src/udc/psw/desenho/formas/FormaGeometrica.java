package udc.psw.desenho.formas;

import java.awt.Graphics;

import udc.psw.desenho.formas.manipulador.ManipuladorForma;

public interface FormaGeometrica {

	Ponto centro();
	double area();
	double perimetro();
	double base();
	double altura();
	double distancia(FormaGeometrica f);
	
	String toString();
	String getNome();
	
	Ponto getEnd();
	Ponto getStart();
	
	FormaGeometrica clone();
	
	//void desenhar(Graphics g);
	
	public ManipuladorForma getManipulador();
}
