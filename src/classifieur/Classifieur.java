package classifieur;
import java.util.ArrayList;

public class Classifieur {
	//Attribut
	private Categorie typologie;
	
	//Constructeur de test
	public Classifieur(Categorie categ){
		typologie = categ;
	}
	
	//Accesseur
	public Categorie getTypologie(){
		return this.typologie;
	}
	
	//M�thodes
	public boolean englobe(Categorie englob�, Categorie englobante) { 
		return englobante.englobe(englob�);
	}
	
	public boolean ajout_sous_categ(Categorie mere, String nomFille, Categorie fille){
		return mere.ajout_sous_categ(nomFille, fille);
	}
	
	public ArrayList<Categorie> toutes_categ(Observation obs){
		return this.typologie.toutes_categ(obs);
	}

	public String toString(){
		return this.typologie.toString();
	}
	
	public ArrayList<String> collecte_caracteristiques(){
		return typologie.collecte_caracteristiques();
	}
}
