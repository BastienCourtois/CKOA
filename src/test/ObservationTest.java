package test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classifieur.Categorie;
import classifieur.Domaine;
import classifieur.EnsembleDeChaine;
import classifieur.IntervalleNumerique;
import classifieur.Observation;

public class ObservationTest {
	Observation obs;
	Categorie categ;

	@Before
	public void setUp() {
		// Création d'une observation
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("taille", "30.0");
		map.put("forme", "conique");
		map.put("taille du tronc", "1.0");
		map.put("ecorce", "ecailles");
		map.put("aiguilles", "peigne");

		obs = new Observation(map);

		// Création de la catégorie arbre
		HashMap<String, Domaine> carac = new HashMap<String, Domaine>();

		ArrayList<String> symb = new ArrayList<String>();
		symb.add("arrondi");
		symb.add("conique");
		symb.add("irregulier");
		EnsembleDeChaine ens = new EnsembleDeChaine(symb);

		IntervalleNumerique inter = new IntervalleNumerique(0.2, 3.0);

		ArrayList<String> symb2 = new ArrayList<String>();
		symb2.add("ecailles");
		symb2.add("fissuree");
		symb2.add("lisse");
		symb2.add("plaques");
		EnsembleDeChaine ens2 = new EnsembleDeChaine(symb2);

		IntervalleNumerique inter2 = new IntervalleNumerique(5.0, 50.0);

		carac.put("forme", ens);
		carac.put("taille du tronc", inter);
		carac.put("ecorce", ens2);
		carac.put("taille", inter2);

		// Création de la catégorie arbre
		categ = new Categorie("arbre", carac);

	}

	@Test
	public void test_est_sous_1() {
		Assert.assertTrue(obs.est_sous(categ));
	}

	@Test
	public void test_est_sous_2() {
		// Création d'une observation
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("taille", "1000.0");
		map.put("forme", "conique");
		map.put("taille du tronc", "1.0");
		map.put("ecorce", "ecailles");
		map.put("aiguilles", "peigne");

		obs = new Observation(map);
		
		Assert.assertTrue(!obs.est_sous(categ));
	}

}
