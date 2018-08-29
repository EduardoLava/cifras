package projeto.seguranca.software.chat.client;

import java.io.InputStream;
import java.util.Scanner;

import projeto.seguranca.software.chat.Security;
import projeto.seguranca.software.criptografia.CifraCezar;

public class ReceivedMessagesHandler implements Runnable {

	private InputStream server;
	private CifraCezar cifraCezar;

	public ReceivedMessagesHandler(InputStream server) {
		this.server = server;
		this.cifraCezar = new CifraCezar();
	}

	public void run() {
		// receive server messages and print out to screen
		Scanner s = new Scanner(server);
		while (s.hasNextLine()) {
			String mensagemRecebida = s.nextLine();
			mensagemRecebida = this.cifraCezar.decripta(mensagemRecebida, Security.getChave());
			System.out.println(mensagemRecebida);
		}
		s.close();
	}
}