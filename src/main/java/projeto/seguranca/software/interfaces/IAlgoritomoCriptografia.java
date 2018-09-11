package projeto.seguranca.software.interfaces;

/**
 * 
 * @author Eduardo
 *
 * @param <T> tipo da chave
 */
public interface IAlgoritomoCriptografia {

	String encripta(String frase);
	
	String decripta(String frase);
	
}
