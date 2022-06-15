package myDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import static myDB.DataManager.*;

public class Flugzeug extends DatabaseObject {

	private final String flugzeugNr;
	private String typ;
	private int plaetze;
	
	public Flugzeug(String flugzeugNr, String typ, int plaetze) {
		this.flugzeugNr = "'"+flugzeugNr+"'";
		this.setTyp(typ);
		this.setPlaetze(plaetze);
		if (!flugzeuge.contains(this)) {
			flugzeuge.add(this);
		}
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = "'"+typ+"'";
	}

	public int getPlaetze() {
		return plaetze;
	}

	public void setPlaetze(int plaetze) {
		this.plaetze = plaetze;
	}

	public String getFlugzeugNr() {
		return flugzeugNr;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Flugzeug) {
			return this.flugzeugNr.equals(((Flugzeug)o).getFlugzeugNr());
		}
		return false;
	}
	
	public static void fromDatabase(ResultSet result) throws SQLException {
		String flugzeugNr = result.getString("flugzeugNr");
		String typ = result.getString("typ");
		int plaetze = result.getInt("plaetze");
		new Flugzeug(flugzeugNr, typ, plaetze);
	}

	@Override
	public String getDeleteStatement() {
		return "DELETE FROM flugzeug WHERE flugzeugNr = "+this.getFlugzeugNr();
	}

	@Override
	public String getInsertStatement() {
		return "INSERT INTO flugzeug VALUES ("
				+ this.getFlugzeugNr() + ", "
				+ this.getTyp() + ", "
				+ this.getPlaetze() + ")";
	}

	@Override
	public String getUpdateStatement() {
		return "UPDATE flugzeug SET "
				+ "typ = "+ this.getTyp() + ", "
				+ "plaetze = " +this.getPlaetze()
				+ " WHERE flugzeugNr = "+this.getFlugzeugNr();
	}

}
