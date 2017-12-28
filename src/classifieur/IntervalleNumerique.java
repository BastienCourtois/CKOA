package classifieur;
public class IntervalleNumerique extends Domaine {
	private double inf; //Borne inférieure de l'intervalle.
	private double sup; //Borne supérieure de l'intervalle.
	
	//Constructeur
	public IntervalleNumerique(String nnom, double i, double s){
		this.nom=nnom;
		if(i < s){
			this.inf = i;
			this.sup = s;
		}else{
			this.inf = s;
			this.sup = i;
		}
	}
	
	//Accesseurs
	public double getSup(){ 
		return this.sup; 
	}
	
	public double getInf(){ 
		return this.inf; 
	}
	
	//Méthodes
	public boolean inclus(Domaine englobant) {
		boolean inclus = false;
		
		if(englobant.isIntervalleNumerique())
			inclus = ( (this.inf >=  ((IntervalleNumerique)englobant).inf) && (this.sup <= ((IntervalleNumerique)englobant).sup) );
		return inclus;
	}
	
	public String toString(){
		return super.toString() + "[" + this.inf + ", " + this.sup + "]";
	}
}
