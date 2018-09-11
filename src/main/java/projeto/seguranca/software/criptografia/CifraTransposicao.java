package projeto.seguranca.software.criptografia;

import java.util.Map;
import java.util.TreeMap;

import projeto.seguranca.software.interfaces.IAlgoritomoCriptografia;

public class CifraTransposicao implements IAlgoritomoCriptografia {

	private int linhas;
	private int colunas;
	
	private char CORINGA = '=';

	private final String chave;
	
	public CifraTransposicao(String chave) {
		this.chave = chave.toLowerCase();
	}

	/**
	 * returna o numero de linhas necessarias para criar a matriz
	 * 
	 * @param colunas
	 * @param frase
	 * @return
	 */
	private int getNumeroLinhas(int colunas, char[] frase){
		
		if(frase.length % colunas == 0){
			return Math.round((frase.length / colunas));
		}
		this.linhas = (int) Math.round((frase.length / colunas) + 0.5d); 
		return this.linhas;
	}
	
	/**
	 * Cria uma matriz através de uma chave
	 * @param frase
	 * @return
	 */
	private char[][] criarMatriz(String frase) {
		
		frase = frase.replaceAll(" ", String.valueOf(CORINGA));
		char[] charList = frase.toCharArray();
		
		this.colunas = chave.length();
		this.linhas = getNumeroLinhas(colunas, charList);
		
		// instacia matriz
		char[][] matriz = new char[this.linhas][this.colunas];
		
		//Inicializa
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				matriz[i][j]= CORINGA;
			}
		}
		
		for (int linha = 0, posicaoFrase = 0; linha < this.linhas; linha++){
			for (int coluna = 0; coluna < this.colunas && posicaoFrase < charList.length; coluna++, posicaoFrase++){
				matriz[linha][coluna] = charList[posicaoFrase]; // variavel b anda pelo vetor de entrada,
//				System.out.print(frase[posicaoFrase]);
				// para colocar os valores na matriz
			}
			
//			System.out.println("");
			
		}
		
//		printMatriz(matriz);
		
		return matriz;
		
	}
	
	private char[][] criarMatrizInversa(String frase, String chave) {
		
		frase = frase.replaceAll(" ", String.valueOf(CORINGA));
		char[] charList = frase.toCharArray();
		
		this.colunas = chave.length();
		this.linhas = getNumeroLinhas(colunas, charList);
		
		// instacia matriz
		char[][] matriz = new char[this.linhas][this.colunas];
		
		//Inicializa
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				matriz[i][j]= CORINGA;
			}
		}
		
		for (int col = 0, posicaoFrase = 0; col < this.colunas; col++){
			for (int linha = 0; linha < this.linhas && posicaoFrase < charList.length; linha++, posicaoFrase++){
				matriz[linha][col] = charList[posicaoFrase]; // variavel b anda pelo vetor de entrada,
//				System.out.print(frase[posicaoFrase]);
				// para colocar os valores na matriz
			}
			
		}
		
//		printMatriz(matriz);
		
		return matriz;
		
	}
	
	/**
	 * 
	 * @param matriz
	 * @param chave
	 * @return
	 */
	private Map<String, char[]> gerarMap(char [][] matriz){
		
		char[] chaveList = chave.toCharArray();
		
		Map<String, char[]> ordenado = new TreeMap<String, char[]>();
		
		for (int col = 0; col < chaveList.length; col++) {
			char[] coluna = new char[this.linhas];
			
			for (int linhaMatriz = 0; linhaMatriz < this.linhas; linhaMatriz++) {
				coluna[linhaMatriz] = matriz[linhaMatriz][col];
			}
			
			ordenado.put(String.valueOf(chaveList[col]), coluna);
			
		}
		
		return ordenado;
		
	}
	
	/**
	 * Ordena as colunas da matriz de acordo com a ordem afabética 
	 * das letras da chave informada 
	 * 
	 * @param matriz
	 * @param chave
	 * @return
	 */
	private String ordenaMatrizPorChave(char[][] matriz){
		
		String encriptado = "";
		
		Map<String, char[]> ordenado = gerarMap(matriz);
		// ordena através de um tree map
		for(String key: ordenado.keySet()){
			
//			System.out.println("key: "+key);
			
			char[] values = ordenado.get(key);
			
			for(int i = 0; i < values.length; i++){
				
//				System.out.println(values[i]);
				encriptado+=String.valueOf(values[i]);
				
			}
		}
		
		return encriptado;
	}
	
	/**
	 * Printa a matriz 
	 * 
	 * @param matriz
	 */
	@SuppressWarnings("unused")
	private void printMatriz(char[][] matriz){
		for (int linha = 0; linha < linhas; linha++) {
			System.out.println("");
			for (int coluna = 0; coluna < colunas; coluna++) {
				System.out.print(matriz[linha][coluna]);
			}
		}
		System.out.println("\n\n\n");
	}
	
	/**
	 * Cria uma lista baseando-se na matriz
	 * @param matriz
	 * @return
	 */
//	private char[] matrizToArray(char[][] matriz, String chave) {
//
//		char[] chaveArray = chave.toCharArray();
//		
//		colunas = chaveArray.length;
//		linhas = (int) Math.round((matriz.length / chaveArray.length) + 0.5d);
//		
//		//recebe a matriz, escreve a transposta em um array de chars, retornando esse array.
//		char[] listaChars = new char[this.linhas*this.colunas];
//        
//		
//		
//        for(int c = 0, a = 0; c < chaveArray.length; c++){
//            for(int l = 0; l < this.linhas && a < listaChars.length; l++, a++){
//            	//fazendo a transposta, primeiro escreve as linhas.
//            	listaChars[a] = matriz[c][l];
//            }
//        }
//        
//        return listaChars;
//    }
	
	
	/**
	 * Criptografa uma String com a cifra de transposição
	 * 
	 * 
	 * Obs: a chave não pode conter a mesma letra mais de uma vez 
	 * 
	 * @param frase
	 * @return
	 */
	@Override
	public String encripta(String frase){
		
		char[][] matriz = criarMatriz(frase);
		
		return this.ordenaMatrizPorChave(matriz);
		
//        charList = matrizToArray(criarMatriz(charList, chave), chave);
        
//        return Arrays.toString(charList);
    }
	
	/**
	 * Descriptografa utilizando a cifra de transposição
	 * 
	 * Obs: é necessário que a chave seja a mesma que foi informada para realizar a criptografia
	 * 
	 * @param fraseCriptografada
	 * @param chave
	 * @return
	 */
	@Override
	public String decripta(String fraseCriptografada){
		
		String descriptografada = "";
		// quantidade de linhas que a matriz possui
		int colunasNecessarias = getNumeroLinhas(chave.length(), fraseCriptografada.toCharArray());
		
		char[][] matriz = this.criarMatrizInversa(fraseCriptografada, chave);
		
		Map<String, char[]> mapa = new TreeMap<>();
		Map<String, char[]> mapaChars = new TreeMap<>();

		// insiro em um map para ordenar minha chave
		for(int i=0; i< chave.length(); i ++){
			mapa.put(String.valueOf(chave.charAt(i)), null);
		}
			
		// com a chave ordenada, pego e insiro na mesma ordem as colunas da matriz
		int i = 0;
		for(String key: mapa.keySet()){
//			System.out.println("chave: "+ key);
			// pego todos os char de uma determinada linha da matriz e vou inserir nessa lista de chars
			char[] colunaMatriz = new char[colunasNecessarias];
			
			for(int linhaMatriz = 0; linhaMatriz < colunasNecessarias; linhaMatriz++){
				colunaMatriz[linhaMatriz] = matriz[linhaMatriz][i];
//				System.out.println(matriz[linhaMatriz][i]);
			}
			// in
			mapaChars.put(key, colunaMatriz);
			i++;
		}
		
		linhas = colunasNecessarias;
		// percorro a chave pegando os caracteres da frase através
		// pego primeiro todos os caracteres da primeira linha, depois todos da segunda e assim por diante
		// montando a frase
		for(int linha = 0; linha < linhas; linha ++){
			for(int k = 0; k < chave.length(); k ++){
				char[] chars = mapaChars.get(String.valueOf(chave.charAt(k)));
//				System.out.print(chars[linha]);
				descriptografada += chars[linha];
				
			}
		}
		
		descriptografada = descriptografada.replaceAll(String.valueOf(CORINGA), " ").trim();
		
//		System.out.println(descriptografada);
		
		return descriptografada;
	}
	
	/*
	public String descriptografar(String texto, int chave) throws IOException{
		
		char[] charList = texto.toCharArray();
		
        this.linhas = chave;// pra descriptografar, é o processo contrario a chave vira as linhas.
        this.colunas = charList.length/this.linhas;
        charList = matrizToArray(criarMatriz(charList, chave));//Primeiro cria a matriz, dps faz a transposta da transpota, que é a propria matriz original
        
        return charList.toString();
        
    }
	*/

}
