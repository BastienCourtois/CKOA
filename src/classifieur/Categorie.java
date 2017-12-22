package classifieur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Categorie {
	// Attribut
	private String nom;
	private HashMap<String, Categorie> filles = new HashMap<String, Categorie>();
	private HashMap<String, Domaine> caracteristiques;

	// Constructeur de test
	public Categorie(String n, HashMap<String, Domaine> carac) {
		this.nom = n;
		this.caracteristiques = carac;
	}

	// Accesseurs
	public HashMap<String, Domaine> getCaracteristiques() {
		return this.caracteristiques;
	}

	public HashMap<String, Categorie> getFilles() {
		return this.filles;
	}

	// Méthodes
	public boolean englobe(Categorie englob�) {
		boolean englobe = true;

		// Pour chaque caractéristiques de la catégorie englobante
		for (Map.Entry<String, Domaine> caracteristique : this.getCaracteristiques().entrySet())
			// Si la catégorie englobé posssède la caractéristique de la
			// catégorie englobante
			if (englob�.getCaracteristiques().containsKey(caracteristique.getKey())) {
				// Si le domaine de la caractéristique de la catégorie englobé
				// n'est pas inclus dans le domaine de la
				// caractéristique de la catégorie englobante.
				if (!englob�.caracteristiques.get(caracteristique.getKey()).inclus(caracteristique.getValue()))
					// Alors la catégorie englobé n'est pas englobée dans la
					// catégorie englobante.
					englobe = false;
				// Sinon la catégorie englobé n'est pas englobée dans la
				// catégorie englobante.
			} else
				englobe = false;

		return englobe;
	}

	public boolean ajout_sous_categ(String nomFille, Categorie fille) {
		boolean ajout = false;

		// si la catégorie englobe la fille
		if (this.englobe(fille)) {
			// on ajoute la catégorie la map des catégories fille avec la clé
			// nomfille
			this.filles.put(nomFille, fille);
			ajout = true;
		}

		// retourne true si l'ajout a été fait, false sinon
		return ajout;
	}

	public ArrayList<Categorie> toutes_categ(Observation obs) {
		// liste des catégories sous lesquelles l'observation se classe
		ArrayList<Categorie> liste = new ArrayList<Categorie>();
		// si l'observation si classe sous la catégorie actuelle
		if (obs.est_sous(this)) {
			// on ajoute la catégorie à la liste
			liste.add(this);
			//si la catégorie possède des catégories fille
			if (this.getFilles().size() != 0)
				//pour chaque fille
				for (Map.Entry<String, Categorie> fille : this.getFilles().entrySet())
					// appel récursif de la fonction pour chaque catégorie fille
					liste.addAll(fille.getValue().toutes_categ(obs));
		}
		//retourne la liste des catégories
		return liste;
	}

	public String toString() {
		//nom de la catégorie
		String str = "CATEGORIE: " + this.nom + "\n";

		//affiche chaque caractéristiques
		for (Map.Entry<String, Domaine> caracteristique : this.getCaracteristiques().entrySet())
			str += caracteristique.getValue().toString() + "\n";
		//affiche chaque catégorie fille
		for (Map.Entry<String, Categorie> fille : this.getFilles().entrySet())
			str += fille.getValue().toString();

		return str;
	}

	public ArrayList<String> collecte_caracteristiques() {
		ArrayList<String> caracteristiques = new ArrayList<String>();

		// Pour chaque caractéristique de la catégorie appelante
		for (Map.Entry<String, Domaine> caracteristique : this.getCaracteristiques().entrySet())
			// Si la caractéristique n'est pas dans la liste
			if (!caracteristiques.contains(caracteristique.getKey()))
				// Alors on l'ajoute.
				caracteristiques.add(caracteristique.getKey());

		// Pour chaque fille de la catégorie appelante
		for (Map.Entry<String, Categorie> fille : this.getFilles().entrySet())
			// On ajoute les caractéristiques de chacune de ses filles.
			caracteristiques.addAll(fille.getValue().collecte_caracteristiques());

		return caracteristiques;
	}
}
