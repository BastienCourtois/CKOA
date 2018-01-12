package ihm;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.xml.sax.SAXException;

import classifieur.Classifieur;
import classifieur.Observation;

/**
 *
 * @author carre
 */
@SuppressWarnings("serial")
public class ihmCkoa extends JFrame {

	static final int HTEXT = 10; // dimensions des zones de textes
	static final int WTEXT = 30;
	static final int HAUT = 800; // dimensions par defaut de de la frame
	static final int LARG = 800;
	static final String FORMAT = ".png"; // format (.png, .png, .tiff, ...)

	// CKOA
	private Observation obs;
	// CKOA/

	// L'application interfacee:
	// Classifieur classeur = ...
	// "en dur" pour le test:
	String[] caracteristiques = { "forme", "taille", "feuilles" };
	// Nom du fichier parametre de l'application:
	// + ".bin": serialise
	// + ".png" : image correspondante
	String nomFichier;

	// Variables d'etats de l'IHM
	// observation en cours de saisie:
	// Observation obs = ...
	// caracteristique selectionnee:
	String caracteristique;
	String valeurCaracteristique;

	// Composants d'interface
	ScrollPane vue = new ScrollPane(); // vue scrollable sur l'image
	ImageCanvas canvas = new ImageCanvas(); // affichage de l'image
	JButton raz = new JButton("RAZ");
	JComboBox selectCaracteristique; // liste de selection des caracteristiques
	JTextField saisieCaracteristique; // saisie de la valeur de la caracteristique selectionne
	JTextArea textObservation; // caracteristiques de l'observation en cours

	ihmCkoa(String fileName) {
		// CKOA
		obs = new Observation();
		// CKOA/

		this.nomFichier = fileName;

		setTitle("ckoa");

		// Chargement de l'image et dimensionnement de la vue
		vue.setSize(LARG, HAUT / 2);
		vue.add(canvas);
		canvas.setImage(nomFichier + FORMAT);

		// Caracteristiques: liste de selection
		// "en dur" pour le test
		selectCaracteristique = new JComboBox(this.caracteristiques);
		// selection intiale par defaut
		selectCaracteristique.setSelectedIndex(0);
		caracteristique = (String) selectCaracteristique.getSelectedItem();

		// Zone de saisie des valeurs
		saisieCaracteristique = new JTextField(WTEXT);
		saisieCaracteristique.setBorder(BorderFactory.createLineBorder(Color.black));

		// Zone d'affichage de l'observation en cours
		this.textObservation = new JTextArea(HTEXT, WTEXT);
		textObservation.setEditable(false);
		textObservation.setBorder(BorderFactory.createLineBorder(Color.black));
		textObservation.setText("OBSERVATION:\n");

		// Ajout et positionnement des composants d'interface
		Container cp = getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.add(vue);
		cp.add(raz);
		cp.add(selectCaracteristique);
		cp.add(saisieCaracteristique);
		cp.add(textObservation);

		// Ecouteurs d'evenements
		selectCaracteristique.addActionListener(new SelectListener());
		saisieCaracteristique.addActionListener(new InputListener());
		raz.addActionListener(new RAZListener());
		this.addWindowListener(new CkoaListener());

	}

	//quand l'utilisateur à choyé de la caractéristique
	class SelectListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			caracteristique = (String) selectCaracteristique.getSelectedItem();
		}
	}

	//quand l'utilisateur ajoute la caractéristique (touche entrée)
	class InputListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			valeurCaracteristique = saisieCaracteristique.getText();
			//si l'observation n'a pas déjà cette caractéristique
			if (!obs.trouveCaracteristique(caracteristique)) {
				obs.ajoutCaracteristique(caracteristique, valeurCaracteristique);
				update();
				saisieCaracteristique.setText("");
			}
		}
	}

	//met à jour 
	public void update() {
		textObservation.append("\t- " + this.caracteristique + ": " + this.valeurCaracteristique);
		textObservation.append("\n");
	}

	class RAZListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			selectCaracteristique.setSelectedIndex(0);
			caracteristique = (String) selectCaracteristique.getSelectedItem();
			valeurCaracteristique = "";
			saisieCaracteristique.setText("");
			textObservation.setText("OBSERVATION:\n");
			obs = new Observation();
		}
	}

	class ImageCanvas extends Canvas {

		java.awt.Image image;

		void setImage(String fileName) {
			image = this.getToolkit().createImage(fileName);
			// wait for the image to be loaded
			MediaTracker mediaTracker = new MediaTracker(this);
			mediaTracker.addImage(image, 0);
			try {
				mediaTracker.waitForAll();
			} catch (InterruptedException e) {
				return;
			}
			// then repaint ...
			this.setSize(new Dimension(image.getWidth(this), image.getHeight(this)));
			repaint(0);
		}

		@Override
		public synchronized void paint(Graphics g) {
			if (image != null) {
				g.drawImage(image, 0, 0, this);
			}
		}
	}

	class CkoaListener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public static void main(String argv[]) throws SAXException, IOException, InterruptedException {
		if (argv.length != 1) {
			System.err.println("usage: java ihm.ProtoCkoa <fichier image> (sans extension)");
		} else {

			ihmCkoa ihm = new ihmCkoa(argv[0]);
			ihm.pack();
			ihm.setSize(LARG, HAUT);
			ihm.setVisible(true);
			
			//Application
			Classifieur classifieur = new Classifieur();
			classifieur.loadXML("ressources/arbres/arbres");
			classifieur.show("arbres2", "png");
		}
	}
}
