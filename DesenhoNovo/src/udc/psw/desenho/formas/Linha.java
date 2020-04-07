package udc.psw.desenho.formas;

import java.awt.Graphics;

import udc.psw.desenho.formas.manipulador.ManipuladorLinha;
import udc.psw.desenho.formas.manipulador.ManipuladorPonto;

public class Linha implements FormaGeometrica {
	
	private Ponto a;
	private Ponto b;
	
	private ManipuladorLinha manipulador = null;
	
	public Linha (Ponto a, Ponto b) {
		this.a = a.clone();
		this.b = b.clone();
	}
	
	public Linha(Linha l) {
		this.a = l.a.clone();
		this.b = l.b.clone();
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
		return 0;
	}

	@Override
	public double perimetro() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double base() {
		// TODO Auto-generated method stub
		return Math.abs(a.getX() - b.getX());
	}

	@Override
	public double altura() {
		return Math.abs(a.getY() - b.getY());
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
	public Linha clone() {
		return new Linha(this);
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
		return "Linha";
	}

	@Override
	public ManipuladorLinha getManipulador() {
		if(manipulador == null)
			manipulador = new ManipuladorLinha(this);
		return manipulador;
	}

}
