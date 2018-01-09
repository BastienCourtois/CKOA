package classifieur;
import java.util.HashMap;
import java.util.Map;

public class Observation {
	//Attribut
	private HashMap<String,String> caracteristiquesObservees;
	
	//Constrcuteur
	public Observation() {
		this.caracteristiquesObservees = new HashMap<String,String>();
	}
	
	//Constructeur de test
	public Observation(HashMap<String,String> caracObs){
		this.caracteristiquesObservees = caracObs;
	}
	
	//Méthodes
	public boolean est_sous(Categorie categ) {
		boolean est_sous = true;
		
		//Pour chaque caractéristiques de la catégorie mère
		for(Map.Entry<String,Domaine> caracteristique : categ.getCaracteristiques().entrySet())
			//Si l'observation possède la caractéristique de la catégorie mère
			if(this.caracteristiquesObservees.containsKey(caracteristique.getKey())){
				//Si la valeur de la caractéristique observées n'est pas inclus dans le domaine de la caractéristique de la
				//catégorie mère
				if(!caracteristique.getValue().inclus_valeur(this.caracteristiquesObservees.get(caracteristique.getKey())))
					//Alors l'observation ne se classe pas sous la catégorie mère
					est_sous = false;
			//Sinon l'observation ne se classe pas sous la catégorie mère
			}else est_sous = false;
		
		return est_sous;
	}
	
	public boolean trouveCaracteristique(String carac) {
		boolean trouve = false;
		for(Map.Entry<String,String> caracteristique : this.caracteristiquesObservees.entrySet())
			if(carac.equalsIgnoreCase(caracteristique.getKey()))
				trouve = true;
		return trouve;
	}
	
	public void ajoutCaracteristique(String nomCarac, String carac) {
		caracteristiquesObservees.put(nomCarac, carac);
	}
}
