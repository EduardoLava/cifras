package projeto.seguranca.software.gui.impl;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import projeto.seguranca.software.bean.Pessoa;
import projeto.seguranca.software.gui.AbstractWindow;
import projeto.seguranca.software.gui.MyGrigLayout;

public class WelcomeWindow extends AbstractWindow {

	
	public WelcomeWindow() {
		super("Seja bem vindo ao chat");
		this.setPreferredSize(new Dimension(400, 200));
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void criaLayout() {
	
		tfNome = new TextField();
		btComecar = new JButton("Começar");
		
		btComecar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionComecar();
			}
			
		});
		
		tfNome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionComecar();
			}
		});
		
		tfNome.setPreferredSize(new Dimension(200, 24));
		
		MyGrigLayout grid = new MyGrigLayout().setPosicao(0, 1);
		grid.fill = GridBagConstraints.HORIZONTAL;
		
		this.panelGeral.add(new Label("Nome:"), new MyGrigLayout().setPosicao(0, 0).alinharDireita());
		this.panelGeral.add(tfNome, grid);
		
		MyGrigLayout gridBtn = new MyGrigLayout().setPosicao(1, 0);
		gridBtn.anchor = GridBagConstraints.CENTER;
		gridBtn.gridwidth = 2;
		
		this.panelGeral.add(btComecar, gridBtn);
		
	}
	
	private void actionComecar(){
		
		if(this.tfNome.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(this, "Informe um nome");
			return;
		}
		
		try {
			
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(this.tfNome.getText());
			
			this.dispose();
			
			new ChatWindow(pessoa);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private JButton btComecar;
	private TextField tfNome;
	

}
