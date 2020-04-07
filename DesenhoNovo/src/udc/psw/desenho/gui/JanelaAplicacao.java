package udc.psw.desenho.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import udc.psw.desenho.formas.Circulo;
import udc.psw.desenho.formas.Linha;
import udc.psw.desenho.formas.Ponto;
import udc.psw.desenho.formas.Retangulo;
import udc.psw.desenho.formas.Triangulo;

public class JanelaAplicacao extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	public JanelaAplicacao() {
		super("Aplicação de desenho com o mouse");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024,740);
		
		this.setLayout(new BorderLayout());
		
		JLabel status = new JLabel("Status da aplicação");
		this.add(status, BorderLayout.SOUTH);
		
		PainelDesenho painel = new PainelDesenho(status);
		this.add(painel, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);
		
		
		JMenu mnArquivoTxt = new JMenu("Arquivo Txt");
//		mnArquivo.setMnemonic('A');
		mnArquivo.add(mnArquivoTxt);
		
		JMenu mnArquivoSerial = new JMenu("Arquivo Serial");
		mnArquivo.add(mnArquivoSerial);
		
		JMenuItem mntmSalvarTxt = new JMenuItem("Salvar");
		JMenuItem mntmLerTxt = new JMenuItem("Ler");
		mnArquivoTxt.add(mntmSalvarTxt);
		mnArquivoTxt.add(mntmLerTxt);
		
		JMenuItem mntmSalvarSerial = new JMenuItem("Salvar");
		JMenuItem mntmLerSerial = new JMenuItem("Ler");
		mnArquivoSerial.add(mntmSalvarSerial);
		mnArquivoSerial.add(mntmLerSerial);
		
		
		JMenu mnForma = new JMenu("Formas");
		mnForma.setMnemonic('F');
		menuBar.add(mnForma);
		
		JMenuItem mntmPonto = new JMenuItem("Ponto");
		mntmPonto.setMnemonic('P');
		mntmPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Ponto(0,0));
			}
		});
		mnForma.add(mntmPonto);
		
		JMenuItem mntmLinha = new JMenuItem("Linha");
		mntmLinha.setMnemonic('L');
		mntmLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Linha(new Ponto(0,0), new Ponto(0,0)));
			}
		});
		mnForma.add(mntmLinha);
		
		JMenuItem mntmTriangulo = new JMenuItem("Triângulo");
		mntmTriangulo.setMnemonic('T');
		mntmTriangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Triangulo(new Ponto(0,0), new Ponto(0,0), new Ponto(0,0)));
			}
		});
		mnForma.add(mntmTriangulo);
		
		JMenuItem mntmCirculo = new JMenuItem("Circulo");
		mntmCirculo.setMnemonic('C');
		mntmCirculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Circulo(new Ponto(0,0), new Ponto(0,0)));
			}
		});
		mnForma.add(mntmCirculo);
		

		JMenuItem mntmRetangulo = new JMenuItem("Retângulo");
		mntmRetangulo.setMnemonic('R');
		mntmRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Retangulo(new Ponto(0,0), new Ponto(0,0)));
			}
		});
		mnForma.add(mntmRetangulo);
	}

}
