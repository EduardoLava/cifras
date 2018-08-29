package projeto.seguranca.software.chat.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	private int port;
	private List<PrintStream> clients;
	private ServerSocket server;
	
	/**
	 * Inicializa o servico do servidor
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		new Server(12345).run();
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
			
			// add client message to list
			this.clients.add(new PrintStream(client.getOutputStream()));
			
			// create a new thread for client handling
			new Thread(new ClientHandler(this, client.getInputStream())).start();
		}
	}

	void broadcastMessages(String msg) {
		for (PrintStream client : this.clients) {
			client.println(msg);
		}
	}
}

