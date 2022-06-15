package myDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import static myDB.Connector.*;
import static myDB.DataManager.*;

public abstract class DatabaseObject {
	
	public abstract String getDeleteStatement();
	public abstract String getInsertStatement();
	public abstract String getUpdateStatement();
	
	public void toDatabase() throws SQLException {
		if (exists(this)) {
			commit(getInsertStatement());
		} else {
			commit(getUpdateStatement());
		}
	};
	
	public void delete() throws SQLException {
		commit(getDeleteStatement());
		objektEntfernen(this);
	}
	
}
