package myDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import static myDB.DataManager.*;

public class Stadt extends DatabaseObject {

	private final int stadtNr;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "'" + name + "'";
	}

	public int getStadtNr() {
		return stadtNr;
	}

	public Stadt(int stadtNr, String name) {
		this.stadtNr = stadtNr;
		this.setName(name);
		if (!staedte.contains(this)) {
			staedte.add(this);
		}
	}

	public static void fromDatabase(ResultSet result) throws SQLException {
		int stadtNr = result.getInt("stadtNr");
		String name = result.getString("name");
		new Stadt(stadtNr, name);
	}
	
	public boolean equals(Object o) {
		if (o instanceof Stadt) {
			return this.getStadtNr() == ((Stadt)o).getStadtNr();
		}
		return false;
	}

	@Override
	public String getDeleteStatement() {
		return "DELETE FROM stadt WHERE stadtNr = "+ this.getStadtNr();
	}

	@Override
	public String getInsertStatement() {
		return "INSERT INTO stadt VALUES ("
				+ this.getStadtNr() + ", " 
				+ this.getName() + ")";
	}

	@Override
	public String getUpdateStatement() {
		return "UPDATE stadt SET "
				+ "name = " + this.getName()
				+ " WHERE stadtNr = " +this.getStadtNr();
	}

}
