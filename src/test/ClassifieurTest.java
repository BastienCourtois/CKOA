package test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classifieur.Categorie;
import classifieur.Classifieur;
import classifieur.Domaine;
import classifieur.EnsembleDeChaine;
import classifieur.IntervalleNumerique;
import classifieur.Observation;

public class ClassifieurTest {
	Classifieur classi;

	Categorie categ;
	Categorie categ2;

	@Before
	public void setUp() {
		// Création des caractéristiques de la catégorie arbre
		HashMap<String, Domaine> carac = new HashMap<String, Domaine>();

		ArrayList<String> symb = new ArrayList<String>();
		symb.add("arrondi");
		symb.add("conique");
		symb.add("irregulier");
		EnsembleDeChaine ens = new EnsembleDeChaine("forme", symb);

		IntervalleNumerique inter = new IntervalleNumerique("taille du tronc", 0.2, 3.0);

		ArrayList<String> symb2 = new ArrayList<String>();
		symb2.add("ecailles");
		symb2.add("fissuree");
		symb2.add("lisse");
		symb2.add("plaques");
		EnsembleDeChaine ens2 = new EnsembleDeChaine("ecorce", symb2);

		IntervalleNumerique inter2 = new IntervalleNumerique("taille", 5.0, 50.0);

		carac.put("forme", ens);
		carac.put("taille du tronc", inter);
		carac.put("ecorce", ens2);
		carac.put("taille", inter2);

		// Création de la catégorie arbre
		categ = new Categorie("arbre", carac);

		// Création du classifieur
		classi = new Classifieur(categ);

		// Création des caracteristiques de la sous-categorie conifere
		HashMap<String, Domaine> carac2 = new HashMap<String, Domaine>();

		ArrayList<String> symb3 = new ArrayList<String>();
		symb3.add("conique");
		EnsembleDeChaine ens3 = new EnsembleDeChaine("forme", symb3);

		IntervalleNumerique inter3 = new IntervalleNumerique("taille du tronc", 0.5, 2.0);

		ArrayList<String> symb4 = new ArrayList<String>();
		symb4.add("ecailles");
		symb4.add("fissuree");
		symb4.add("lisse");
		EnsembleDeChaine ens4 = new EnsembleDeChaine("ecorce", symb4);

		IntervalleNumerique inter4 = new IntervalleNumerique("taille", 5.0, 40.0);

		carac2.put("forme", ens3);
		carac2.put("taille du tronc", inter3);
		carac2.put("ecorce", ens4);
		carac2.put("taille", inter4);

		// Création de la catégorie arbre
		categ2 = new Categorie("conifere", carac2);
	}

	@Test
	public void test_ajout_sous_categ_1() {
		Assert.assertTrue(classi.ajout_sous_categ(categ, "conifere", categ2));
	}

	@Test
	public void test_ajout_sous_categ_2() {
		// Création des caracteristiques de la sous-categorie conifere
		HashMap<String, Domaine> carac = new HashMap<String, Domaine>();

		ArrayList<String> symb = new ArrayList<String>();
		symb.add("marchepas");
		EnsembleDeChaine ens = new EnsembleDeChaine("forme",symb);

		IntervalleNumerique inter3 = new IntervalleNumerique("taille du tronc", 0.5, 2.0);

		ArrayList<String> symb4 = new ArrayList<String>();
		symb4.add("ecailles");
		symb4.add("fissuree");
		symb4.add("lisse");
		EnsembleDeChaine ens4 = new EnsembleDeChaine("ecorce", symb4);

		IntervalleNumerique inter4 = new IntervalleNumerique("taille", 5.0, 40.0);

		carac.put("forme", ens);
		carac.put("taille du tronc", inter3);
		carac.put("ecorce", ens4);
		carac.put("taille", inter4);

		// Création de la catégorie arbre
		categ2 = new Categorie("conifere", carac);

		Assert.assertTrue(!classi.ajout_sous_categ(categ, "conifere", categ2));
	}
	
	@Test
	public void test_toutes_categ_1() {
		Observation obs;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("taille", "30.0");
		map.put("forme", "conique");
		map.put("taille du tronc", "1.0");
		map.put("ecorce", "ecailles");
		map.put("aiguilles", "peigne");

		obs = new Observation(map);

		classi.ajout_sous_categ(categ, "conifere", categ2);
		ArrayList<Categorie> liste = new ArrayList<Categorie>();
		liste.add(categ);
		liste.add(categ2);
		ArrayList<Categorie> liste2 = classi.toutes_categ(obs) ;
		Assert.assertEquals(liste, liste2);
	}

}
