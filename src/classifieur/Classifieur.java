package classifieur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import parser.HandlerPerso;

public class Classifieur {
	//Attribut
	private Categorie typologie;
	
	//Constructeur de test
	public Classifieur(Categorie categ){
		typologie = categ;
	}
	
	public Classifieur() {
		typologie = new Categorie();
	}
	
	//Accesseur
	public Categorie getTypologie(){
		return this.typologie;
	}
	
	//Méthodes
	public boolean englobe(Categorie englobé, Categorie englobante) { 
		return englobante.englobe(englobé);
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
	
	public void loadXML(String fileName) throws SAXException, IOException {
		// Le parseur SAX
        XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

        // Creation d'un flot XML sur le fichier d'entree
        InputSource input = new InputSource(new FileInputStream(fileName + ".xml"));

        // Creation et connexion du ContentHandler specifique
        HandlerPerso monHandler = new HandlerPerso();
        reader.setContentHandler(monHandler);
        // Lancement du traitement...
        reader.parse(input);
        
        typologie = monHandler.getTypologie();
	}
	
	public void show(String fileName, String format) throws InterruptedException, IOException {
		//ecrit le contenu du fichier .gv
		String str = "diagraph G{\n";
		str+=typologie.show();
		str+="}";
		
		//crée le fichier .gv avec le contenu
		PrintWriter writer = new PrintWriter(fileName + ".gv", "UTF-8");
		writer.println(str);
		writer.close();
		
		//genere le .format
		String srcFile = fileName + ".gv";
		String destFile = fileName + "." + format;
		String commande = "dot -Tpng -o " + destFile + " " + srcFile;
		Runtime.getRuntime().exec(commande).waitFor();
	}
}
