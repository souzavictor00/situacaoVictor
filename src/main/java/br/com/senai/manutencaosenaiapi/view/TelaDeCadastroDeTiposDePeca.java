package br.com.senai.manutencaosenaiapi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.service.TipoDePecaService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaDeCadastroDeTiposDePeca extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtDescricao;
	@Autowired
	private TipoDePecaService tipoDePecaService;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeCadastroDeTiposDePeca frame = new TelaDeCadastroDeTiposDePeca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaDeCadastroDeTiposDePeca() {
		setTitle("Cadastro de Tipos de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 132);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Id");
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição");
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(txtId.getText() != null && txtId.getText().length() > 0) {
						TipoDePeca tipoDePeca = new TipoDePeca();
						tipoDePeca.setDescricao(txtDescricao.getText());
						tipoDePeca.setId(Integer.parseInt(txtId.getText()));
						tipoDePecaService.alterar(tipoDePeca);
						JOptionPane.showMessageDialog(btnSalvar, "Tipo de peça alterada com sucesso!");
					}else {
						TipoDePeca tipoDePeca = new TipoDePeca();
						tipoDePeca.setDescricao(txtDescricao.getText());
						TipoDePeca tipoSalvo = tipoDePecaService.inserir(tipoDePeca);
						JOptionPane.showMessageDialog(btnSalvar, "Tipo de peça salva com sucesso!");
						txtId.setText(tipoSalvo.getId().toString());
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(btnSalvar, ex.getMessage());
				}
			}
		});
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(txtDescricao, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
							.addComponent(btnSalvar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnVoltar)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void colocarEmEdicao(TipoDePeca tipoSelecionado) {
		txtId.setText(tipoSelecionado.getId().toString());
		txtDescricao.setText(tipoSelecionado.getDescricao());		
	}
}
