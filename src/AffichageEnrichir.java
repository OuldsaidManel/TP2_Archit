import java.time.format.DateTimeFormatter; 
import java.time.LocalDateTime;  

public class AffichageEnrichir implements IJournal{

	private static String emetteur;


	public static void setSender(String Name)
	{
		emetteur = Name;
	}



	@Override
	public void outPut_Msg(String message) {


		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

	    LocalDateTime now = LocalDateTime.now(); 

		System.out.println(message);

		System.out.println("Généré par: " + emetteur);

		System.out.println("Date: " + dtf.format(now)+"\n");

	}
}
