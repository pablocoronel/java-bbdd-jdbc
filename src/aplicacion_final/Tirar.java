package aplicacion_final;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

// clase de prueba para adaptar el codigo de la documentacion de Java
public class Tirar {

	public static void main(String[] args) {
		// instancia del componente padre
		Marco mi_marco = new Marco();
		mi_marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(mi_marco);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
		}
	}

}

class Marco extends JFrame {
	public Marco() {
		setBounds(300, 300, 300, 300);
		setVisible(true);
	}
}
