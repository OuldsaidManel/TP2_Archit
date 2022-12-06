
import java.sql.SQLException;
import java.sql.Statement;

public class UniversiteRepository {
public class UniversiteRepository implements I_UniversiteRepository{

private IJournal journal;

	Universite GetById(int universityId) throws SQLException {
	public UniversiteRepository(IJournal comp) 
	{
		this.journal = comp;
	}

	@Override
	public Universite GetById(int universityId) throws SQLException {

		DBConnection BD= DBConnection.getDBConnection();
		Connection connect=BD.getConn(); 
		Connection connect = DBConnection.getConn();       //DBConnection -> Singleton 
		Statement stmt = connect.createStatement();
		System.out.println("LogBD : d�but recherche de id universit� dans la base de donn�e");

		AffichageEnrichir.setSender("UniversiteRepository");

		journal.outPut_Msg("LogBD : d�but recherche de id universit� dans la base de donn�e");

		String sql = "select * from universite where i_univ="+ universityId;
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();	

		TypePackage p=TypePackage.valueOf(rs.getString(3));
		Universite u = new Universite (rs.getInt(1),rs.getString(2),p);

		System.out.println("LogBD : universit� r�cup�r�e");
		journal.outPut_Msg("LogBD : universit� r�cup�r�e");

		//connect.close(); C'est mieux de garder la connection ouverte (Singleton)
		//connect.close();
		return u;	


	}	
	
}