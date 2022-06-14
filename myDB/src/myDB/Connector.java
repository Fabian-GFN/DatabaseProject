package myDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


// Diese Klasse soll die Verbindung zur Datenbank initialisieren
public class Connector {

	// Diese Strings benötigen wir für die Verbindung
	// jdbc:mysql ist der Treiber
	static String URL = "jdbc:mysql://Localhost:3306/fluege";
	static String user = "root";
	static String password = "fabian";
	static Connection conn;
	static ArrayList<Pilot> piloten;
	public static ArrayList<Stadt> staedte;
	
	public static void main(String[] args) {
		
		// Der Code zum initialisiern der Verbindung muss in einem "try-block"
		// stehen - die Methode getConnection() der Klasse DriverManager
		// gibt dies vor. Sie deklariert nämlich eine sogenannte "checked Exception".
		
		
		try {
			// Connection ist die Klasse von Java für Datenbankverbindungen
			conn = DriverManager.getConnection(URL, user, password);
			// Mit prepareStatement können wir einen SQL-Befehl per String speichern
			PreparedStatement ps = conn.prepareStatement("Select * From stadt");
			// Wenn wir das PreparedStatement ausführen, erhalten wir als antwort
			// ein "ResultSet" - im Prinzip handelt es sich um eine Tabelle
			ResultSet result = ps.executeQuery();
			
			staedte = new ArrayList<>();
			
			// Mit next() springen wir in die nächste Zeile der Ergebnistabelle
			while (result.next()) {
				Stadt.fromDatabase(result);				
			}
			
			for (Stadt st : staedte) {
				System.out.println(st.getName());
			}
			
			staedte.get(0).delete();
			Stadt.createStadt(7, "Lissabon");
			
			for (Stadt st : staedte) {
				System.out.println(st.getName());
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}