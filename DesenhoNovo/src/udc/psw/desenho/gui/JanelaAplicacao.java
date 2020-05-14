package udc.psw.desenho.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import udc.psw.desenho.AplicacaoDesenho;
import udc.psw.desenho.formas.Circulo;
import udc.psw.desenho.formas.Linha;
import udc.psw.desenho.formas.Ponto;
import udc.psw.desenho.formas.Retangulo;
import udc.psw.desenho.formas.Triangulo;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class JanelaAplicacao extends JFrame {

	private static final long serialVersionUID = 1L;
	
	int count = 0;
	
	public JanelaAplicacao() {
		super("Aplicação de desenho com o mouse");
		
		ImageIcon icon = new ImageIcon("../icon-plus.png","Adicionar aba");

		ButtonGroup buttonGroup = new ButtonGroup();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024,740);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel status = new JLabel("Status da aplicação");
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setAlignmentY(Component.TOP_ALIGNMENT);
		getContentPane().add(status, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		PainelDesenho painel = new PainelDesenho(status);
		//getContentPane().add(painel, BorderLayout.CENTER);

		PainelTexto painelTexto = new PainelTexto();
		//tabbedPane.addTab("tab2", painelTexto);
		//this.add(painel, BorderLayout.CENTER);
		
		AplicacaoDesenho.getDocumento().addPainel(painel);
		painel.setLayout(new BorderLayout(0, 0));
		
		PainelDesenhoTexto painelDesenhoTexto = new PainelDesenhoTexto(status);
		painelDesenhoTexto.setLayout(new BoxLayout(painelDesenhoTexto, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, painelTexto, painel);	
		splitPane.setDividerLocation(250);
		painelDesenhoTexto.add(splitPane);
		
		AplicacaoDesenho.getDocumento().addPainel(painelTexto);
		AplicacaoDesenho.getDocumento().addPainel(painelDesenhoTexto);
//		AplicacaoDesenho.getDocumento().listaPaineis.add(painel);
//		AplicacaoDesenho.getDocumento().listaPaineis.add(painelTexto);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);
		
		JMenu mnTipoDisplay = new JMenu("Display");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnTipoDisplay);
		
		JRadioButtonMenuItem displayDesenho = new JRadioButtonMenuItem("Desenho");
		JRadioButtonMenuItem displayTexto = new JRadioButtonMenuItem("Texto");
		JRadioButtonMenuItem displayDesenhoTexto = new JRadioButtonMenuItem("Desenho e Texto");
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		
		displayDesenho.setSelected(true);
		displayDesenho.addItemListener(new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
//					getContentPane().remove(painelTexto);
//					getContentPane().remove(painelDesenhoTexto);
//					getContentPane().add(painel, BorderLayout.CENTER);
//					getContentPane().revalidate();
					
					tabbedPane.remove(painelTexto);
					tabbedPane.remove(painelDesenhoTexto);
					tabbedPane.addTab("Aba 1", painel);
					tabbedPane.addTab("Aba 2", painelTexto);
					tabbedPane.addTab("Aba 3", painelDesenhoTexto);
					revalidate();
				}
			}
		});
		buttonGroup.add(displayDesenho);
		mnTipoDisplay.add(displayDesenho);

		displayTexto.addItemListener(new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {										//PRECISO ORGANIZAR DIREITO COMO FUNCIONA AS ABAS, DEPOIS PENSAR NA QUESTÃO DE CADA ABA TER SEU DOCUMENTO
				if(e.getStateChange() == ItemEvent.SELECTED) {
//					getContentPane().remove(painel);
//					getContentPane().remove(painelDesenhoTexto);
					tabbedPane.remove(displayDesenhoTexto);
					tabbedPane.remove(painel);
					
					tabbedPane.addTab("Aba 2",painelTexto);
//					getContentPane().add(painelTexto, BorderLayout.CENTER);
//					getContentPane().revalidate();
					revalidate();
				}
			}
		});
		buttonGroup.add(displayTexto);
		mnTipoDisplay.add(displayTexto);
		
		displayDesenhoTexto.addItemListener(new ItemListener () {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					getContentPane().remove(painel);
					getContentPane().remove(painelTexto);
					getContentPane().add(painelDesenhoTexto, BorderLayout.CENTER);
					
					//getContentPane().revalidate();
					revalidate();
				}
			}
			
		});
		buttonGroup.add(displayDesenhoTexto);
		mnTipoDisplay.add(displayDesenhoTexto);
		
		JMenu mnArquivoTxt = new JMenu("Arquivo Txt");
//		mnArquivo.setMnemonic('A');
		mnArquivo.add(mnArquivoTxt);
		
		JMenu mnArquivoSerial = new JMenu("Arquivo Serial");
		mnArquivo.add(mnArquivoSerial);
		
		JMenuItem mntmSalvarTxt = new JMenuItem("Salvar");
		JMenuItem mntmLerTxt = new JMenuItem("Ler");
		mntmSalvarTxt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().salvarTxt(f);
				}
			}
		});
		
		mntmLerTxt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().lerTxt(f);
				}
			}
		});
		mnArquivoTxt.add(mntmSalvarTxt);
		mnArquivoTxt.add(mntmLerTxt);
		
		JMenuItem mntmSalvarSerial = new JMenuItem("Salvar");
		JMenuItem mntmLerSerial = new JMenuItem("Ler");
		mntmSalvarSerial.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().salvar(f);
				}
			}
		});
		mntmLerSerial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().ler(f);
				}
			}
		});
		mnArquivoSerial.add(mntmSalvarSerial);
		mnArquivoSerial.add(mntmLerSerial);

		JButton botaoAba = new JButton("+ Aba");
		menuBar.add(botaoAba);
		botaoAba.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.addTab("Aba", null, painel, "Nova Aba");
			}
		});
		
		
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
	
	public int contadorAbas() {
		this.count += 1;
		return this.count;
	}
	public void teste() {}

}
