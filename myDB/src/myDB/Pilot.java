package myDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* Die Klasse Pilot repr�sentiert den Entit�tstypen Pilot aus unserem ERD
 Sie hat die selben Attribute wie die Tabelle Pilot in der Datenbank
 Ein Objekt der Klasse Pilot entspricht einem Datensatz (Zeile) der Tabelle*/

public class Pilot extends DatabaseObject {

	/* Attribute(Instanzvariablen) - pilotNr(Prim�rschl�ssel) soll nicht �nderbar sein */
	private final int pilotNr;
	private String nachname;
	private String vorname;
	
	/* Konstrukor ist private - Objecte m�ssen mit der Methode createPilot erzeugt werden */
	private Pilot(int pilotNr, String nachname, String vorname) {
		this.pilotNr = pilotNr;
		this.setNachname(nachname);
		this.setVorname(vorname);
	}

	/* Setter und Getter */
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
	
	/* �berschreibung der toString() aus Object, um den Namen komplett ausgeben zu lassen */
	public String toString() {
		return nachname +", "+ vorname;
	}
	
	/* �berschreibung der abstrakten Methode toDatabase() aus der Elternklasse 
	 * Die Methode pr�ft anhand des Prim�rschl�ssels, ob das Objekt (this)
	 * bereits in der Datenbank existiert. Ist dies nicht der Fall, wird
	 * es mit einem Insert-Befehl in die Datenbank eingef�gt.
	 * Ist das Objekt bereits vorhanden, werden alle Attribute au�er dem
	 * Prim�rschl�ssel aus dem Objekt in den entsprechenden Datensatz kopiert -
	 * nur falls sich etwas ge�ndert hat. */
	public void toDatabase() throws SQLException {
		String statement = "SELECT pilotNr FROM pilot WHERE pilotNr = "+ this.getPilotNr();
		PreparedStatement ps = Connector.conn.prepareStatement(statement);
		ResultSet result = ps.executeQuery();
		
		// next() gibt false zur�ck, wenn keine weitere Zeile in result existiert
		// ist die R�ckgabe beim ersten Aufruf bereits false, hat die Tabelle 0 Zeilen
		if (result.next() == false) { 
			statement = "INSERT INTO Pilot VALUES ("+this.pilotNr+", "+this.nachname+", "+this.vorname+")";
			ps.close();
			ps = Connector.conn.prepareStatement(statement);
			ps.execute();		
		} else {
			statement = "UPDATE Pilot SET nachname = "+this.nachname+", vorname = "+this.vorname+" WHERE PilotNr = "+this.getPilotNr();
			ps.close();
			ps = Connector.conn.prepareStatement(statement);
			ps.execute();
			
		}
		ps.close();
	}
	
	/* �berschreibung der abstrakten Methode delete  aus der Elternklasse
	   L�scht anhand eines Piloten den entsprechenden Datensatz aus der 
	   Datenbank und entfernt anschlie�end das Objekt aus der Pilotenliste */
	
	public void delete() throws SQLException {
		String statement = "DELETE FROM Pilot WHERE pilotNr = "+ this.getPilotNr();
		PreparedStatement ps = Connector.conn.prepareStatement(statement);
		ps.execute();
		ps.close();
		Connector.piloten.remove(this);
	}
	
	
	/* Statische Methode zum erstellen eines Piloten-Objektes anhand eines Datensatzes
	   Es muss darauf geachtet werden, zwischen einzelnen Aufrufen der Methode den Zeiger
	   des ResultSets mit next() zu verschieben, um in die n�chste Zeile zu springen */

	public static void fromDatabase(ResultSet result) throws SQLException {
		int pilotNr = result.getInt("pilotNr");
		String vorname = result.getString("vorname");
		String nachname = result.getString("nachname");
		Pilot p = new Pilot(pilotNr, nachname, vorname);
		Connector.piloten.add(p);
	}
	
	
	/* Statische Methode zum erzeugen eines Piloten mit Eingabedaten -
	   der Konstruktor soll nur aufgerufen werden, wenn der Prim�rschl�ssel
	   noch nicht existiert! */
	
	public static Pilot createPilot(int pilotNr, String nachname, String vorname) {
		
		for (Pilot p : Connector.piloten) {
			if (pilotNr == p.getPilotNr()) {
				System.out.println("Pilot mit dieser Nummer existiert bereits");
				return null;
			} 
		}
		
		Pilot pilot = new Pilot(pilotNr, nachname, vorname);
		Connector.piloten.add(pilot);
		return pilot;
	}
}
