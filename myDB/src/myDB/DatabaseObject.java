package myDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseObject {
	
	public abstract void toDatabase() throws SQLException;
	
	public abstract void delete() throws SQLException;
	
}
