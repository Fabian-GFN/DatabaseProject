package myDB;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import static myDB.DataManager.*;

public class Flugzeuge_Tabelle extends JTable {

	private static Object[] columns = {"FlugzeugNummer", "Typ", "Passagierplätze"};
	private static Object[][] rowData = new Object[flugzeuge.size()][];
	private static JScrollPane tableBox = new JScrollPane(new Flugzeuge_Tabelle());
	
	static {
		for (int i = 0; i < flugzeuge.size(); i++) {
			Flugzeug fl = flugzeuge.get(i);
			Object[] flugzeugDaten = {fl.getFlugzeugNr(), fl.getTyp(), fl.getPlaetze()};
			rowData[i] = flugzeugDaten;
		}
	}
	
	public Flugzeuge_Tabelle() {
		super(rowData, columns);
	}

	public static JScrollPane getTableBox() {
		return tableBox;
	}
}
