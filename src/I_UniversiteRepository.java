import java.sql.SQLException;


public interface I_UniversiteRepository {

	abstract Universite GetById(int universityId) throws SQLException;

	abstract int GetNbLivreMensuel(Universite univ) throws SQLException;

}