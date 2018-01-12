package parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import classifieur.Categorie;
import classifieur.Classifieur;
import classifieur.Domaine;

/**
 *
 * @author carre
 */
public class ParserExample {

    public static void main(String argv[]) throws SAXException, IOException {
        if (argv.length != 1) {
            System.err.println("usage: java parser.ParserExample <fichier> (sans suffixe)");
        } else {
            System.out.println("analyse de " + argv[0] + ".xml");

            // Le parseur SAX
            XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

            // Creation d'un flot XML sur le fichier d'entree
            InputSource input = new InputSource(new FileInputStream(argv[0] + ".xml"));

            // Creation et connexion du ContentHandler specifique
            HandlerPerso monHandler = new HandlerPerso();
            reader.setContentHandler(monHandler);
            // Lancement du traitement...
            reader.parse(input);

            // recuperation de donnees
            System.out.println("Categories: " + monHandler.getCategories());
            
            //recuperation du classifieur(donc simple catégorie mere et de toutes les sous catégories non triées
            System.out.println("\n\n\n\nClassifieur : " + monHandler.getTypologie());
            
        }
    }
}
