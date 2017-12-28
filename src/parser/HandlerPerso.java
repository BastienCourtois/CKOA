package parser;

import java.util.*;
import org.xml.sax.*;

import classifieur.*;

/**
 *
 * @author carre
 */
public class HandlerPerso implements ContentHandler {

	//Variables du projet
	private Classifieur classifieur;
	private Categorie categCourante;
	private HashMap<String, Domaine> mapCarac;//caract�ristiques de la cat�gorie courante
	private HashMap<String, Categorie> mapCategorie;//liste des cat�gories filles non tri�es
	private boolean mere;//d�finit si la cat�gorie courante est la m�re afin de cr�er le classifieur d�s que la mere est identifi�e
	private String nomMere;//nom de la m�re de la cat�gorie courante
	
	public Classifieur getClassifieur() {
		return classifieur;
	}
	public HashMap<String, Categorie> getCategs(){
		return mapCategorie;
	}
	//
	
	
    private String elementType;
    private Set<String> categories = new TreeSet<String>();
    private String categorieCourante;
    private String caracteristiqueCourante;
    private double inf;
    private double sup;
    private ArrayList<String> ensemble = new ArrayList<String>();

    public HandlerPerso() {
    }

    public Set<String> getCategories() {
        return categories;
    }

    // trace du document
    public void startDocument() throws SAXException {
        System.out.println("Start document...\n");
        mapCategorie = new HashMap<String, Categorie>();
    }

    public void endDocument() throws SAXException {
        System.out.println("... document charge.");
    }

    // trace de certains elements
    public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
        elementType = localName;
        if (localName.equals("categorie")) {
            System.out.println("New categorie:");
            mapCarac = new HashMap<String, Domaine>();
        } else {
            if (localName.equals("caracteristique")) {
                System.out.print("\tnew caracteristique: ");

            }
        }
    }

    public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        if (localName.equals("intervalle")) {
            System.out.println("\t\tintervalle: [" + inf + ", " + sup + "]");
            mapCarac.put(caracteristiqueCourante, new IntervalleNumerique("intervalle", inf, sup));
        } else {
            if (localName.equals("caracteristique")) {
                System.out.println("\tend caracteristique: " + this.caracteristiqueCourante + ".");
                mapCarac.put(caracteristiqueCourante, new EnsembleDeChaine(caracteristiqueCourante, ensemble));
                ensemble=new ArrayList<String>();

            } else {
                if (localName.equals("categorie")) {
                    System.out.println("end categorie.\n");
                    if(mere) {
                    	classifieur = new Classifieur(new Categorie(categorieCourante, mapCarac));
                    	mere=false;
                    }
                    else {
                    	categCourante = new Categorie(categorieCourante, mapCarac);
                    	mapCategorie.put(categorieCourante, categCourante);
                    }
                }
                else {
                	if(localName.equals("ensemble")) {
                		System.out.println("\t\tensemble: " + ensemble);
                		mapCarac.put(caracteristiqueCourante, new EnsembleDeChaine(caracteristiqueCourante, ensemble));
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
                        if(contenu.equalsIgnoreCase("TOP"))
                        	mere=true;
                        nomMere=contenu;
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