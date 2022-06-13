package myDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// Diese Klasse soll die Verbindung zur Datenbank initialisieren
public class Connector {

	// Diese Strings ben�tigen wir f�r die Verbindung
	// jdbc:mysql ist der Treiber
	static String URL = "jdbc:mysql://Localhost:3306/fluege";
	static String user = "root";
	static String password = "fabian";
	
	public static void main(String[] args) {
		
		// Der Code zum initialisiern der Verbindung muss in einem "try-block"
		// stehen - die Methode getConnection() der Klasse DriverManager
		// gibt dies vor. Sie deklariert n�mlich eine sogenannte "checked Exception".
		
		
		try {
			// Connection ist die Klasse von Java f�r Datenbankverbindungen
			Connection conn = DriverManager.getConnection(URL, user, password);
			// Mit prepareStatement k�nnen wir einen SQL-Befehl per String speichern
			PreparedStatement ps = conn.prepareStatement("Select * From stadt");
			// Wenn wir das PreparedStatement ausf�hren, erhalten wir als antwort
			// ein "ResultSet" - im Prinzip handelt es sich um eine Tabelle
			ResultSet result = ps.executeQuery();
			
			// Mit next() springen wir in die n�chste Zeile der Ergebnistabelle
			while (result.next()) {
				System.out.println(result.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}