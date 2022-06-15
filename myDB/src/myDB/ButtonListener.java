package myDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		
		Object button = event.getSource();
		String name = null;
		if (button instanceof JButton) {
			name = ((JButton)button).getText();
		}
		switch (name) {
		case "Datensatz anlegen":
			String[] selections = {"Pilot", "Flugzeug", "Stadt", "Flug"};
			String selection = (String)JOptionPane.showInputDialog(null, "Welche Art von Datensatz soll angelegt werden?", name, 0, null, selections, selections[0]);
			HauptFenster.formularAnzeigen(selection);
			break;
		case "Datensatz löschen":
			System.out.println("Das kitzelt!");
			break;
		case "Datensatz ändern":
			System.out.println("Bitte aufhören!");
		}
		
		
	}



}
