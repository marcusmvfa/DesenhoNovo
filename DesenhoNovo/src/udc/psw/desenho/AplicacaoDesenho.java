package udc.psw.desenho;

import udc.psw.desenho.gui.JanelaAplicacao;

public class AplicacaoDesenho {

	static AplicacaoDesenho aplicacao;

	private AplicacaoDesenho() {
		JanelaAplicacao janela = new JanelaAplicacao();
		janela.setVisible(true);
	}

	static AplicacaoDesenho getAplicacao() {
		if(aplicacao == null)
			aplicacao = new AplicacaoDesenho();
		return aplicacao;
	}

	public static void main(String[] args) {
		getAplicacao();
	}
}
