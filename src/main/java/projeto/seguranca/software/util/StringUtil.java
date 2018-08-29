package projeto.seguranca.software.util;

import java.text.Normalizer;

public class StringUtil {

	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static boolean contemNumeros(String str){
		return !str.matches("[A-Z a-z]{"+str.length()+"}");
	}
	
}
