package projeto.seguranca.software.criptografia;

public class CifraTransposicao {

	private char CORINGA = '*';
	
	public String encripta(String frase, String chave) throws IllegalArgumentException {
		
		char[] chaveList = this.preencheLista(chave);
		
		frase = frase.replaceAll(" ", "");
		
		int linhas = frase.length() / chave.length();
		int colunas = chave.length();
		int cursor = 0;
		
		char[][] matriz = new char[frase.length()][chaveList.length];
		
		
		for( int li = 0; li < linhas ; li ++ ){
			for( int col =0; col < colunas; col ++ ){
				if(cursor < frase.length()){
					matriz[li][col] = frase.charAt(cursor); 
				} else {
					matriz[li][col] = CORINGA;
				}
				cursor++;
			}
		}
		
		
		/// dividir o tamanho da palavra sem espaco para saber a quantidade de linhas da coluna
		
		printMatriz(matriz);
		
		return null;
	}

	public String decripta(String frase, int chave) {
		return null;
	}
	
	private char[] preencheLista(String chave){
		
		char[] col = new char[chave.length()]; 

		for(int c = 0; c < chave.length(); c ++){
			col[c] = chave.charAt(c);
		}
		
//		Arrays.sort(col);
		
		return col;
		
	}

	private void printMatriz(char[][] matriz){
		System.out.println(matriz.length);
		for(int i=0; i< matriz.length; i ++){
			for(int j=0; j< matriz.length; j ++){
				System.out.println(matriz[i][j]);
			}
			System.out.println("\n");
		}
	}
	
}
