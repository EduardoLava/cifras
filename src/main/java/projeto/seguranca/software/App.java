package projeto.seguranca.software;

import java.util.Scanner;

import projeto.seguranca.software.criptografia.CifraTransposicao;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		// for(int i = 0; i< 255 ; i++){
		// System.out.println(((char)i));
		// }

		/*
		 * System.out.
		 * println("-- ----------------------- Cifra de cézar ------------------------"
		 * );
		 * 
		 * Scanner s = new Scanner(System.in);
		 * 
		 * System.out.println("Informe uma frase:"); String text = s.nextLine();
		 * 
		 * System.out.println("Informe uma chave numérica"); int chave =
		 * s.nextInt();
		 * 
		 * s.close();
		 * 
		 * CifraCezar cifraCezar = new CifraCezar();
		 * 
		 * String cifrado = "";
		 * 
		 * try{
		 * 
		 * cifrado = cifraCezar.encripta(text, chave);
		 * 
		 * System.out.println(text +" criptografado é "+cifrado); String
		 * decrifrado = cifraCezar.decripta(cifrado, chave);
		 * 
		 * System.out.println(text+ " criptografado é = "+cifrado+" e = a "
		 * +decrifrado+" descriptografado");
		 * 
		 * } catch (IllegalArgumentException e) { e.printStackTrace(); }
		 * 
		 * System.out.
		 * println("-- ----------------------- Cifra de cézar ------------------------"
		 * );
		 */

		Scanner s = new Scanner(System.in);

		System.out.println("Informe uma frase:");
		String text = s.nextLine();

		System.out.println("Informe uma chave");
		String chave = s.next();

		s.close();

		CifraTransposicao cifraTransposicao = new CifraTransposicao(chave);
		String cripto = cifraTransposicao.encripta(text).toString();
		System.out.println(text+" Criptografado é: "+cripto);

		System.out.println("\nDescriptografando....");
		
		String descripto = cifraTransposicao.decripta(cripto);
		
		System.out.println("Descriptografado: "+descripto);

	}
	
}
