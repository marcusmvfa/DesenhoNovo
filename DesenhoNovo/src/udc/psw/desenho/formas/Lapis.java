package udc.psw.desenho.formas;

public class Lapis implements FormaGeometrica{
	
	private static final long serialVersionUID = 1L;
	private List<Ponto> pontos;
	
	private transient ManipuladorLapis manipulador = null;
	
	public Lapis() {
		pontos = new LinkedList<Ponto>();
	}
	
	public int getTam() {
		return pontos.size();
	}
	
	public Ponto getPosicao(int i) {
		return pontos.get(i);
	}

	public void adicionar(Ponto p) {
		pontos.add(p);
	}
	
	@Override
	public Ponto centro() {
		pontos.size();
		int x = 0;
		int y = 0;
		for (Ponto p : pontos) {
			x += p.getX();
			y += p.getY();
		}
		x /= pontos.size();
		y /= pontos.size();
		return new Ponto (x, y);
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
		return 0;
	}

	@Override
	public double altura() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double distancia(FormaGeometrica f) {
		return centro().distancia(f.centro());
	}

	@Override
	public String getNome() {
		return "Lapis";
	}

	@Override
	public Ponto getEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ponto getStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaGeometrica clone() {
		return new Lapis();
	}

	@Override
	public ManipuladorLapis getManipulador() {
		if(manipulador == null)
			manipulador = new ManipuladorLapis(this);
		return manipulador;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		for(Ponto p : pontos) {
			buf.append(p);
		}
		buf.append("}");
		return buf.toString();
	}
}
