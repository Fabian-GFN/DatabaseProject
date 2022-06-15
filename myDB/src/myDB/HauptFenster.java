package myDB;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class HauptFenster extends JFrame {

	private static HauptFenster instance;
	
	public HauptFenster() {
//		setBackground(new Color(121, 121, 121));
		setSize(1000, 1200);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		add(Piloten_Tabelle.getTableBox());
		add(Staedte_Tabelle.getTableBox());
		add(Flugzeuge_Tabelle.getTableBox());
		add(Fluege_Tabelle.getTableBox());
		add(MenueLeiste.getButtonBox());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		instance = this;
	}
	
	public static void formularAnzeigen(String formular) {
		switch (formular) {
			case "Pilot": instance.add(new Pilot_Formular());
			instance.revalidate();
			instance.repaint();
			instance.setVisible(true);
		}
	}
}
