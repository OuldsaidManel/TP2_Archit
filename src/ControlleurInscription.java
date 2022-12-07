import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ControlleurInscription extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	EtudiantService ES;
	ViewInscription V;
	Etudiant modele;


		public ControlleurInscription(EtudiantService ES , ViewInscription V) {

			this.V  =  V;
			this.ES = ES;			
		}


		private void initialiser() {

	        (V.Pane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
	        V.Pane.setLayout(null);

	        final JLabel Lab_Matricule = new JLabel("Matricule :");
	        Lab_Matricule.setBounds(30, 33, 68, 12);
	        V.Pane.add(Lab_Matricule);

	        (V.TxtMat = new JTextField()).setBounds(184, 27, 200, 20);
	        V.Pane.add(V.TxtMat);
	        V.TxtMat.setColumns(10);

	        final JLabel LabNom = new JLabel("Nom :");
	        LabNom.setBounds(30, 60, 46, 14);

	        V.Pane.add(LabNom);
	        (V.TxtNom = new JTextField()).setBounds(184, 54, 200, 20);

	        V.Pane.add(V.TxtNom);
	        V.TxtNom.setColumns(10);

	        final JLabel LabPrenom = new JLabel("Prenom :");
	        LabPrenom.setBounds(30, 91, 60, 14);
	        V.Pane.add(LabPrenom);

	        (V.TxtPrenom = new JTextField()).setBounds(184, 85, 200, 20);
	        V.Pane.add(V.TxtPrenom);
	        V.TxtPrenom.setColumns(10);

	        final JLabel LabEmail = new JLabel("Email :");
	        LabEmail.setBounds(30, 126, 46, 14);
	        V.Pane.add(LabEmail);

	        (V.TxtEmail = new JTextField()).setColumns(10);
	        V.TxtEmail.setBounds(184, 120, 200, 20);
	        V.Pane.add(V.TxtEmail);

	        final JLabel LabPwd = new JLabel("Mot de passe :");
	        LabPwd.setBounds(30, 165, 90, 14);
	        V.Pane.add(LabPwd);

	        (V.TxtPwd = new JPasswordField()).setColumns(10);
	        V.TxtPwd.setEchoChar('*');
	        V.TxtPwd.setBounds(184, 159, 200, 20);
	        V.Pane.add(V.TxtPwd);

	        final JLabel LabId_Univ = new JLabel("Identifiant universite :");
	        LabId_Univ.setBounds(30, 200, 125, 14);
	        V.Pane.add(LabId_Univ);

	        (V.TxtIdUniv = new JTextField()).setColumns(10);
	        V.TxtIdUniv.setBounds(184, 194, 200, 20);
	        V.Pane.add(V.TxtIdUniv);


	        V.btnSubmit = new JButton("Inscrire");
	        V.btnSubmit.setActionCommand("Inscrire");
	        V.btnSubmit.addActionListener(this);

	        V.btnSubmit.setBounds(295, 227, 89, 23);
	        V.Pane.add(V.btnSubmit);


	        V.btnCancel = new JButton("Annuler");
	        V.btnCancel.setActionCommand("Annuler");
	        V.btnCancel.addActionListener(this);

	        V.btnCancel.setBounds(184, 227, 89, 23);
	        V.Pane.add(V.btnCancel);


	        (V.frame = new JFrame("Interface de l'inscription")).setBounds(100, 100, 450, 300);
	        V.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        V.frame.setLocationRelativeTo(null);

	        V.frame.setContentPane(V.Pane);
	    }


	    public boolean Validation() {

	        if (V.TxtMat.getText().isEmpty() || V.TxtNom.getText().isEmpty() || V.TxtPrenom.getText().isEmpty() || V.TxtEmail.getText().isEmpty() || V.TxtPwd.getText().isEmpty() || V.TxtIdUniv.getText().isEmpty()) 
	        {
	            this.ShowErreur("Veuillez remplir tous les champs");
	            return false;
	        }

	        if (!V.TxtMat.getText().matches("-?\\d+") || !V.TxtIdUniv.getText().matches("-?\\d+")) {
	            this.ShowErreur("Les champs matricule et id universite doivent etre de format numeriques ");
	            return false;
	        }

	        if (!V.TxtEmail.getText().matches("^(.+)@(.+)$")) {
	            this.ShowErreur("Il faut respecter le format d'un email xxx@xxx.xxx");
	            return false;
	        }
	        return true;
	    }


	    public void Demarrer_Inscription() {
	        this.initialiser();
	        V.frame.setVisible(true);

	    }



	    public void ShowDialog(final String msg) {
	        JOptionPane.showMessageDialog(new JFrame(), msg);
	    }



	    public void ShowErreur(final String msg) {
	        JOptionPane.showMessageDialog(new JFrame(), msg, "Erreur", 0);
	    }



	    public void Init_Inscription() {
	        V.TxtMat.setText("");
	        V.TxtNom.setText("");
	        V.TxtPrenom.setText("");
	        V.TxtEmail.setText("");
	        V.TxtPwd.setText("");
	        V.TxtIdUniv.setText("");
	    }



	    public int Get_Mat() {
	        return Integer.parseInt(V.TxtMat.getText());
	    }



	    public String Get_Nom() {
	        return V.TxtNom.getText();
	    }



	    public String Get_Prenom() {
	        return V.TxtPrenom.getText();
	    }



	    public String Get_email() {
	        return V.TxtEmail.getText();
	    }



	    public String Get_pwd() {
	        return V.TxtPwd.getText();
	    }



	    public int Get_id_univ() {
	        return Integer.parseInt(V.TxtIdUniv.getText());
	    }



	    public void actionPerformed(ActionEvent e) {

			if("Annuler".equals(e.getActionCommand())) {

				int dialogButton = JOptionPane.showConfirmDialog(V.frame,"Voulez vous quitter l'application?","Quitter", JOptionPane.YES_NO_CANCEL_OPTION);

				if(dialogButton == JOptionPane.YES_OPTION) {
		            System.exit(0);
				 }

			 }

			if("Inscrire".equals(e.getActionCommand())) 
			{				
				boolean Validation = Validation();

				if(Validation) {

					int matricule = Get_Mat();
					String nom = Get_Nom();
					String prenom = Get_Prenom();
					String email = Get_email();
					String password = Get_pwd();
					int ID_Univ = Get_id_univ();

					modele = new Etudiant(matricule, nom, prenom, email, password, ID_Univ);

					try 
					{	
						ES.inscription(modele);
						ShowDialog("Vérifiez la console pour voir le résultat de l'inscription");
					} 

					catch (SQLException e1) {

						e1.printStackTrace();
					} 


				}


			}

		}

	}
