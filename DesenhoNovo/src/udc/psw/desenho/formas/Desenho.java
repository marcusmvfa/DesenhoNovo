package udc.psw.desenho.formas;

public class Desenho {
	Integer ID;
	String nome;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d - %s", this.ID, this.nome);
	}
}
