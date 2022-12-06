import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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



	boolean inscription (int matricule, String nom, String prénom, String email,String pwd, int id_universite) throws SQLException	
	{
		I_EtudiantRepository StudRep = I_EtudiantRepository.StudRep; //Inversion de dépendance 
	    UniversiteRepository UnivRep= new UniversiteRepository();
	    Etudiant stud = new Etudiant(matricule, nom, prénom, email,pwd,id_universite);
	    Universite univ=UnivRep.GetById(id_universite);

	    System.out.println("Log: début de l'opération d'ajout de l'étudiant avec matricule "+matricule);
	    Etudiant stud = new Etudiant(matricule, nom, prénom, email, pwd, id_universite);
	    Universite univ = univRep.GetById(id_universite);
	    AffichageEnrichir.setSender("EtudiantService");

	    journal.outPut_Msg("Log: début de l'opération d'ajout de l'étudiant avec matricule "+matricule);


	    if(email == null || email.length() == 0)
	    {
	    	return false;
	    }

	    if (StudRep.Exists(matricule))
	    if (etudRep.Exists(matricule))
	    {
	        return false;
	    }

		if (StudRep.Exists(email))
		if (etudRep.Exists(email))
	    {
	        return false;
	    }
		
		
		
		 if (univ.getPack() == TypePackage.Standard)
	     {
	          stud.setNbLivreMensuel_Autorise(10);
	     }
	     else if (univ.getPack() == TypePackage.Premium)
	     {
	    	 stud.setNbLivreMensuel_Autorise(10*2);
	     }                           

		 StudRep.add(stud);
		 System.out.println("Log: Fin de l'opération d'ajout de l'étudiant avec matricule "+matricule);
		 etudRep.add(stud);
		 journal.outPut_Msg("Log: Fin de l'opération d'ajout de l'étudiant avec matricule "+matricule);
		 return true;


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