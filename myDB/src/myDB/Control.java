package myDB;

import java.sql.SQLException;

public class Control {

	public static void main(String[] args) {
		try {
			Connector.init();
			DataManager.datenLesen();
			
			System.out.println("Flugzeuge: ");
			for (Flugzeug f : DataManager.flugzeuge) {
				System.out.println(f.getTyp());
			}
			System.out.println("Staedte: ");
			for (Stadt st : DataManager.staedte) {
				System.out.println(st.getName());
			}
			System.out.println("Piloten: ");
			for (Pilot p : DataManager.piloten) {
				System.out.println(p);
			}
			System.out.println("Fluege: ");
			for (Flug f : DataManager.fluege) {
				System.out.println(f.getFlugNr());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
