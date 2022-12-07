public class MainApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AffichageComposite composite = new AffichageComposite();

		IJournal journal_Ecran = new AffichageEcran();
		IJournal journal_File = new AffichageFichier();

		IJournal journal_Ecran    = new AffichageEcran();
		IJournal journal_File     = new AffichageFichier();
		IJournal journal_Enrichir = new AffichageEnrichir();

		composite.ajouter(journal_Ecran); 	 //Affichage Ecran
		composite.ajouter(journal_File);     //Affichage Fichier
		composite.ajouter(journal_Enrichir); //Affichage Message + Date + Emetteur 

		composite.ajouter(journal_Ecran);     //Affichage Ecran
		composite.ajouter(journal_File);      //Affichage Fichier
		composite.ajouter(journal_Enrichir);  //Affichage Message + Date + Emetteur 


		Etudiant stud = new Etudiant(2, "Guendouziiiii", "wassila", "guen@gmail.com", "xxxx", 1);


		I_EtudiantRepository etudRep = new EtudiantRepository(composite);

		I_UniversiteRepository univRep = new UniversiteRepository(composite);

		EtudiantService etud_service = new EtudiantService(etudRep, univRep, composite);
		EtudiantService etud_Service = new EtudiantService(etudRep, univRep, composite);


		try {
			etud_service.inscription(stud);

		} catch (Exception e) {
			etud_Service.inscription(stud);   //Inscription de l'étudiant stud

			stud.AjouterBonus(univRep);       //Ajouter bonus à l'étudiant

			etud_Service.AjouterBonusTous();  //Ajouter bonus à tous les étudiants avec 'Standard' et 'Premium'

		} 

		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}