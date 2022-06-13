package myDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pilot {

	private int pilotNr;
	private String nachname;
	private String vorname;
	
	public Pilot(int pilotNr, String nachname, String vorname) {
		this.pilotNr = pilotNr;
		this.setNachname("'"+nachname+"'");
		this.setVorname("'"+vorname+"'");
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getPilotNr() {
		return pilotNr;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String toString() {
		return nachname +", "+ vorname;
	}
	
	public void toDataBase() throws SQLException {
		PreparedStatement ps = Connector.conn.prepareStatement("SELECT pilotNr FROM pilot WHERE pilotNr = "+ this.getPilotNr());
		ResultSet result = ps.executeQuery();
		
		if (result.next() == false) {
			ps = Connector.conn.prepareStatement("INSERT INTO Pilot VALUES ("+this.pilotNr+", '"+this.nachname+"', '"+this.vorname+"')");
			ps.execute();
		} else {
			
		}
	}
}
