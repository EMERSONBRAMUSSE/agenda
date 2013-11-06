package br.edu.unicapital.agenda.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.edu.unicapital.agenda.controller.Agenda;
import br.edu.unicapital.agenda.model.Contato;

public class Agenda2013 extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Agenda controller;

	private JPanel contentPane;
	private JTextField idTf;
	private JTextField nomeTf;
	private JTextField telefone1Tf;
	private JTextField telefone2Tf;
	JButton apagarBt;
	JButton gravarBt;
	JButton buscarBt;
	JButton editarBt;
	JButton proximoBt;
	JButton anteriorBt;
	JButton novoBt;

	public Agenda2013(String titulo, Agenda controller) {
		super(titulo);
		this.controller = controller;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 426);
		this.setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel idLb = new JLabel("ID");
		idLb.setFont(new Font("Showcard Gothic", Font.ITALIC, 18));
		idLb.setBackground(new Color(0, 0, 255));
		idLb.setLabelFor(idLb);
		idLb.setBounds(87, 148, 45, 34);
		contentPane.add(idLb);

		idTf = new JTextField();
		idTf.setBounds(124, 157, 86, 20);
		contentPane.add(idTf);
		idTf.setColumns(10);

		JLabel nomeLb = new JLabel("NOME");
		nomeLb.setBackground(new Color(0, 0, 255));
		nomeLb.setFont(new Font("Showcard Gothic", Font.ITALIC, 18));
		nomeLb.setBounds(53, 183, 61, 34);
		contentPane.add(nomeLb);

		nomeTf = new JTextField();
		nomeTf.setBounds(124, 192, 228, 20);
		contentPane.add(nomeTf);
		nomeTf.setColumns(10);

		JLabel tel1Lb = new JLabel("TELEFONE 1");
		tel1Lb.setFont(new Font("Showcard Gothic", Font.ITALIC, 18));
		tel1Lb.setBounds(10, 222, 104, 20);
		contentPane.add(tel1Lb);

		telefone1Tf = new JTextField();
		telefone1Tf.setBounds(124, 224, 120, 20);
		contentPane.add(telefone1Tf);
		telefone1Tf.setColumns(10);

		JLabel tel2Lb = new JLabel("TELEFONE 2");
		tel2Lb.setFont(new Font("Showcard Gothic", Font.ITALIC, 18));
		tel2Lb.setBounds(10, 253, 104, 20);
		contentPane.add(tel2Lb);

		telefone2Tf = new JTextField();
		telefone2Tf.setColumns(10);
		telefone2Tf.setBounds(124, 255, 120, 20);
		contentPane.add(telefone2Tf);

		anteriorBt = new JButton("");
		anteriorBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				anterior();
			}
		});
		anteriorBt.setIcon(new ImageIcon("C:\\Users\\EDILAINE\\Desktop\\preview.jpg"));
		anteriorBt.setBounds(10, 326, 45, 51);
		contentPane.add(anteriorBt);

		proximoBt = new JButton("");
		proximoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				proximo();
			}
		});
		proximoBt.setIcon(new ImageIcon("C:\\Users\\EDILAINE\\Desktop\\preview - C\u00F3pia.jpg"));
		proximoBt.setBounds(317, 326, 54, 51);
		contentPane.add(proximoBt);

		gravarBt = new JButton("Salvar");
		gravarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gravar();
			}
		});
		gravarBt.setBounds(87, 304, 89, 23);
		contentPane.add(gravarBt);

		apagarBt = new JButton("Excluir");
		apagarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				apagar();
			}
		});
		apagarBt.setBounds(202, 304, 89, 23);
		contentPane.add(apagarBt);

		novoBt = new JButton("Novo");
		novoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				novo();
			}
		});
		novoBt.setBounds(87, 338, 89, 23);
		contentPane.add(novoBt);

		buscarBt = new JButton("Buscar");
		buscarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buscar();
			}
		});
		buscarBt.setBounds(202, 338, 89, 23);
		contentPane.add(buscarBt);

		editarBt = new JButton("Editar");
		editarBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		editarBt.setBounds(282, 254, 89, 23);
		contentPane.add(editarBt);

		JLabel lmagenAgenda = new JLabel("New label");
		lmagenAgenda.setBackground(Color.WHITE);
		lmagenAgenda.setLabelFor(contentPane);
		lmagenAgenda.setBounds(5, 0, 371, 388);
		lmagenAgenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lmagenAgenda.setIcon(new ImageIcon("C:\\Users\\EDILAINE\\Desktop\\\u00CDndice.jpeg"));
		contentPane.add(lmagenAgenda);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(anteriorBt)) {
			anterior();
		} else if (e.getSource().equals(proximoBt)) {
			proximo();
		} else if (e.getSource().equals(gravarBt)) {
			gravar();
		} else if (e.getSource().equals(apagarBt)) {
			apagar();
		} else if (e.getSource().equals(novoBt)) {
			novo();
		} else if (e.getSource().equals(buscarBt)) {
			buscar();
		} else if (e.getSource().equals(editarBt)) {
			editar();
		}

	}

	private void gravar() {

		if ((nomeTf.getText().trim().isEmpty())) {

		} else if ((telefone1Tf.getText().trim().isEmpty())) {

		} else if ((telefone2Tf.getText().trim().isEmpty())) {

		} else {

			Contato c = new Contato(idTf.getText(), nomeTf.getText(), telefone1Tf.getText(), telefone2Tf.getText());
			desenhar(controller.gravar(c));

		}

	}

	private void editar() {



			Contato c = new Contato(idTf.getText(), nomeTf.getText(), telefone1Tf.getText(), telefone2Tf.getText());
			desenhar(controller.editar(c));

			idTf.setText("");
			nomeTf.setText("");
			telefone1Tf.setText("");
			telefone2Tf.setText("");

		}

	

	private void novo() {
		desenhar(controller.novo());
	}

	private void apagar() {

		if ((nomeTf.getText().trim().isEmpty())) {

		} else if ((telefone1Tf.getText().trim().isEmpty())) {

		} else if ((telefone2Tf.getText().trim().isEmpty())) {

		} else {

			desenhar(controller.apagar());

		}
	}

	private void proximo() {
		desenhar(controller.proximo());
	}

	private void anterior() {
		desenhar(controller.anterior());
	}

	private void buscar() {
		String nome = JOptionPane.showInputDialog("Digite o nome");
		desenhar(controller.buscar(nome));
	}

	public void desenhar(Contato c) {
		idTf.setText("" + c.getId());
		nomeTf.setText(c.getNome());
		telefone1Tf.setText(c.getTelefone1());
		telefone2Tf.setText(c.getTelefone2());
	}

}
