package projeto.seguranca.software.criptografia;

public class CifraCezar {

	private Integer MAX_CHAR_ASCII = 255;
	private Integer MIN_CHAR_ASCII = 1;
	
	public String encripta(String frase, int chave) throws IllegalArgumentException {

//		frase = StringUtil.removerAcentos(frase);
//		
//		if(StringUtil.contemNumeros(frase)){
//			throw new IllegalArgumentException("Não podem haver numeros na frase");
//		}
		
		String textoCriptografado = "";
		
		for(int i=0; i< frase.length(); i++){
			textoCriptografado+= getChar(frase.charAt(i), chave);
		}
		
		return textoCriptografado;
	}
	

	public String decripta(String frase, int chave){
		
		String textoDescriptografado = "";
		
		chave = chave *-1;
		
		for(int i=0; i< frase.length(); i++){
			textoDescriptografado+= getChar(frase.charAt(i), chave);
		}
		
		return textoDescriptografado; 
	}
	
	private char getChar(char caracter, int chave){
		
		if(caracter == ' '){
			return caracter;
		}
		
		int posicao = (((int) caracter)+ chave);
		
		posicao = validaPosicao(posicao);
		
		return ((char)posicao); 
	}


	private int validaPosicao(int posicao) {
		
		while(posicao > MAX_CHAR_ASCII || posicao < MIN_CHAR_ASCII){
			if(posicao > MAX_CHAR_ASCII){
				posicao = MIN_CHAR_ASCII + (posicao - MAX_CHAR_ASCII) -1;
			} else if (posicao < MIN_CHAR_ASCII ){
				posicao = MAX_CHAR_ASCII - ( MIN_CHAR_ASCII - posicao ) + 1;  
			}
		}
		
		return posicao;
			
			
	}
	
	
	
}
