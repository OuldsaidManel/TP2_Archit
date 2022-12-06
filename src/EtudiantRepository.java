
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class EtudiantRepository {
public class EtudiantRepository implements I_EtudiantRepository  {  


	void add(Etudiant E) throws SQLException
	public void add(Etudiant E) throws SQLException
	{

		DBConnection BD= new DBConnection();
		DBConnection BD= DBConnection.getDBConnection(); //DBConnection -> Singleton  
		Connection connect=BD.getConn();

		Statement stmt = connect.createStatement();
		String sql = "INSERT into etudiant values (" + E.getMatricule() + ",'" + E.getNom() + "','" + E.getPrenom() + "','" + E.getEmail() + "'," +E.getNbLivreMensuel_Autorise() + "," +E.getNbLivreEmprunte() + "," +E.getId_universite()+")";
		// J'ai ajouté E.getPwd() dans sql
		String sql = "INSERT into etudiant values (" + E.getMatricule() + ",'" + E.getNom() + "','" + E.getPrenom() + "','" + E.getEmail() + "','" + E.getPwd() + "'," +E.getNbLivreMensuel_Autorise() + "," +E.getNbLivreEmprunte() + "," +E.getId_universite()+")";
		int rs = stmt.executeUpdate(sql);

		if (rs == 1){
@@ -25,43 +27,58 @@ void add(Etudiant E) throws SQLException
		connect.close();
	 }


	boolean Exists(String email) throws SQLException	
	public boolean Exists(int mat) throws SQLException	
	{
		DBConnection BD= new DBConnection();
		DBConnection BD= DBConnection.getDBConnection(); //DBConnection -> Singleton  
		Connection connect=BD.getConn();

		Statement stmt = connect.createStatement();
		String sql = "select * from etudiant where email='"+ email+"'";
		boolean rs = stmt.execute(sql);
		String sql = "select * from etudiant where matricule=" + mat;
		ResultSet rs = stmt.executeQuery(sql); //J'ai remplacé stmt.execute(sql);

		if (rs){
			System.out.println("logBD--- :email existe dans la BD  " + email);
			connect.close();

		if (rs.next()) //If ResultSet rs is not empty
	      {       
	        System.out.print("logBD--- :l'étudiant avec ce matricule existe déja dans la BD :\n ");
	        System.out.format("(%d, %s, %s, %s, %s, %s)\n", rs.getInt("matricule"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("pwd"), rs.getInt("nbLivreMensuel_Autorise"),rs.getInt("nbLivreEmprunte"),rs.getInt("id_universite"));
	        connect.close();
			return true;
			}
		System.out.println("logBD--- : email n'existe pas " + email);	
		connect.close();
		return false;
	      }

		else  //If ResultSet rs is empty
		{
			System.out.println("logBD----: l'étudiant avec ce matricule n'existe pas :" + mat);
			connect.close();
			return false;
		}
	}

	boolean Exists(int mat) throws SQLException	


	public boolean Exists(String email) throws SQLException	
	{
		DBConnection BD= new DBConnection();
		DBConnection BD= DBConnection.getDBConnection(); //DBConnection -> Singleton  
		Connection connect=BD.getConn();

		Statement stmt = connect.createStatement();
		String sql = "select * from etudiant where matricule="+ mat;
		boolean rs = stmt.execute(sql);
		String sql = "select * from etudiant where email='"+ email +"'";
		ResultSet rs = stmt.executeQuery(sql); //J'ai remplacé stmt.execute(sql);

		if (rs){
			System.out.println("logBD--- :etudiant avec ce matricule existe déja dans la BD  " + mat);
		if (rs.next())  //If ResultSet rs is not empty
		{
			System.out.println("logBD--- : email existe dans la BD : " + email);
			connect.close();
			return true;
			}
		System.out.println("logBD----: etudiant avec ce matricule n'existe pas " + mat);	
		connect.close();
		return false;
	}
		}

		else  //If ResultSet rs is empty
		{
			System.out.println("logBD--- : email n'existe pas : " + email);	
			connect.close();
			return false;
		}


	}


}