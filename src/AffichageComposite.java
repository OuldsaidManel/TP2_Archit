import java.util.List;
import java.util.ArrayList;

public class AffichageComposite implements IJournal{

	private List<IJournal>journals = new ArrayList<IJournal>();
	private List <IJournal> Liste = new ArrayList<IJournal>();


  @Override
  public void outPut_Msg(String message) {


		for(IJournal j : journals){
			j.outPut_Msg(message);
		}
  public void ajouter(IJournal journal){
	  	 Liste.add(journal);
	}


  public void add(IJournal journal){
		journals.add(journal);
  public void supprimer(IJournal journal){
	  	Liste.remove(journal);
	}


  @Override
  public void outPut_Msg(String message) {

  public void remove(IJournal journal){
		journals.remove(journal);
		for(IJournal L : Liste){
			L.outPut_Msg(message);
		}
	}

}