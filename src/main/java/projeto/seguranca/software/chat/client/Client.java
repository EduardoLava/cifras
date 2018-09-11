package projeto.seguranca.software.chat.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import projeto.seguranca.software.chat.Security;
import projeto.seguranca.software.interfaces.IAlgoritomoCriptografia;
import projeto.seguranca.software.interfaces.IListenerTexto;

public class Client {

	private String host;
	private int port;
	private String nickname;
	
	private IAlgoritomoCriptografia cifra;

	private Socket client;
	private PrintStream output;

	private IListenerTexto listener;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client("127.0.0.1", 12345,"", null).run();
	}

	public Client(
		String host, 
		int port, 
		String nickName,
		IListenerTexto listener
	) {
		this.host = host;
		this.port = port;
		this.nickname = nickName;
		this.cifra = Security.getInstance().getAlgoritmoCriptografia();
		this.listener = listener;
	}

	public void run() throws UnknownHostException, IOException {
		// connect client to server
		this.client = new Socket(host, port);
		System.out.println("Client successfully connected to server!");

		// create a new thread for server messages handling
		new Thread(new ReceivedMessagesHandler(client.getInputStream() , listener)).start();

		// ask for a nickname
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Enter a nickname: ");
//		nickname = sc.nextLine();

		// read messages from keyboard and send to server
//		System.out.println("Send messages: ");
		output = new PrintStream(client.getOutputStream());
//		while (sc.hasNextLine()) {
//			final String mensagem = nickname + ": " + sc.nextLine();
//			output.println(cifra.encripta(mensagem, Security.getChave()));
////			this.cifraCezar.encripta(nickname + ": " + sc.nextLine(), Security.getChave());
////			output.println(nickname + ": " + sc.nextLine());
//		}
		
//		output.close();
//		sc.close();
//		client.close();
	}
	
	public void close() throws IOException{
		output.close();
		client.close();
	}
	
	/**
	 * Envia mensagem criptografada
	 * 
	 * @param mensagem
	 */
	public void enviarMensagem(String mensagem){
		
		// criptografa
		String mensagemCriptografada= cifra.encripta(nickname + ": " +mensagem);
		
		// envia
		output.println(mensagemCriptografada);
		
	}
	
}

