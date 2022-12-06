
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
public class EtudiantRepository implements I_EtudiantRepository  {  
	
	
	public void add(Etudiant E) throws SQLException
	{
		DBConnection BD= DBConnection.getDBConnection(); //DBConnection -> Singleton  
		Connection connect=BD.getConn();
		
		Statement stmt = connect.createStatement();
		// J'ai ajouté E.getPwd() dans sql
		String sql = "INSERT into etudiant values (" + E.getMatricule() + ",'" + E.getNom() + "','" + E.getPrenom() + "','" + E.getEmail() + "','" + E.getPwd() + "'," +E.getNbLivreMensuel_Autorise() + "," +E.getNbLivreEmprunte() + "," +E.getId_universite()+")";
		int rs = stmt.executeUpdate(sql);
		
		if (rs == 1){
				System.out.println("log : ajout dans la BD réussi de l'étudiant  du Matricule" + E.getMatricule());
			}else if (rs == 0){
				System.out.println("log : Echec de l'ajout dans la BD de l'étudiant  du Matricule" + E.getMatricule());
			}
		connect.close();
		//connect.close(); C'est mieux de garder la connection ouverte (Singleton)
	 }

	public boolean Exists(int mat) throws SQLException	
	{
		DBConnection BD= DBConnection.getDBConnection(); //DBConnection -> Singleton  
		Connection connect=BD.getConn();
		
		Statement stmt = connect.createStatement();
		String sql = "select * from etudiant where matricule=" + mat;
		ResultSet rs = stmt.executeQuery(sql); //J'ai remplacé stmt.execute(sql);
		
		
		if (rs.next()) //If ResultSet rs is not empty
	      {       
	        System.out.print("logBD--- :l'étudiant avec ce matricule existe déja dans la BD :\n ");
	        System.out.format("(%d, %s, %s, %s, %s, %s)\n", rs.getInt("matricule"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("pwd"), rs.getInt("nbLivreMensuel_Autorise"),rs.getInt("nbLivreEmprunte"),rs.getInt("id_universite"));
	        connect.close();
	      //connect.close();
			return true;
	      }

		else  //If ResultSet rs is empty
		{
			System.out.println("logBD----: l'étudiant avec ce matricule n'existe pas :" + mat);
			connect.close();
		 //connect.close();
			return false;
		}
	}
@@ -66,14 +66,14 @@ public boolean Exists(String email) throws SQLException
		if (rs.next())  //If ResultSet rs is not empty
		{
			System.out.println("logBD--- : email existe dans la BD : " + email);
			connect.close();
			//connect.close(); 
			return true;
		}

		else  //If ResultSet rs is empty
		{
			System.out.println("logBD--- : email n'existe pas : " + email);	
			connect.close();
			//connect.close();
			return false;
		}

		
	}
	
	
}