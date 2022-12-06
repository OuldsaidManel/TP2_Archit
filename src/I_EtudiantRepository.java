import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public interface I_EtudiantRepository  {

    I_EtudiantRepository StudRep = new EtudiantRepository(); //Inversion de dépendance 

	void add(Etudiant E) throws SQLException;

    abstract void add(Etudiant E) throws SQLException;

	boolean Exists(int matricule) throws SQLException;
    abstract boolean Exists(int matricule) throws SQLException;

	boolean Exists(String email) throws SQLException;
    abstract boolean Exists(String email) throws SQLException;

}