package projeto.seguranca.software.util;

import java.util.Random;

public class StringUtil {

	private static final String letras = "abcdefghijklmnopqrstuvwyxz.,+-()"; 
	
	public static String getChaveAleatoria(){

		Random random = new Random();  

		String armazenaChaves = "";  
		int index = -1;
		int i = 0;
		
		int tamanhoChave = 0;
		
		while (tamanhoChave < 2) {
			tamanhoChave = random.nextInt(letras.length() - 1);
		}
		
		System.out.println(tamanhoChave);
		
		do {
			
		   index = random.nextInt( letras.length() );  
		   String letra = letras.substring( index, index + 1 );
		   if(!armazenaChaves.contains(letra)){
			   armazenaChaves += letra;  
			   i++;
		   }
			
		} while (i < tamanhoChave);
		
		System.out.println(armazenaChaves);
		
		return armazenaChaves;
	}
	
	
}
