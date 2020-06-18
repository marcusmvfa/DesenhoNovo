package udc.psw.desenho.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;

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
import udc.psw.desenho.database.PontoDAO;
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
import javax.swing.JDialog;
import javax.swing.JTextField;

public class JanelaAplicacao extends JFrame {

	private static final long serialVersionUID = 1L;
	
	int count = 0;
	
	public JanelaAplicacao() throws SQLException {
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
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
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
		menuBar.add(mnTipoDisplay);
		
		JRadioButtonMenuItem displayDesenho = new JRadioButtonMenuItem("Desenho");
		JRadioButtonMenuItem displayTexto = new JRadioButtonMenuItem("Texto");
		JRadioButtonMenuItem displayDesenhoTexto = new JRadioButtonMenuItem("Desenho e Texto");
		
		
		
		displayDesenho.setSelected(true);
		displayDesenho.addItemListener(new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
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
		
		JMenu mnBanco = new JMenu("Banco de Dados");
		mnArquivo.add(mnBanco);
		
		JMenuItem mntmSalvarTxt = new JMenuItem("Salvar");
		JMenuItem mntmLerTxt = new JMenuItem("Ler");
		mntmSalvarTxt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					try {
						AplicacaoDesenho.getAplicacao().getDocumento().salvarTxt(f);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		mntmLerTxt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					try {
						AplicacaoDesenho.getAplicacao().getDocumento().lerTxt(f);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
					try {
						AplicacaoDesenho.getAplicacao().getDocumento().salvar(f);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmLerSerial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					try {
						AplicacaoDesenho.getAplicacao().getDocumento().ler(f);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnArquivoSerial.add(mntmSalvarSerial);
		mnArquivoSerial.add(mntmLerSerial);
		
		JMenuItem mntmSalvarBanco = new JMenuItem("Salvar no Banco");
		JMenuItem mntmLerBanco = new JMenuItem("Ler do Banco");
		
		
		mntmSalvarBanco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DatabaseDialog dataDialog;
				try {
					dataDialog = new DatabaseDialog("Salvar");
					dataDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dataDialog.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mntmLerBanco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DatabaseDialog dataDialog;
				try {
					dataDialog = new DatabaseDialog("Leitura");
					dataDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dataDialog.setVisible(true);
//					AplicacaoDesenho.getAplicacao().getDocumento().lerBanco();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		mnBanco.add(mntmSalvarBanco);
		mnBanco.add(mntmLerBanco);

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
		
		JMenu mnServer = new JMenu("Server");
		menuBar.add(mnServer);
		
		JMenuItem mntmServer = new JMenuItem("Server");
		mnServer.add(mntmServer);
		mntmServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mnServer.setEnabled(false);
				mnServer.setText("Server");
			}
		});
		
		JMenuItem mntmClient = new JMenuItem("Client");
		mnServer.add(mntmClient);
		mntmClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mnServer.setEnabled(false);
				mnServer.setText("Cliente");
				
			}
		});
	}
	
	public int contadorAbas() {
		this.count += 1;
		return this.count;
	}
	public void teste() {}

}
