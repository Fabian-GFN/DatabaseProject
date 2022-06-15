package myDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static myDB.DataManager.*;
import static myDB.Connector.*;

public class Flug extends DatabaseObject {

	private final String flugNr;
	private LocalDateTime abflug;
	private double dauer;
	private Flugzeug flugzeug;
	private Stadt von;
	private Stadt nach;
	private ArrayList<Pilot> flugPiloten;

	private Flug(String flugNr, LocalDateTime abflug, double dauer, Flugzeug flugzeug, Stadt von, Stadt nach,
			ArrayList<Pilot> flugPiloten) {
		this.flugNr = "'" + flugNr + "'";
		this.abflug = abflug;
		this.dauer = dauer;
		this.flugzeug = flugzeug;
		this.von = von;
		this.nach = nach;
		this.flugPiloten = flugPiloten;
		if (!fluege.contains(this)) {
			fluege.add(this);
		}
	}

	public LocalDateTime getAbflug() {
		return abflug;
	}

	public void setAbflug(LocalDateTime abflug) {
		this.abflug = abflug;
	}

	public double getDauer() {
		return dauer;
	}

	public void setDauer(double dauer) {
		this.dauer = dauer;
	}

	public Flugzeug getFlugzeug() {
		return flugzeug;
	}

	public void setFlugzeug(Flugzeug flugzeug) {
		this.flugzeug = flugzeug;
	}

	public Stadt getVon() {
		return von;
	}

	public void setVon(Stadt von) {
		this.von = von;
	}

	public Stadt getNach() {
		return nach;
	}

	public void setNach(Stadt nach) {
		this.nach = nach;
	}

	public ArrayList<Pilot> getFlugPiloten() {
		return flugPiloten;
	}

	public void setFlugPiloten(ArrayList<Pilot> flugPiloten) {
		this.flugPiloten = flugPiloten;
	}

	public String getFlugNr() {
		return flugNr;
	}

	@Override
	public void toDatabase() throws SQLException {
		String statement = "SELECT flugNr FROM Flug WHERE flugNr = " + this.getFlugNr();
		ResultSet result = commit(statement);

		if (result.next() == false) {
			statement = "INSERT INTO Flug VALUES (" + this.getFlugNr() + ", " + this.getAbflug() + ", "
					+ this.getDauer() + ", " + this.getFlugzeug().getFlugzeugNr() + ", " + this.getVon().getStadtNr()
					+ ", " + this.getNach().getStadtNr() + ")";
			commit(statement);

			for (Pilot p : this.getFlugPiloten()) {
				statement = "INSERT INTO flug_pilot VALUES (" + this.getFlugNr() + ", " + p.getPilotNr() + ")";
				commit(statement);
			}
		} else {
			statement = "UPDATE Flug SET Abflug = " + this.getAbflug() + ", dauer = " + this.getDauer() + ", flugzeug ="
					+ this.getFlugzeug().getFlugzeugNr() + ", von =" + this.getVon().getStadtNr() + ", nach ="
					+ this.getNach().getStadtNr() + " WHERE flugNr = " + this.getFlugNr();
			commit(statement);

			statement = "DELETE FROM flug_pilot WHERE flugNr = " + this.getFlugNr();
			commit(statement);

			for (Pilot p : this.getFlugPiloten()) {
				statement = "INSERT INTO flug_pilot VALUES (" + this.getFlugNr() + ", " + p.getPilotNr() + ")";
				commit(statement);
			}
		}
	}

	public static void fromDatabase(ResultSet result) throws SQLException {
		String flugNr = result.getString("flugNr");
		LocalDateTime abflug = LocalDateTime.parse(result.getString("abflug"));
		double dauer = result.getDouble("dauer");
		Flugzeug flugzeug = flugzeugAusListe(result.getString("flugzeugNr"));
		Stadt vonStadt = stadtAusListe(result.getInt("von"));
		Stadt nachStadt = stadtAusListe(result.getInt("nach"));
		ArrayList<Pilot> piloten = new ArrayList<>();

		String statement = "SELECT pilotNr FROM flug_Pilot WHERE flugNr = " + flugNr;
		ResultSet pilotenAusDatenbank = commit(statement);

		while (pilotenAusDatenbank.next()) {
			for (Pilot p : piloten) {
				if (p.getPilotNr() == pilotenAusDatenbank.getInt(0)) {
					piloten.add(p);
				}
			}
		}
		new Flug(flugNr, abflug, dauer, flugzeug, vonStadt, nachStadt, piloten);

	}

	@Override
	public String getDeleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsertStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
