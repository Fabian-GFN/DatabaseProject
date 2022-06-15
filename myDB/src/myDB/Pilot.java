package myDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import static myDB.DataManager.*;

/* Die Klasse Pilot repräsentiert den Entitätstypen Pilot aus unserem ERD
 Sie hat die selben Attribute wie die Tabelle Pilot in der Datenbank
 Ein Objekt der Klasse Pilot entspricht einem Datensatz (Zeile) der Tabelle*/

public class Pilot extends DatabaseObject {

	private final int pilotNr;
	private String nachname;
	private String vorname;
	
	public Pilot(int pilotNr, String nachname, String vorname) throws SQLException {
		this.pilotNr = pilotNr;
		this.setNachname(nachname);
		this.setVorname(vorname);
		if (!piloten.contains(this)) {
			piloten.add(this);
		}
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = "'"+nachname+"'";
	}

	public int getPilotNr() {
		return pilotNr;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = "'"+vorname+"'";
	}
	
	public String toString() {
		return nachname +", "+ vorname;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pilot) {
			return this.getPilotNr() == ((Pilot)o).getPilotNr();
		}
		return false;
	}
	
	public static void fromDatabase(ResultSet result) throws SQLException {
		int pilotNr = result.getInt("pilotNr");
		String vorname = result.getString("vorname");
		String nachname = result.getString("nachname");
		new Pilot(pilotNr, nachname, vorname);	
	}

	@Override
	public String getDeleteStatement() {
		return "DELETE FROM pilot WHERE pilotNr = "+ this.getPilotNr();
	}

	@Override
	public String getInsertStatement() {
		return "INSERT INTO pilot VALUES ("
				+ this.getPilotNr() + ", "
				+ this.getNachname() + ", "
				+ this.getVorname() + ")";
	}

	@Override
	public String getUpdateStatement() {
		return "UPDATE pilot SET "
				+ "nachname = " + this.getNachname() 
				+ ", vorname = " + this.getVorname()
				+ " WHERE pilotNr = "+this.getPilotNr();
	}
}
