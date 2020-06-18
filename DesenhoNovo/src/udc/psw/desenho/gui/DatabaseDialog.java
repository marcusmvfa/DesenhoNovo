package udc.psw.desenho.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import udc.psw.desenho.AplicacaoDesenho;
import udc.psw.desenho.database.DesenhosDAO;
import udc.psw.desenho.formas.Desenho;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.SpringLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;

public class DatabaseDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnAddDatabase;
	private Integer returnCode;
	
	private String modoDialog; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatabaseDialog dialog = new DatabaseDialog("Leitura");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public DatabaseDialog(String nome) throws SQLException {
		
		this.modoDialog = nome;

		List<Desenho> desenhos = new ArrayList<Desenho>();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		if(this.modoDialog == "Salvar")
			super.setTitle("Salvar no Banco de Dados");
		else if(this.modoDialog == "Leitura")
			super.setTitle("Ler do Banco de Dados");
		getContentPane().add(contentPanel);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblNewLabel = new JLabel("Selecione o Banco");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 85, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPanel);
			lblNewLabel.setMinimumSize(new Dimension(0, 0));
			contentPanel.add(lblNewLabel);
		}
		{
//			DesenhosDAO dao = new DesenhosDAO();
//			for (Desenho d : dao.getAllDesenhos()) {
//				desenhos.add(d);
//			}
		}
		{
//			comboBox = new JComboBox(desenhos.toArray());
			comboBox = new JComboBox(fillComboBox(desenhos).toArray());
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, comboBox);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBox, 80, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox, 146, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, comboBox, 100, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, comboBox, 287, SpringLayout.WEST, contentPanel);
			comboBox.setMaximumSize(new Dimension(100, 50));
			comboBox.setSize(100, 50);
			contentPanel.add(comboBox);
		}
		{
			btnNewButton = new JButton("Novo Desenho");
			btnNewButton.setBounds(new Rectangle(20, 20, 20, 20));
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnNewButton, 28, SpringLayout.SOUTH, comboBox);
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, comboBox);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnNewButton, 148, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, comboBox);
			
			if(modoDialog == "Salvar")
				contentPanel.add(btnNewButton);
			
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textField.setVisible(true);
					btnAddDatabase.setVisible(true);
				}
			});
		}

		textField = new JTextField();
		textField.setVisible(false);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, 22, SpringLayout.SOUTH, btnNewButton);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, comboBox);
		sl_contentPanel.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, comboBox);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			btnAddDatabase = new JButton("OK");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnAddDatabase, 0, SpringLayout.NORTH, textField);
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnAddDatabase, -131, SpringLayout.EAST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnAddDatabase, 0, SpringLayout.SOUTH, textField);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnAddDatabase, -78, SpringLayout.EAST, contentPanel);
			btnAddDatabase.setActionCommand("OK");
			btnAddDatabase.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DesenhosDAO dao;
					try {
						if (textField.getText().length() > 2) {
							dao = new DesenhosDAO();
							System.out.println(textField.getText());
							returnCode = dao.saveDesenho(textField.getText()); // -2 se for success

							if (returnCode == -2) {
								textField.setText("");
								textField.setVisible(false);
								btnAddDatabase.setVisible(false);
								lblNewLabel_1.setVisible(false);
								comboBox.addItem(dao.getLastItem());
								dispose();
							}
						} 
						else {
							lblNewLabel_1.setVisible(true);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			btnAddDatabase.setVisible(false);
			contentPanel.add(btnAddDatabase);
		}
		
		lblNewLabel_1 = new JLabel("Nome deve ter no m\u00EDnimo 3 caracteres");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 4, SpringLayout.SOUTH, textField);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, comboBox);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblNewLabel_1, 229, SpringLayout.WEST, comboBox);
		lblNewLabel_1.setVisible(false);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBounds(0, 228, 434, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			if(modoDialog == "Salvar")
			{
				JButton okButton = new JButton("Salvar");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Desenho des = new Desenho();
							des = (Desenho) comboBox.getSelectedItem();
							AplicacaoDesenho.getAplicacao().getDocumento().salvarNoBanco(des.getID());
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			else if(modoDialog == "Leitura")
			{
				JButton okButton = new JButton("Ler");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Desenho des = new Desenho();
							des = (Desenho) comboBox.getSelectedItem();
							AplicacaoDesenho.getAplicacao().getDocumento().lerBanco(des.getID());
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	public List<Desenho> fillComboBox(List<Desenho> list) throws SQLException {
		list.clear();
		DesenhosDAO dao = new DesenhosDAO();
		for (Desenho d : dao.getAllDesenhos()) {
			list.add(d);
		}
		return list;
	}
}