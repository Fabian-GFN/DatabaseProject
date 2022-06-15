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
		if (!exists(this)) {
			commitWithoutResult(getInsertStatement());
		} else {
			commitWithoutResult(getUpdateStatement());
		}
	};
	
	public void delete() throws SQLException {
		commitWithoutResult(getDeleteStatement());
		objektEntfernen(this);
	}
	
}
