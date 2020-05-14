package udc.psw.desenho;

import udc.psw.desenho.gui.JanelaAplicacao;

public class AplicacaoDesenho {

	static AplicacaoDesenho aplicacao;
	private static Documento documento;

	private AplicacaoDesenho() {
		documento = new Documento();
		JanelaAplicacao janela = new JanelaAplicacao();
		janela.setVisible(true);
	}

	public static AplicacaoDesenho getAplicacao() {
		if(aplicacao == null)
			aplicacao = new AplicacaoDesenho();
		return aplicacao;
	}
	
	public static Documento getDocumento() {
		if(documento == null)
			documento = new Documento();
		return documento;
	}

	public static void main(String[] args) {
		getAplicacao();
	}
}
