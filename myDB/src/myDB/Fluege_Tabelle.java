package myDB;
import static myDB.DataManager.fluege;

import javax.swing.*;

public class Fluege_Tabelle extends JTable {
	
	private static Object[] columns = {"FlugNummer", "Abflug_Datum", "Flugdauer", "FlugzeugNummer", "Fliegt_von", "Fliegt_nach", "Piloten"};
	private static Object[][] rowData = new Object[fluege.size()][];
	private static JScrollPane tableBox = new JScrollPane(new Fluege_Tabelle());
	
	static {
		for (int i = 0; i < fluege.size(); i++) {
			Flug fl = fluege.get(i);
			Object[] flugDaten = {fl.getFlugNr(), fl.getAbflug(), fl.getDauer(), fl.getFlugzeug().getFlugzeugNr(), fl.getVon().getName(), fl.getNach().getName(), fl.getFlugPiloten()};
			rowData[i] = flugDaten;
		}
	}
	
	public Fluege_Tabelle() {
		super(rowData, columns);
	}

	public static JScrollPane getTableBox() {
		return tableBox;
	}
}
