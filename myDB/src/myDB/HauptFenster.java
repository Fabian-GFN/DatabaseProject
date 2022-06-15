package myDB;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class HauptFenster extends JFrame {

	public HauptFenster() {
//		setBackground(new Color(121, 121, 121));
		setSize(900, 900);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		add(Piloten_Tabelle.getTableBox());
		add(Staedte_Tabelle.getTableBox());
		add(Flugzeuge_Tabelle.getTableBox());
		add(Fluege_Tabelle.getTableBox());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}
