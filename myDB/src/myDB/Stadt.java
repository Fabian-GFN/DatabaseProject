package myDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stadt extends DatabaseObject {

	private final int stadtNr;
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = "'" + name + "'";
	}

	/**
	 * @return the stadtNr
	 */
	public int getStadtNr() {
		return stadtNr;
	}

	private Stadt(int stadtNr, String name) {
		this.stadtNr = stadtNr;
		this.setName(name);
	}

	@Override
	public void toDatabase() throws SQLException {
		String statement = "SELECT stadtNr FROM stadt WHERE stadtNr = " + this.getStadtNr();
		PreparedStatement ps = Connector.conn.prepareStatement(statement);
		ResultSet result = ps.executeQuery();

		if (result.next() == false) {
			statement = "INSERT INTO Stadt VALUES (" + this.stadtNr + ", " + this.name + ")";
			ps.close();
			ps = Connector.conn.prepareStatement(statement);
			ps.execute();
		} else {
			statement = "UPDATE Stadt SET name = " + this.getName() + " WHERE stadtNr = " + this.getStadtNr();
			ps.close();
			ps = Connector.conn.prepareStatement(statement);
			ps.execute();

		}
		ps.close();

	}

	@Override
	public void delete() throws SQLException {
		String statement = "DELETE FROM Stadt WHERE stadtNr = " + this.getStadtNr();
		PreparedStatement ps = Connector.conn.prepareStatement(statement);
		ps.execute();
		ps.close();
		Connector.staedte.remove(this);
	}

	public static void fromDatabase(ResultSet result) throws SQLException {
		int stadtNr = result.getInt("stadtNr");
		String name = result.getString("name");
		Stadt st = new Stadt(stadtNr, name);
		Connector.staedte.add(st);
	}

	public static Stadt createStadt(int stadtNr, String name) {
		/* Aufgabe dieser Schleife ist lediglich, die Methode abzubrechen, sobald
		   eine Stadt mit derselben Nr gefunden wurde! */
		for (Stadt st : Connector.staedte) {
			if (stadtNr == st.getStadtNr()) {
				System.out.println("Stadt mit dieser Nummer existiert bereits");
				return null;
			}
		}

		Stadt stadt = new Stadt(stadtNr, name);
		Connector.staedte.add(stadt);
		return stadt;
	}

}
