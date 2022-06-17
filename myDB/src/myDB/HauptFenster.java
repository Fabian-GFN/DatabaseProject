package myDB;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class HauptFenster extends JFrame {

	private static HauptFenster instance;

	public HauptFenster() {
//		setBackground(new Color(121, 121, 121));
		setSize(800, 600);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		add(Piloten_Tabelle.getTableBox());
		add(Staedte_Tabelle.getTableBox());
		add(Flugzeuge_Tabelle.getTableBox());
		add(Fluege_Tabelle.getTableBox());
		add(MenueLeiste.getButtonBox());
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		instance = this;
	}

	public static void formularAnzeigen(String formular) {
		switch (formular) {
		case "Pilot":
			new Pilot_Formular();
			break;
		case "Stadt":
			new Stadt_Formular();
			break;
		case "FLugzeug":
			new Flugzeug_Formular();
			break;
		case "Flug":
			new Flug_Formular();
		}
	}
	
	public static void update() {
		instance.getContentPane().removeAll();
		instance.add(Piloten_Tabelle.getTableBox());
		instance.add(Staedte_Tabelle.getTableBox());
		instance.add(Flugzeuge_Tabelle.getTableBox());
		instance.add(Fluege_Tabelle.getTableBox());
		instance.add(MenueLeiste.getButtonBox());
		instance.revalidate();
	}
}
