package parser;

import java.util.*;
import org.xml.sax.*;

/**
 *
 * @author carre
 */
public class HandlerExample implements ContentHandler {

    private String elementType;
    private Set<String> categories = new TreeSet<String>();
    private String categorieCourante;
    private String caracteristiqueCourante;
    private double inf;
    private double sup;
    private Set<String> ensemble = new TreeSet<String>();
    private String ensembleCourant;

    public HandlerExample() {
    }

    public Set<String> getCategories() {
        return categories;
    }

    // trace du document
    public void startDocument() throws SAXException {
        System.out.println("Start document...\n");
    }

    public void endDocument() throws SAXException {
        System.out.println("... document charge.");
    }

    // trace de certains elements
    public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
        elementType = localName;
        if (localName.equals("categorie")) {
            System.out.println("New categorie:");
        } else {
            if (localName.equals("caracteristique")) {
                System.out.print("\tnew caracteristique: ");

            }
        }
    }

    public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        if (localName.equals("intervalle")) {
            System.out.println("\t\tintervalle: [" + inf + ", " + sup + "]");
        } else {
            if (localName.equals("caracteristique")) {
                System.out.println("\tend caracteristique: " + this.caracteristiqueCourante + ".");
                ensemble=new TreeSet<String>();

            } else {
                if (localName.equals("categorie")) {
                    System.out.println("end categorie.\n");
                }
                else {
                	if(localName.equals("ensemble")) {
                		System.out.println("\t\tensemble: " + ensemble);
                	}
                }
                elementType = null; // fin traitement de contenu
            }
        }
    }
    //contenu characteres de l'element courant

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementType != null) {
            if ((elementType.equals("docbase")) || (elementType.equals("categorie")) || (elementType.equals("caracteristique")) || (elementType.equals("intervalle")) || (elementType.equals("ensemble"))) {
                // pas de contenu
            } else {
                String contenu = new String(ch, start, length);
                if (elementType.equals("nom")) {
                    System.out.println("nom: " + contenu);
                    categorieCourante = contenu;
                    categories.add(contenu);
                } else {
                    if (elementType.equals("mere")) {
                        System.out.println("categorie mere: " + contenu);
                    } else {
                        if (elementType.equals("intitule")) {
                            System.out.println("" + contenu);
                            caracteristiqueCourante = contenu;
                        } else {
                            if (elementType.equals("inf")) {
                                inf = Double.parseDouble(contenu);
                            } else {
                                if (elementType.equals("sup")) {
                                    sup = Double.parseDouble(contenu);
                                } else {
                                	if (elementType.equals("element")) {
                                		ensemble.add(contenu);
                                	}
                                } 
                            }
                        }
                    }
                }
            }
        }
    }


// NOP
    public void startPrefixMapping(String prefix, String uri) {
    }

    public void endPrefixMapping(String prefix) {
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    public void processingInstruction(String target, String data) throws SAXException {
    }

    public void skippedEntity(String name) throws SAXException {
    }

    public void setDocumentLocator(Locator arg0) {
    }
}
