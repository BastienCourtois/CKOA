package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classifieur.EnsembleDeChaine;
import classifieur.IntervalleNumerique;

public class EnsembleDeChainesTest {

	EnsembleDeChaine ens;
	
	@Before
	public void setUp(){
		ArrayList<String> liste = new ArrayList<String>();
		liste.add("PLP");
		liste.add("AC");
		liste.add("LC");
		ens = new EnsembleDeChaine(liste);
	}
	
	@Test
	public void test_inclus_valeur_1() {
		Assert.assertTrue(ens.inclus_valeur("PLP"));
	}

	@Test
	public void test_inclus_valeur_2() {
		Assert.assertTrue(!ens.inclus_valeur("BC"));
	}
	
	@Test
	public void test_inclus_1() {
		ArrayList<String> liste2 = new ArrayList<String>();
		liste2.add("PLP");
		liste2.add("AC");
		liste2.add("LC");
		EnsembleDeChaine ens2 = new EnsembleDeChaine(liste2);
		Assert.assertTrue(ens.inclus(ens2));
	}
	
	@Test
	public void test_inclus_2() {
		ArrayList<String> liste2 = new ArrayList<String>();
		liste2.add("PLP");
		liste2.add("AC");
		liste2.add("BC");
		EnsembleDeChaine ens2 = new EnsembleDeChaine(liste2);
		Assert.assertTrue(!ens.inclus(ens2));
	}
	
	@Test
	public void test_inclus_3() {
		ArrayList<String> liste2 = new ArrayList<String>();
		liste2.add("PLP");
		liste2.add("AC");
		liste2.add("BC");
		liste2.add("LC");
		EnsembleDeChaine ens2 = new EnsembleDeChaine(liste2);
		Assert.assertTrue(ens.inclus(ens2));
	}
	
	@Test
	public void test_inclus_4() {
		ArrayList<String> liste2 = new ArrayList<String>();
		liste2.add("PLP");
		liste2.add("AC");
		EnsembleDeChaine ens2 = new EnsembleDeChaine(liste2);
		Assert.assertTrue(!ens.inclus(ens2));
	}
	
	@Test
	public void test_isEnsembleDeChaine_1() {
		Assert.assertTrue(ens.isEnsembleDeChaine());
	}
	
	@Test
	public void test_isEnsembleDeChaine_2() {
		IntervalleNumerique inte = new IntervalleNumerique(10,100);
		Assert.assertTrue(!inte.isEnsembleDeChaine());
	}
	
}
