package classifieur;

public abstract class Domaine {
	//Attribut
	protected String nom;
	
	//Méthodes
	public abstract boolean inclus(Domaine englobant);
	
	public boolean isEnsembleDeChaine(){ 
		return (this instanceof EnsembleDeChaine); 
	}
	
	public boolean isIntervalleNumerique(){ 
		return (this instanceof IntervalleNumerique); 
	}
	
	public boolean inclus_valeur(String value){
		boolean est_inclus = false;
		
		if(this.isEnsembleDeChaine())
			est_inclus = ((EnsembleDeChaine)this).getSymboles().contains(value);
		else{
			double valueDouble = Double.parseDouble(value);
			est_inclus = valueDouble >= ((IntervalleNumerique)this).getInf() && valueDouble <= ((IntervalleNumerique)this).getSup();
		}
		
		return est_inclus;
	}
	
	public String toString(){
		return "- " + this.nom + ": ";
	}
}
