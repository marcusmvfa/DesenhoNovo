package udc.psw.desenho.formas.manipulador;

import java.awt.Graphics;
import java.lang.reflect.Array;

import udc.psw.desenho.formas.Ponto;
import udc.psw.desenho.formas.Retangulo;

public class ManipuladorRetangulo implements ManipuladorForma {
	private Retangulo retangulo;
	private int estado;
	
	public ManipuladorRetangulo(Retangulo r) {
		retangulo = r;
		estado = 0;
	}
	
	public void press(int x, int y) {
		Ponto p = new Ponto(x, y);
		try {
			retangulo.setA(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p = new Ponto(x, y);
		try {
			retangulo.setB(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void release(int x, int y) {
		Ponto p = new Ponto(x, y);
		try {
			retangulo.setB(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void drag(int x, int y) {
		Ponto p = new Ponto(x, y);
		try {
			retangulo.setB(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void desenhar(Graphics g) {
		int xa = 0;
		try {
			xa = (int) retangulo.getA().getX();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int xb = 0;
		try {
			xb = (int) retangulo.getB().getX();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ya = 0;
		try {
			ya = (int) retangulo.getA().getY();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int yb = 0;
		try {
			yb = (int) retangulo.getB().getY();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(estado == 1) {
			g.drawLine(retangulo.getA().getX(), retangulo.getA().getY(), retangulo.getB().getX(), retangulo.getB().getY());
		}
		else if(estado == 2) {
			int[] xpoint = new int[5];
			xpoint[0] = retangulo.getA().getX();
			xpoint[1] = retangulo.getB().getX();
			xpoint[2] = retangulo.getC().getX();
			xpoint[3] = retangulo.getD().getX();
			xpoint[4] = retangulo.getA().getX();
			
			int[] ypoint = new int[5];
			ypoint[0] = retangulo.getA().getY();
			ypoint[1] = retangulo.getB().getY();
			ypoint[2] = retangulo.getC().getY(); 
			ypoint[3] = retangulo.getD().getY();
			ypoint[4] = retangulo.getA().getY();
			
			g.drawPolyline(xpoint, ypoint, 5);
			//g.drawRect(xa < xb ? xa : xb, ya < yb ? ya : yb, 
			//		(int) retangulo.base(), (int) retangulo.altura() );
		}
		
	}


	@Override
	public void arrastar(int x, int y) {
		Ponto p = new Ponto(x, y);
		retangulo.setB(p);
		
	}


	@Override
	public void mover(int x, int y) {
		switch(estado) {
		case 0:
			retangulo.setA(new Ponto(x, y));
			retangulo.setB(new Ponto(x, y));
			retangulo.setC(new Ponto(x, y));
			retangulo.setD(new Ponto(x, y));
			//estado = 1;
		case 1:
			retangulo.setB(new Ponto(x, y));
			//retangulo.setC(new Ponto(x, y));
			//retangulo.setD(new Ponto(x, y));
			//estado = 2;
		case 2:
			Ponto p = new Ponto(x,y);
			retangulo.setC(new Ponto((retangulo.getB().getX() * 2) + p.getX() , ((int)retangulo.altura() * 2) ));
			retangulo.setD(new Ponto((p.getX() + retangulo.getA().getX()) * 2 ,  (p.getY() + retangulo.getA().getY()) * 2));
			
			//estado = 0;
		}
		
	}


	@Override
	public boolean clicar(int x, int y) {
		switch(estado) {
		case 0:
			retangulo.setA(new Ponto(x, y));
			retangulo.setB(new Ponto(x, y));
			retangulo.setC(new Ponto(x, y));
			retangulo.setD(new Ponto(x, y));
			estado = 1;
			return false;
		case 1:
			retangulo.setB(new Ponto(x, y));
			retangulo.setC(new Ponto(x, y));
			retangulo.setD(new Ponto(x, y));
			estado = 2;
			return false;
		case 2:
			Ponto p = new Ponto(x,y);
			//p = retangulo.centro();
			
			retangulo.setC(new Ponto(p.getX() , p.getY() - retangulo.getA().getY()));
			retangulo.setD(new Ponto(p.getX() , p.getY() - retangulo.getB().getY() ));
			//estado = 0;
			return true;
		}
		return false;
	}

}
