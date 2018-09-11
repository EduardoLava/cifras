package projeto.seguranca.software.criptografia;

import projeto.seguranca.software.interfaces.IAlgoritomoCriptografia;

public class CifraCezar implements IAlgoritomoCriptografia {

	private Integer MAX_CHAR_ASCII = 255;
	private Integer MIN_CHAR_ASCII = 1;
	
	private char CORINGA = '=';
	
	private final Integer chave;
	
	public CifraCezar(Integer chave) {
		this.chave = chave;
	}
	
	@Override
	public String encripta(String frase) throws IllegalArgumentException {

//		frase = StringUtil.removerAcentos(frase);
//		
//		if(StringUtil.contemNumeros(frase)){
//			throw new IllegalArgumentException("Não podem haver numeros na frase");
//		}
		
		frase = frase.replaceAll(" ", String.valueOf(CORINGA));
		
		String textoCriptografado = "";
		
		for(int i=0; i< frase.length(); i++){
			textoCriptografado+= getChar(frase.charAt(i), chave);
		}
		
		return textoCriptografado;
	}
	
	@Override
	public String decripta(String frase){
		
		String textoDescriptografado = "";
		
		Integer chave = this.chave *-1;
		
		for(int i=0; i< frase.length(); i++){
			textoDescriptografado+= getChar(frase.charAt(i), chave);
		}
		
		textoDescriptografado = textoDescriptografado.replaceAll(String.valueOf(CORINGA), " ");
		return textoDescriptografado; 
	}
	
	private char getChar(char caracter, Integer chave){
		
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
