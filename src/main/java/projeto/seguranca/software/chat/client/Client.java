package projeto.seguranca.software.chat.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import projeto.seguranca.software.chat.Security;
import projeto.seguranca.software.criptografia.CifraCezar;

public class Client {

	private String host;
	private int port;
	private String nickname;
	
	private CifraCezar cifraCezar;

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client("127.0.0.1", 12345).run();
	}

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
		this.cifraCezar = new CifraCezar();
	}

	public void run() throws UnknownHostException, IOException {
		// connect client to server
		Socket client = new Socket(host, port);
		System.out.println("Client successfully connected to server!");

		// create a new thread for server messages handling
		new Thread(new ReceivedMessagesHandler(client.getInputStream())).start();

		// ask for a nickname
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a nickname: ");
		nickname = sc.nextLine();

		// read messages from keyboard and send to server
		System.out.println("Send messages: ");
		PrintStream output = new PrintStream(client.getOutputStream());
		while (sc.hasNextLine()) {
			final String mensagem = nickname + ": " + sc.nextLine();
			output.println(cifraCezar.encripta(mensagem, Security.getChave()));
//			this.cifraCezar.encripta(nickname + ": " + sc.nextLine(), Security.getChave());
//			output.println(nickname + ": " + sc.nextLine());
		}
		
		output.close();
		sc.close();
		client.close();
	}
}

