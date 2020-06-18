package udc.psw.desenho.formas;

import java.awt.Graphics;
import java.io.Serializable;

import udc.psw.desenho.formas.manipulador.ManipuladorForma;

public interface FormaGeometrica extends Serializable{

	Ponto centro();
	double area();
	double perimetro();
	double base();
	double altura();
	double distancia(FormaGeometrica f);
	
	String toString();
	String toStringBanco();
	String getNome();
	Integer getID();
	
	Ponto getEnd();
	Ponto getStart();
	
	FormaGeometrica clone();
	
	//void desenhar(Graphics g);
	
	public ManipuladorForma getManipulador();
}
