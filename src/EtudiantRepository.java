import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class EtudiantRepository implements I_EtudiantRepository  {  

private IJournal journal;

	public EtudiantRepository(IJournal j){

		this.journal = j ;

		}	


	@Override
	public void add(Etudiant E) throws SQLException
	{

		DBConnection BD= DBConnection.getDBConnection(); //DBConnection -> Singleton  
		Connection connect=BD.getConn();
		AffichageEnrichir.setSender("EtudiantRepository");

		Connection connect = DBConnection.getConn(); //DBConnection -> Singleton 

		Statement stmt = connect.createStatement();

		// J'ai ajout� E.getPwd() dans sql
		String sql = "INSERT into etudiant values (" + E.getMatricule() + ",'" + E.getNom() + "','" + E.getPrenom() + "','" + E.getEmail() + "','" + E.getPwd() + "'," +E.getNbLivreMensuel_Autorise() + "," +E.getNbLivreEmprunte() + "," +E.getId_universite()+")";

		int rs = stmt.executeUpdate(sql);

		if (rs == 1){
				System.out.println("log : ajout dans la BD r�ussi de l'�tudiant  du Matricule" + E.getMatricule());
			}else if (rs == 0){
				System.out.println("log : Echec de l'ajout dans la BD de l'�tudiant  du Matricule" + E.getMatricule());
			journal.outPut_Msg("log : ajout dans la BD r�ussi de l'�tudiant  du Matricule" + E.getMatricule());
			}
		else if (rs == 0){
				journal.outPut_Msg("log : Echec de l'ajout dans la BD de l'�tudiant  du Matricule" + E.getMatricule());
			}
		//connect.close(); C'est mieux de garder la connection ouverte (Singleton)
	 }


	@Override
	public boolean Exists(int mat) throws SQLException	
	{
		DBConnection BD= DBConnection.getDBConnection(); //DBConnection -> Singleton  
		Connection connect=BD.getConn();
		Connection connect=DBConnection.getConn();

		Statement stmt = connect.createStatement();
		String sql = "select * from etudiant where matricule=" + mat;
		ResultSet rs = stmt.executeQuery(sql); //J'ai remplac� stmt.execute(sql);
		ResultSet rs = stmt.executeQuery(sql);      //J'ai remplac� stmt.execute(sql);


		if (rs.next()) //If ResultSet rs is not empty
		if (rs.next())     //If ResultSet rs is not empty
	      {       
	        System.out.print("logBD--- :l'�tudiant avec ce matricule existe d�ja dans la BD :\n ");
	        System.out.format("(%d, %s, %s, %s, %s, %s)\n", rs.getInt("matricule"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("pwd"), rs.getInt("nbLivreMensuel_Autorise"),rs.getInt("nbLivreEmprunte"),rs.getInt("id_universite"));
	      //connect.close();
			journal.outPut_Msg("logBD--- :l'�tudiant avec ce matricule existe d�ja dans la BD : " + mat);   
			return true;
	      }

		else  //If ResultSet rs is empty
		else    //If ResultSet rs is empty
		{
			System.out.println("logBD----: l'�tudiant avec ce matricule n'existe pas :" + mat);
		 //connect.close();
			journal.outPut_Msg("logBD----: l'�tudiant avec ce matricule n'existe pas :" + mat);
			return false;
		}
	}


	@Override
	public boolean Exists(String email) throws SQLException	
	{
		DBConnection BD= DBConnection.getDBConnection(); //DBConnection -> Singleton  
		Connection connect=BD.getConn();
		Connection connect=DBConnection.getConn();

		Statement stmt = connect.createStatement();
		String sql = "select * from etudiant where email='"+ email +"'";
		ResultSet rs = stmt.executeQuery(sql); //J'ai remplac� stmt.execute(sql);
		ResultSet rs = stmt.executeQuery(sql);    //J'ai remplac� stmt.execute(sql);

		if (rs.next())  //If ResultSet rs is not empty
		{
			System.out.println("logBD--- : email existe dans la BD : " + email);
			//connect.close(); 
			journal.outPut_Msg("logBD--- : email existe dans la BD : " + email);
			return true;
		}

		else  //If ResultSet rs is empty
		{
			System.out.println("logBD--- : email n'existe pas : " + email);	
			//connect.close();
			journal.outPut_Msg("logBD--- : email n'existe pas : " + email);	
			return false;
		}

		
	}
	
	
}