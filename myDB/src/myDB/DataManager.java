package myDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {
	
	public static ArrayList<Pilot> piloten = new ArrayList<>();
	public static ArrayList<Flugzeug> flugzeuge = new ArrayList<>();
	public static ArrayList<Stadt> staedte = new ArrayList<>();
	public static ArrayList<Flug> fluege = new ArrayList<>();
	
	public static void datenLesen() throws SQLException{
		pilotenLaden();
		flugzeugeLaden();
		staedteLaden();
		fluegeLaden();
	}

	private static void fluegeLaden() throws SQLException {
		String statement = "SELECT * FROM flug";
		ResultSet result = Connector.commit(statement);
		while (result.next()) {
			Flug.fromDatabase(result);
		}
		result.close();
	}

	private static void staedteLaden() throws SQLException {
		String statement = "SELECT * FROM stadt";
		ResultSet result = Connector.commit(statement);
		while (result.next()) {
			Stadt.fromDatabase(result);
		}		
	}

	private static void flugzeugeLaden() throws SQLException {
		String statement = "SELECT * FROM flugzeug";
		ResultSet result = Connector.commit(statement);
		while (result.next()) {
			Flugzeug.fromDatabase(result);
		}		
	}

	private static void pilotenLaden() throws SQLException{
		String statement = "SELECT * FROM pilot";
		ResultSet result = Connector.commit(statement);
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

}
