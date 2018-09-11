package projeto.seguranca.software.gui.impl;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import projeto.seguranca.software.bean.Pessoa;
import projeto.seguranca.software.chat.client.Client;
import projeto.seguranca.software.gui.AbstractWindow;
import projeto.seguranca.software.gui.MyGrigLayout;
import projeto.seguranca.software.interfaces.IListenerTexto;

public class ChatWindow extends AbstractWindow implements IListenerTexto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8125828973584885351L;
	
	private final Pessoa pessoa;
	
	private final Client client;
	
	
	public ChatWindow(Pessoa pessoa) throws UnknownHostException, IOException {
		super("Chat - "+pessoa.getNome());
		this.pessoa = pessoa;
		
		setPreferredSize(new Dimension(550, 540));
		pack();
		
		client = new Client("127.0.0.1", 12345, this.pessoa.getNome(), this);
		client.run();
		
	}
	
	@Override
	protected void criaLayout() {
		
		textAreaMensagens = new TextArea();
		tfNovaMensagem = new TextField();
		
		btEnviar = new JButton("Enviar");
		
		btEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionEnviarMensagem();
			}
		});
		
		tfNovaMensagem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionEnviarMensagem();
			}
		});
		
		textAreaMensagens.setPreferredSize(new Dimension(500, 400));
		tfNovaMensagem.setPreferredSize(new Dimension(430, 24));
		
//		textAreaMensagens.setEnabled(false);
		textAreaMensagens.setEditable(false);
		
		MyGrigLayout grid = new MyGrigLayout();
		grid.setPosicao(0, 0);
		grid.weightx = 1D;
		grid.weighty = 1D;
		grid.gridwidth = 3;
		
		this.panelGeral.add(textAreaMensagens, 		grid);
		this.panelGeral.add(tfNovaMensagem, 		new MyGrigLayout().setPosicao(1, 0));
		
		this.panelGeral.add(btEnviar,				new MyGrigLayout().setPosicao(1, 1));
		
		focus();
	}
	
	private void focus(){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				tfNovaMensagem.requestFocusInWindow();
			}
		});
	}
	
	/**
	 * Envia mensagens
	 */
	private void actionEnviarMensagem() {
		this.client.enviarMensagem(tfNovaMensagem.getText());
		tfNovaMensagem.setText("");
		focus();
	}

	

	private TextArea textAreaMensagens;
	private TextField tfNovaMensagem;

	private JButton btEnviar;

	/**
	 * Recebe as mensagens e printa na tela
	 */
	@Override
	public void setTexto(String text) {
		
		text+="\n";
		
		this.textAreaMensagens.setText(
			this.textAreaMensagens.getText()+
			text
		);
		
	}
	
}
