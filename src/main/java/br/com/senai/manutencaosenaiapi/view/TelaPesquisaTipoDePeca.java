package br.com.senai.manutencaosenaiapi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;


import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.service.TipoDePecaService;
import br.com.senai.manutencaosenaiapi.view.table.TipoDePecaTableModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaPesquisaTipoDePeca extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtParametro;
	private JTable table;
	@Autowired
	private TelaDeCadastroDeTiposDePeca telaCadastro;
	@Autowired
	private TipoDePecaService tipoDePecaService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPesquisaTipoDePeca frame = new TelaPesquisaTipoDePeca();
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
	public TelaPesquisaTipoDePeca() {
		setTitle("Pesquisa de Tipo de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Parametro");
		
		txtParametro = new JTextField();
		txtParametro.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<TipoDePeca> tipos = tipoDePecaService.listarPor(txtParametro.getText());
					TipoDePecaTableModel model = new TipoDePecaTableModel(tipos);
					table.setModel(model);
					TableColumnModel tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(50);
					tcm.getColumn(1).setPreferredWidth(500);
					table.updateUI();
					
				} 
				catch (Exception ex) {
					JOptionPane.showMessageDialog(btnPesquisar, ex.getMessage());
				}
				
			}
		});
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaCadastro.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es para a linha selecionada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = table.getSelectedRow();
					TipoDePecaTableModel tptm = (TipoDePecaTableModel) table.getModel();
					TipoDePeca tipoSelecionado = tptm.getPor(linhaSelecionada);
					telaCadastro.colocarEmEdicao(tipoSelecionado);
					telaCadastro.setVisible(true);
					setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnEditar, "Selecione um tipo para editar");
				}
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = table.getSelectedRow();
					TipoDePecaTableModel tptm = (TipoDePecaTableModel) table.getModel();
					TipoDePeca tipoSelecionado = tptm.getPor(linhaSelecionada);
					tipoDePecaService.removerPor(tipoSelecionado.getId());
					JOptionPane.showMessageDialog(btnExcluir, "Tipo de peça excluida!");
				} 
				catch (Exception e2) {
					JOptionPane.showMessageDialog(btnEditar, "Selecione um tipo de peça para efetuar a exclusão!");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 212, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addComponent(btnEditar)
					.addGap(18)
					.addComponent(btnExcluir)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 73, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtParametro, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(321)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtParametro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar)
						.addComponent(btnAdicionar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
