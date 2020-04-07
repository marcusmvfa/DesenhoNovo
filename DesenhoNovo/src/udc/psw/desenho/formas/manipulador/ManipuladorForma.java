package udc.psw.desenho.formas.manipulador;

import java.awt.Graphics;

public interface ManipuladorForma {

	void desenhar(Graphics g);
	
	void arrastar(int x, int y);
	void mover(int x, int y);
	boolean clicar(int x, int y);
	
}
