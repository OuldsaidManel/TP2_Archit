import java.sql.SQLException;
public interface I_EtudiantRepository  {
		
	
    abstract void add(Etudiant E) throws SQLException;
    abstract boolean Exists(int matricule) throws SQLException;

    abstract boolean Exists(String email) throws SQLException;

    abstract boolean Verification (int matricule, String email) throws SQLException;

}