
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
@@ -10,12 +9,12 @@ public class UniversiteRepository {

	Universite GetById(int universityId) throws SQLException {

		DBConnection BD= new DBConnection();
		DBConnection BD= DBConnection.getDBConnection();
		Connection connect=BD.getConn(); 
		Statement stmt = connect.createStatement();
		System.out.println("LogBD : d�but recherche de id universit� dans la base de donn�e");

		String sql = "select * from universite where id_universite="+ universityId;
		String sql = "select * from universite where i_univ="+ universityId;
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();	
		TypePackage p=TypePackage.valueOf(rs.getString(3));
		Universite u = new Universite (rs.getInt(1),rs.getString(2),p);
			
		System.out.println("LogBD : universit� r�cup�r�e");
		
		connect.close();
		return u;	
	
		
	}	
	
}