package projeto.seguranca.software.gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Panel;

import javax.swing.JDialog;

public abstract class AbstractWindow extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6144051020205771749L;

	public AbstractWindow(String title) {
		super();
		inicializaLayout();
		criaLayout();
		setTitle(title);
		pack();
		setVisible(true);
	}

	private void inicializaLayout(){
		this.setLayout( new GridBagLayout());
		this.setPreferredSize(new Dimension(400, 500));
		
		this.panelGeral = new Panel();
		this.panelGeral.setLayout(new GridBagLayout());
		this.add(panelGeral);
	}
	
	protected abstract void criaLayout();
	
	protected Panel panelGeral;
	
	
	
	
}
