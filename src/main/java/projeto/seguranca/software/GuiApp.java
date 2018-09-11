package projeto.seguranca.software;

import projeto.seguranca.software.gui.impl.WelcomeWindow;

public class GuiApp {

	public static void main(String[] args) {
		new GuiApp().inicializaChat();
	}
	
	
	private void inicializaChat(){
		
		new WelcomeWindow();
		
	}
	
}
