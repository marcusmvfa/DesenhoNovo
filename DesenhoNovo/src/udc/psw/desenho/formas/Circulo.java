package udc.psw.desenho.formas;

import java.awt.Graphics;

import udc.psw.desenho.formas.manipulador.ManipuladorCirculo;

import udc.psw.desenho.formas.manipulador.ManipuladorPonto;

public class Circulo implements FormaGeometrica {
	
	private Ponto a;
	private Ponto b;
	
	private ManipuladorCirculo manipulador = null;
	
	public Circulo (Ponto a, Ponto b) {
		this.a = a.clone();
		this.b = b.clone();
	}
	
	public Circulo(Circulo c) {
		this.a = c.a.clone();
		this.b = c.b.clone();
	}
	
	public void setA(Ponto a) {
		this.a = a.clone();
	}
	
	public void setB(Ponto b) {
		this.b = b.clone();
	}

	public Ponto getA() {
		return a;
	}

	public Ponto getB() {
		return b;
	}

	@Override
	public Ponto centro() {
		return new Ponto((a.getX()+b.getX())/2, (a.getY()+b.getY())/2);
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return Math.PI * (Raio() * Raio());
	}

	@Override
	public double perimetro() {
		// TODO Auto-generated method stub
		return Math.PI * Raio();
	}

	@Override
	public double base() {
		// TODO Auto-generated method stub
		return 2 * Math.abs(a.getX() - b.getX());
	}

	@Override
	public double altura() {
		return Math.abs((a.getY() - b.getY()) + (a.getX() - b.getX()) /2);
	}
	
	public double Raio() {
		return Math.PI * base();
	}

	@Override
	public Ponto getEnd() {
		int x;
		int y;
		if(a.getX() > b.getX())
			x = a.getX();
		else
			x = b.getX();
		
		if(a.getY() > b.getY())
			y = a.getY();
		else
			y = b.getY();
		
		//if(a.getX() < b.getX() && a.getY() < b.getY())
			//return new Ponto(a.getX(), a.getY());
		
		return new Ponto(x, y);
	}

	@Override
	public Ponto getStart() {
		int x;
		int y;
		if(a.getX() < b.getX())
			x = a.getX();
		else
			x = b.getX();
		
		if(a.getY() < b.getY())
			y = a.getY();
		else
			y = b.getY();
		
		//if(a.getX() < b.getX() && a.getY() < b.getY())
			//return new Ponto(a.getX(), a.getY());
		
		return new Ponto(x, y);
	}

	@Override
	public Circulo clone() {
		return new Circulo(this);
	}

	@Override
	public double distancia(FormaGeometrica f) {
		Ponto cf = f.centro();
		Ponto cl = centro();
		int dx = cl.getX() - cf.getX();
		int dy = cl.getY() - cf.getY();
		
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	
	@Override
	public String toString() {
		return String.format("(%s, %s)", a, b);
	}

	@Override
	public String getNome() {
		return "Circulo";
	}

	@Override
	public ManipuladorCirculo getManipulador() {
		if(manipulador == null)
			manipulador = new ManipuladorCirculo(this);
		return manipulador;
	}

}
