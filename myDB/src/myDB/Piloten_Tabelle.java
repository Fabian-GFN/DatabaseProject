package myDB;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import static myDB.DataManager.*;

public class Piloten_Tabelle extends JTable {
	
	static Object[] columns = {"PilotenNummer", "Nachname", "Vorname"};
	static Object[][] rowData = new Object[piloten.size()][];
	private static JScrollPane tableBox = new JScrollPane(new Piloten_Tabelle());
	
	static {
		for (int i = 0; i < piloten.size(); i++) {
			Pilot p = piloten.get(i);
			Object[] pilotDaten = {p.getPilotNr(), p.getNachname(), p.getVorname()};
			rowData[i] = pilotDaten;
		}
	}

	public Piloten_Tabelle() {
		super(rowData, columns);
	}

	public static JScrollPane getTableBox() {
		return tableBox;
	}
	
	

}
