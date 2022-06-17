package myDB;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;

public class SubmitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		Pilot_Formular.close();
		Component component = (Component)event.getSource();
		Container parent = component.getParent();
		JTextField textfield = (JTextField)parent.getComponent(2);
		int pilotNr = Integer.parseInt(textfield.getText());
		textfield = (JTextField)parent.getComponent(4);
		String nachname = textfield.getText();
		textfield = (JTextField)parent.getComponent(6);
		String vorname = textfield.getText();
		try {
			Pilot p = new Pilot(pilotNr, nachname, vorname);
			p.toDatabase();
			DataManager.datenLesen();
			HauptFenster.update();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
