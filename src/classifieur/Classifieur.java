package classifieur;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Classifieur {
	//Attribut
	private HashMap<String,Categorie> typologies = new HashMap<String, Categorie>();;
	
	//Constructeur de test
	public Classifieur(String nom, Categorie categ){
		typologies.put(nom, categ);
	}
	
	//Accesseur
	public HashMap<String,Categorie> getTypologies(){
		return this.typologies;
	}
	
	//MÃ©thodes
	public boolean englobe(Categorie englobé, Categorie englobante) { 
		int i;
		return englobante.englobe(englobé);
	}
	
	public boolean ajout_sous_categ(Categorie mere, String nomFille, Categorie fille){
		return mere.ajout_sous_categ(nomFille, fille);
	}
	
	public ArrayList<Categorie> toutes_categ(Observation obs, Categorie typo){
		return typo.toutes_categ(obs);
	}

	public String toString(){
		String str = "";
		
		//afiche toutes les typologies
		for(Map.Entry<String,Categorie> typologie : this.getTypologies().entrySet())
			str += typologie.getValue().toString();
		
		return str;
	}
	
	public ArrayList<String> collecte_caracteristiques(){
		ArrayList<String> caracteristiques = new ArrayList<String>();
		
		for(Map.Entry<String,Categorie> typologie : this.getTypologies().entrySet()){
			typologie.getValue().collecte_caracteristiques();
			
			for(Map.Entry<String,Categorie> fille : typologie.getValue().getFilles().entrySet())
				fille.getValue().collecte_caracteristiques();
				
		}
		
		return caracteristiques;
	}
}
