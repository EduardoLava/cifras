package projeto.seguranca.software.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MyGrigLayout extends GridBagConstraints{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7738643131326376639L;

	public MyGrigLayout() {
		super();
		this.insets = new Insets(1, 1, 1, 1);
	}
	
	public MyGrigLayout setPosicao(int linha, int coluna){
		this.gridy = linha;
		this.gridx = coluna;
		return this;
	}
	
	public MyGrigLayout alinharDireita(){
		this.anchor = GridBagConstraints.EAST;
		return this;
	}
	
	public MyGrigLayout alinharEsquerda(){
		this.anchor = GridBagConstraints.WEST;
		return this;
	}
	
}
