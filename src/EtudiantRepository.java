import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class EtudiantRepository implements I_EtudiantRepository  {  
	
private IJournal journal;
	
	public EtudiantRepository(IJournal j){
		
		this.journal = j ;
		
		}	
	
	
	@Override
	public void add(Etudiant E) throws SQLException
	{
		AffichageEnrichir.setSender("EtudiantRepository");
 
		Connection connect = DBConnection.getConn(); //DBConnection -> Singleton 
		
		Statement stmt = connect.createStatement();
		
		// J'ai ajouté E.getPwd() dans sql
		String sql = "INSERT into etudiant values (" + E.getMatricule() + ",'" + E.getNom() + "','" + E.getPrenom() + "','" + E.getEmail() + "','" + E.getPwd() + "'," +E.getNbLivreMensuel_Autorise() + "," +E.getNbLivreEmprunte() + "," +E.getId_universite()+")";
		
		int rs = stmt.executeUpdate(sql);

		if (rs == 1){
			journal.outPut_Msg("log : ajout dans la BD réussi de l'étudiant  du Matricule" + E.getMatricule());
			journal.outPut_Msg("log : Ajout dans la BD réussi de l'étudiant du Matricule " + E.getMatricule());
			}

		else if (rs == 0){
				journal.outPut_Msg("log : Echec de l'ajout dans la BD de l'étudiant  du Matricule" + E.getMatricule());
				journal.outPut_Msg("log : Echec de l'ajout dans la BD de l'étudiant du Matricule " + E.getMatricule());
			}

		//connect.close(); C'est mieux de garder la connection ouverte (Singleton)
	 }

@@ -46,18 +48,18 @@ public boolean Exists(int mat) throws SQLException

		Statement stmt = connect.createStatement();
		String sql = "select * from etudiant where matricule=" + mat;
		ResultSet rs = stmt.executeQuery(sql);      //J'ai remplacé stmt.execute(sql);
		ResultSet rs = stmt.executeQuery(sql);         //J'ai remplacé stmt.execute(sql); par stmt.executeQuery(sql);


		if (rs.next())     //If ResultSet rs is not empty
		if (rs.next())  //If ResultSet rs is not empty
	      {       
			journal.outPut_Msg("logBD--- :l'étudiant avec ce matricule existe déja dans la BD : " + mat);   
			journal.outPut_Msg("logBD--- : l'étudiant avec ce matricule existe déja dans la BD : " + mat);   
			return true;
	      }

		else    //If ResultSet rs is empty
		{
			journal.outPut_Msg("logBD----: l'étudiant avec ce matricule n'existe pas :" + mat);
			journal.outPut_Msg("logBD--- : l'étudiant avec ce matricule n'existe pas :" + mat);
			return false;
		}
	}
@@ -68,8 +70,9 @@ public boolean Exists(String email) throws SQLException
		Connection connect=DBConnection.getConn();

		Statement stmt = connect.createStatement();

		String sql = "select * from etudiant where email='"+ email +"'";
		ResultSet rs = stmt.executeQuery(sql);    //J'ai remplacé stmt.execute(sql);
		ResultSet rs = stmt.executeQuery(sql);       //J'ai remplacé stmt.execute(sql); par stmt.executeQuery(sql);

		if (rs.next())  //If ResultSet rs is not empty
		{
@@ -86,5 +89,11 @@ public boolean Exists(String email) throws SQLException

	}

	@Override
	public boolean Verification(int mat, String email) throws SQLException	
	{
		return ( Exists(email) || Exists(mat) || email == null || email.length() == 0 );

	}

}