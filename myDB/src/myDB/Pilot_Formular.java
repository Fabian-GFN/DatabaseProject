package myDB;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Pilot_Formular extends JPanel {

	String[] labels = {"PilotenNummer", "Nachname", "Vorname"};
	public Pilot_Formular() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		for (String s : labels) {
			JLabel label = new JLabel(s);
			JTextField textField = new JTextField();
			textField.setName(s);
			add(label);
			add(textField);
		}

	}
}
