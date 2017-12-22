package parser;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

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
            HandlerExample monHandler = new HandlerExample();
            reader.setContentHandler(monHandler);
            // Lancement du traitement...
            reader.parse(input);

            // recuperation de donnees
            System.out.println("Categories: " + monHandler.getCategories());
        }
    }
}
