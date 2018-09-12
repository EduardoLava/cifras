package projeto.seguranca.software.chat.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import projeto.seguranca.software.chat.Security;
import projeto.seguranca.software.criptografia.CifraCezar;
import projeto.seguranca.software.criptografia.CifraTransposicao;
import projeto.seguranca.software.interfaces.IAlgoritomoCriptografia;
import projeto.seguranca.software.util.StringUtil;

public class Server {

	private int port;
	private List<PrintStream> clients;
	private ServerSocket server;
	
	private IAlgoritomoCriptografia algoritomoCriptografia;
	
	/**
	 * Inicializa o servico do servidor
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		Server server = new Server(12345);
		
		server.algoritomoCriptografia = server.getAlgoritmo();
		
		server.run();
		
	}
	
	private IAlgoritomoCriptografia getAlgoritmo() {
		
		int numero = new Random().nextInt(1000);
		System.out.println(numero);
		if( numero % 2 == 0){
			System.out.println("Utilizando cifra de cezar");
			return new CifraCezar(Security.getInstance().getChaveNumber());
		}
		
		System.out.println("Utilizando cifra de transposicao");
		return new CifraTransposicao(StringUtil.getChaveAleatoria());
		
	}

	/**
	 * Constructor
	 * 
	 * @param port
	 */
	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<PrintStream>();
	}

	/**
	 * Executa serviço
	 * 
	 * @throws IOException
	 */
	public void run() throws IOException {
		server = new ServerSocket(port) {
			protected void finalize() throws IOException {
				this.close();
			}
		};
		
		System.out.println("Port 12345 is now open.");

		while (true) {
			// accepts a new client
			Socket client = server.accept();
			System.out.println("Connection established with client: " + client.getInetAddress().getHostAddress());
			enviaAlgoritmo(client);
			// add client message to list
			this.clients.add(new PrintStream(client.getOutputStream()));
			
			// create a new thread for client handling
			new Thread(new ClientHandler(this, client.getInputStream())).start();
		}
	}
	
	private void enviaAlgoritmo(Socket socket) throws IOException{
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(this.algoritomoCriptografia);
        objectOutputStream.flush();

//        objectOutputStream.close();
	}

	void broadcastMessages(String msg) {
		for (PrintStream client : this.clients) {
			client.println(msg);
		}
	}
}

