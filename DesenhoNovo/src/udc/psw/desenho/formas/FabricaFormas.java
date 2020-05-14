package udc.psw.desenho.formas;

public class FabricaFormas {

	public static FormaGeometrica fabricarForma(String forma) {
		int i = forma.indexOf(' ');
		String nome = forma.substring(0, i);
		FormaGeometrica formaGeometrica = null;
		
		if(nome.equals(Ponto.class.getSimpleName())) {
			formaGeometrica = fabricarPonto(forma.substring(i+1));
		}
		else if(nome.equals(Linha.class.getSimpleName()))
			formaGeometrica = fabricarLinha(forma.substring(i+1));
		else if(nome.equals(Triangulo.class.getSimpleName()))
			formaGeometrica = fabricarTriangulo(forma.substring(i+1));
		else if(nome.equals(Circulo.class.getSimpleName()))
			formaGeometrica = fabricarCirculo(forma.substring(i+1));
		else if(nome.equals(Retangulo.class.getSimpleName()))
			formaGeometrica = fabricarRetangulo(forma.substring(i+1));
		return formaGeometrica;
	}
	
	public static FormaGeometrica fabricarRetangulo(String str) {
		return null;
	}

	public static FormaGeometrica fabricarCirculo(String substring) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Triangulo fabricarTriangulo(String str) {
		int i = str.indexOf(')');
		Ponto p1 = fabricarPonto(str.substring(1, i+1));
		str = str.substring(i+1);
		i = str.indexOf(')');
		Ponto p2 = fabricarPonto(str.substring(0, i+1));
		str = str.substring(i+1);
		i = str.indexOf(')');
		Ponto p3 = fabricarPonto(str.substring(0, i+1));
		Triangulo t = new Triangulo(p1, p2, p3);
		return t;
	}

	public static Linha fabricarLinha(String linha) {
		int i = linha.indexOf(')');
		Ponto p1 = fabricarPonto(linha.substring(1, i+1));
		Ponto p2 = fabricarPonto(linha.substring(i+1));
		Linha l =new Linha(p1, p2);
		return l;
	}

	public static Ponto fabricarPonto(String ponto) {
		int i = ponto.indexOf(';');
		int j = ponto.indexOf(')');
		int x = Integer.parseInt(ponto.substring(1, i));
		int y = Integer.parseInt(ponto.substring(i+2, j));
		Ponto p = new Ponto(x,y);
		return p;
	}
}
