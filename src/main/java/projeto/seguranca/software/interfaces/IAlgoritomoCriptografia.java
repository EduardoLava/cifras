package projeto.seguranca.software.interfaces;

import java.io.Serializable;

/**
 * 
 * @author Eduardo
 *
 */
public interface IAlgoritomoCriptografia extends Serializable {

	String encripta(String frase);
	
	String decripta(String frase);
	
}
