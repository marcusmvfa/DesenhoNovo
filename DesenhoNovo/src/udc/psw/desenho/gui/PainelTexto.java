package udc.psw.desenho.gui;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import udc.psw.desenho.AplicacaoDesenho;
import udc.psw.desenho.formas.FormaGeometrica;
import udc.psw.desenho.formas.TipoPainel;

public class PainelTexto extends JTextPane implements TipoPainel{

	private static final long serialVersionUID = 1L;
	StyledDocument doc = this.getStyledDocument();

	public PainelTexto() {
	}
	@Override
	public void atualizar(FormaGeometrica forma) {
		if(!forma.toString().isEmpty()) {			
			try {
				doc.insertString(doc.getLength(), formaFormatada(forma), null);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public String formaFormatada(FormaGeometrica forma) {
		return forma.getNome() + ' ' + forma.toString() + ' ';
	}
	
}
