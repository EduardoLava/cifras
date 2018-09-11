package projeto.seguranca.software.chat.client;

import java.io.InputStream;
import java.util.Scanner;

import projeto.seguranca.software.chat.Security;
import projeto.seguranca.software.interfaces.IAlgoritomoCriptografia;
import projeto.seguranca.software.interfaces.IListenerTexto;

public class ReceivedMessagesHandler implements Runnable {

	private InputStream server;
	private IAlgoritomoCriptografia cifra;
	
	private IListenerTexto listener;

	public ReceivedMessagesHandler(
		InputStream server, 
		IListenerTexto listener
	) {
		this.server = server;
		this.listener = listener;
		this.cifra = Security.getInstance().getAlgoritmoCriptografia();
	}

	public void run() {
		// receive server messages and print out to screen
		Scanner s = new Scanner(server);
		while (s.hasNextLine()) {
			String mensagemRecebida = s.nextLine();
			mensagemRecebida = this.cifra.decripta(mensagemRecebida);
			listener.setTexto(mensagemRecebida);
//			System.out.println(mensagemRecebida);
		}
		s.close();
	}
}