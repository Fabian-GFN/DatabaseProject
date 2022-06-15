package myDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {
	
	public static ArrayList<Pilot> piloten;
	public static ArrayList<Flugzeug> flugzeuge;
	public static ArrayList<Stadt> staedte;
	public static ArrayList<Flug> fluege;
	
	public static void datenLesen() throws SQLException{
		piloten = new ArrayList<>();
		flugzeuge = new ArrayList<>();
		staedte = new ArrayList<>();
		fluege = new ArrayList<>();
		pilotenLaden();
		flugzeugeLaden();
		staedteLaden();
		fluegeLaden();
	}

	private static void fluegeLaden() throws SQLException {
		String statement = "SELECT * FROM flug";
		ResultSet result = Connector.commitWithResult(statement);
		while (result.next()) {
			Flug.fromDatabase(result);
		}
		result.close();
	}

	private static void staedteLaden() throws SQLException {
		String statement = "SELECT * FROM stadt";
		ResultSet result = Connector.commitWithResult(statement);
		while (result.next()) {
			Stadt.fromDatabase(result);
		}		
	}

	private static void flugzeugeLaden() throws SQLException {
		String statement = "SELECT * FROM flugzeug";
		ResultSet result = Connector.commitWithResult(statement);
		while (result.next()) {
			Flugzeug.fromDatabase(result);
		}		
	}

	private static void pilotenLaden() throws SQLException{
		String statement = "SELECT * FROM pilot";
		ResultSet result = Connector.commitWithResult(statement);
		while (result.next()) {
			Pilot.fromDatabase(result);
		}		
	}
	
	public static void objektEntfernen(DatabaseObject o) {
		piloten.remove(o);
		flugzeuge.remove(o);
		staedte.remove(o);
		fluege.remove(o);
	}
	
	public static Flugzeug flugzeugAusListe(String flugzeugNr) {
		for (Flugzeug flzg : flugzeuge) {
			if (flzg.getFlugzeugNr().equals(flugzeugNr)) {
				return flzg;
			}
		}
		return null;
	}
	
	public static Stadt stadtAusListe(int stadtNr) {
		for (Stadt st : staedte) {
			if (st.getStadtNr() == stadtNr) {
				return st;
			}
		}
		return null;
	}
	
	public static Pilot pilotAusListe(int pilotNr) {
		for (Pilot p : piloten) {
			if (p.getPilotNr() == pilotNr) {
				return p;
			}
		}
		return null;
	}

}
