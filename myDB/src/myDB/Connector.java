package myDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


// Diese Klasse soll die Verbindung zur Datenbank initialisieren
public class Connector {

	// Diese Strings ben�tigen wir f�r die Verbindung
	// jdbc:mysql ist der Treiber
	static String URL = "jdbc:mysql://Localhost:3306/fluege";
	static String user = "root";
	static String password = "fabian";
	static Connection conn;
	
	public static void main(String[] args) {
		
		// Der Code zum initialisiern der Verbindung muss in einem "try-block"
		// stehen - die Methode getConnection() der Klasse DriverManager
		// gibt dies vor. Sie deklariert n�mlich eine sogenannte "checked Exception".
		
		
		try {
			// Connection ist die Klasse von Java f�r Datenbankverbindungen
			conn = DriverManager.getConnection(URL, user, password);
			// Mit prepareStatement k�nnen wir einen SQL-Befehl per String speichern
			PreparedStatement ps = conn.prepareStatement("Select * From pilot");
			// Wenn wir das PreparedStatement ausf�hren, erhalten wir als antwort
			// ein "ResultSet" - im Prinzip handelt es sich um eine Tabelle
			ResultSet result = ps.executeQuery();
			
			ArrayList<Pilot> piloten = new ArrayList<>();
			
			// Mit next() springen wir in die n�chste Zeile der Ergebnistabelle
			while (result.next()) {
				
				int pilotNr = result.getInt("pilotNr");
				String vorname = result.getString("vorname");
				String nachname = result.getString("nachname");
				Pilot p = new Pilot(pilotNr, nachname, vorname);
				piloten.add(p);
			}
			
			for (Pilot p : piloten) {
				System.out.println(p);
			}
			
			Pilot pilot = new Pilot(7, "Schulte", "Franz");
			piloten.add(pilot);
			
			for (Pilot p : piloten) {
				p.toDataBase();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}