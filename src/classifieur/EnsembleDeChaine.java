package classifieur;
import java.util.ArrayList;

public class EnsembleDeChaine extends Domaine{
	//Attribut
	private ArrayList<String> symboles;
	
	//Constructeur
	public EnsembleDeChaine(String nnom, ArrayList<String> liste){
		this.nom=nnom;
		symboles = liste;
	}
	
	//Méthodes
	public ArrayList<String> getSymboles(){ return this.symboles; } 
	
	public boolean inclus(Domaine englobant) {
		boolean inclus = true;
		int nbElements = this.symboles.size();
		int i = 0;
		
		if(englobant.isEnsembleDeChaine())
			while(i < nbElements && inclus){
				inclus = ((EnsembleDeChaine)englobant).symboles.contains(this.symboles.get(i));
				i++;
			}
		
		return inclus;
	}
	
	public String toString(){
		return super.toString() + symboles.toString();
	}
}
