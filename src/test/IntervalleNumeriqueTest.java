package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classifieur.EnsembleDeChaine;
import classifieur.IntervalleNumerique;

public class IntervalleNumeriqueTest {

	IntervalleNumerique inte;
	
	@Before
	public void setUp(){
		inte = new IntervalleNumerique("intervalle", 10,100);
	}
	
	@Test
	public void test_inclus_valeur_1() {
		Assert.assertTrue(inte.inclus_valeur("50"));
	}

    @Test
	public void test_inclus_valeur_2() {
		Assert.assertTrue(inte.inclus_valeur("10"));
	}

    @Test
	public void test_inclus_valeur_3() {
		Assert.assertTrue(inte.inclus_valeur("100"));
	}

	@Test
	public void test_inclus_valeur_4() {
		Assert.assertTrue(!inte.inclus_valeur("101"));
	}

    @Test
	public void test_inclus_valeur_5() {
		Assert.assertTrue(!inte.inclus_valeur("9"));
	}
	
	@Test
	public void test_inclus_1(){
        IntervalleNumerique inte2 = new IntervalleNumerique("intervalle", 9,101);
        Assert.assertTrue(inte.inclus(inte2));
	}
	
    @Test
	public void test_inclus_2(){
        IntervalleNumerique inte2 = new IntervalleNumerique("intervalle", 9,99);
        Assert.assertTrue(!inte.inclus(inte2));
	}
	
    @Test
	public void test_inclus_3(){
        IntervalleNumerique inte2 = new IntervalleNumerique("intervalle", 11,101);
        Assert.assertTrue(!inte.inclus(inte2));
	}

	@Test
	public void test_inclus_4(){
        IntervalleNumerique inte2 = new IntervalleNumerique("intervalle", 11,99);
        Assert.assertTrue(!inte.inclus(inte2));
	}
	
	@Test
	public void test_isIntervalleNumerique_1(){
        Assert.assertTrue(inte.isIntervalleNumerique());
	}
	
	@Test
	public void test_isIntervalleNumerique_2(){
		ArrayList<String> liste2 = new ArrayList<String>();
		liste2.add("PLP");
		liste2.add("AC");
		EnsembleDeChaine ens = new EnsembleDeChaine("ensemble", liste2);
        Assert.assertTrue(!ens.isIntervalleNumerique());
	}
	
}