import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class Etudiant {
		private int matricule;
	    private String nom;
	    private String prenom;
	    private String email ;
	    private String pwd;
	    private int nbLivreMensuel_Autorise;
	    private int nbLivreEmprunte;
	    private int id_universite;
	    
	   
		public Etudiant(int matricule, String nom, String prenom, String email, String pwd, int id_universite) {
			
			this.matricule = matricule;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.pwd = pwd;
			this.id_universite = id_universite;
		}
		
		
		public void AjouterBonus(I_UniversiteRepository univRep) throws SQLException{
			
			Universite univ = univRep.GetById(this.getId_universite());
			
			if(univ.getPack() == TypePackage.Standard) { 
				
				this.nbLivreMensuel_Autorise += 5;
			}
			
			if(univ.getPack() == TypePackage.Premium) {
				
				this.nbLivreMensuel_Autorise += 10;
			}
			
			Connection connect = DBConnection.getConn();
			Statement stmt = connect.createStatement();
			String sql = "UPDATE etudiant SET nbLivreMensuel_Autorise = " + this.nbLivreMensuel_Autorise + " WHERE matricule = " + this.matricule; 
			stmt.executeUpdate(sql);

			System.out.println("Log: Un bonus a été ajouté a l'étudiant avec le matricule "+ this.matricule+"\n");
		}


		public int getMatricule() {
			return matricule;
		}
		public void setMatricule(int matricule) {
			this.matricule = matricule;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getNbLivreMensuel_Autorise() {
			return nbLivreMensuel_Autorise;
		}
		public void setNbLivreMensuel_Autorise(int nbLivreMensuel_Autorise) {
			this.nbLivreMensuel_Autorise = nbLivreMensuel_Autorise;
		}
		public int getNbLivreEmprunte() {
			return nbLivreEmprunte;
		}
		public void setNbLivreEmprunte(int nbLivreEmprunte) {
			this.nbLivreEmprunte = nbLivreEmprunte;
		}
		public int getId_universite() {
			return id_universite;
		}
		public void setId_universite(int id_universite) {
			this.id_universite = id_universite;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		
			
		
	    }