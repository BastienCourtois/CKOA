package test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import classifieur.Categorie;
import classifieur.Classifieur;
import classifieur.Observation;

public class TestXml {

	Classifieur arbres, nuages;
	
	@Before
	public void setUp() throws SAXException, IOException {
		arbres = new Classifieur(new Categorie());
		arbres.loadXML("ressources/arbres/arbres");
		
		nuages = new Classifieur(new Categorie());
		nuages.loadXML("ressources/nuages/nuages");
	}
	
	@Test
	public void test_toutes_categ_1() {
		Observation obs;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("taille", "30.0");
		map.put("forme", "conique");
		map.put("taille du tronc", "1.0");
		map.put("ecorce", "ecailles");

		obs = new Observation(map);
		
		ArrayList<Categorie> liste = new ArrayList<>();
		liste.add(arbres.getTypologie());
		liste.add(arbres.getTypologie().getFilles().get("conifere"));
		
		ArrayList<Categorie> liste2 = arbres.toutes_categ(obs) ;
		System.out.println(liste);
		System.out.println(liste2);
		Assert.assertEquals(liste, liste2);
	}

}
