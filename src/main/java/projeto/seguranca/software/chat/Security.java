package projeto.seguranca.software.chat;

import java.util.Random;

import projeto.seguranca.software.interfaces.IAlgoritomoCriptografia;

public class Security {

	private static Security security;
	private static final Integer MAX = 500;
	
	private Random random;
	
	private IAlgoritomoCriptografia algoritmoCriptografia;
	
	public static Security getInstance(){
		
		if( security == null){
			security = new Security();
		}
		
		return security;
		
	}

	private Security() {
		random = new Random();
		this.algoritmoCriptografia = null;
	}
	
	
	/**
	 * 
	 * Gera chave randomica somente com numeros
	 * 
	 * @return
	 */
	public Integer getChaveNumber(){
		return random.nextInt(MAX);
	}
	
	public IAlgoritomoCriptografia getAlgoritmoCriptografia() {
		return security.algoritmoCriptografia;
	}

	public void setAlgoritmoCriptografia(IAlgoritomoCriptografia arg) {
		security.algoritmoCriptografia = arg;
	}
	
	
	
}
