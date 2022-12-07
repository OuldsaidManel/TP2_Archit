import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class EtudiantService {

	private IJournal journal;
	private I_EtudiantRepository etudRep;
	private I_UniversiteRepository univRep;
	
	
	public EtudiantService(I_EtudiantRepository etudRep, I_UniversiteRepository univRep, IJournal comp) 
	{
		this.journal = comp;
		this.etudRep = etudRep;
		this.univRep = univRep;
	}




	boolean inscription (Etudiant stud) throws SQLException	
	{

	    Universite univ = univRep.GetById(stud.getId_universite());
	    
	    AffichageEnrichir.setSender("EtudiantService");
	    
	    journal.outPut_Msg("Log: début de l'opération d'ajout de l'étudiant avec matricule " + stud.getMatricule());


		if (etudRep.Verification(stud.getMatricule(), stud.getEmail()))
	    //Verification de l'existance du matricule et d'email dans la bdd
		if (etudRep.Verification(stud.getMatricule(), stud.getEmail()))   
	    {
	        return false;
	    }

		//Initialiser le nombre de livre mensuel autorisé
	    stud.setNbLivreMensuel_Autorise(univRep.GetNbLivreMensuel(univ));

	     

		 etudRep.add(stud);
		 journal.outPut_Msg("Log: Fin de l'opération d'ajout de l'étudiant avec matricule "+ stud.getMatricule());
		 return true;

	}




	public void AjouterBonusTous() throws SQLException {

		Connection connect = DBConnection.getConn();       
		Statement stmt = connect.createStatement();

	}
		int tab_Standard[] = new int[0];     //Pour les id de tous les universités avec package type Standard
		int tab_Premium[]  = new int[0];     //Pour les id de tous les universités avec package type Premium


		String sql1 = "select i_univ from universite where TypePackage ='Standard';";
		ResultSet rs1 = stmt.executeQuery(sql1);

		while(rs1.next()) 
		{
			tab_Standard = Arrays.copyOf(tab_Standard, tab_Standard.length + 1);
			tab_Standard[tab_Standard.length - 1] = rs1.getInt("i_univ");;
		}



		String sql2 = "select i_univ from universite where TypePackage ='Premium';";
		ResultSet rs2 = stmt.executeQuery(sql2);

		while(rs2.next()) 
		{
			tab_Premium = Arrays.copyOf(tab_Premium, tab_Premium.length + 1);
			tab_Premium[tab_Premium.length - 1] = rs2.getInt("i_univ");;
		}



		//Ajouter bonus de +5 pour tous les étudiants appartenant à les universités avec package type Standard
		for (int e : tab_Standard) 
		{

			String sql = "UPDATE etudiant SET nbLivreMensuel_Autorise = nbLivreMensuel_Autorise + 5 WHERE id_universite = " + e; 
			stmt.executeUpdate(sql);

		}


		//Ajouter bonus de +10 pour tous les étudiants appartenant à les universités avec package type Premium
		for (int e : tab_Premium) 
		{

			String sql = "UPDATE etudiant SET nbLivreMensuel_Autorise = nbLivreMensuel_Autorise + 10 WHERE id_universite = " + e; 
			stmt.executeUpdate(sql);

		}


	 }



public ArrayList<Etudiant> GetEtudiantParUniversitye()
{
    //...
	return new ArrayList<>(4);
}
public ArrayList<Etudiant> GetEtudiatparLivreEmprunte()
{
    //...
	return new ArrayList<>(4);
	
}
	
}