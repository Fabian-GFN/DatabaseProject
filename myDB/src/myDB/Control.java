package myDB;

import java.sql.SQLException;

public class Control {

	public static void main(String[] args) {
		try {
			Connector.init();
			DataManager.datenLesen();
			
			HauptFenster fenster = new HauptFenster();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hey!");
	}

}
