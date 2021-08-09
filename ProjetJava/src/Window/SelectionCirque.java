package Window;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import POJO.Categorie;
import POJO.Client;
import POJO.Commande;
import POJO.Configuration;
import POJO.Place;
import POJO.Spectacle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class SelectionCirque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
    private JTextField textNbrTicket;
    private Categorie categorie;
    private List<Categorie> listeCategorie;
    private JLabel lblNewLabel_2;
    private JLabel lblPlaceMax;
    private Place place;
    private int nbrClientPlace;
    private Categorie catDiamant;
    private Categorie catOr;
    private Categorie catArgent;
    private Categorie catBronze;    
    private int valeurMax;
    private int valeurMaxDiamant;
    private int valeurMaxOr;
    private int valeurMaxArgent;
    private int valeurMaxBronze;
    private int statut;
    private JLabel lblNewLabel_4;
    private JLabel lblPrixPlaceUnitaire;
    private JRadioButton rdbtnDiamant;
    private JRadioButton rdbtnOr;
    private JRadioButton rdbtnArgent;
    private JRadioButton rdbtnBronze;
    private JLabel lblNewLabel_3;
    private double prixCatPlace; //Prix total des catégories
    private double totalPrixCatPlace;
    private JButton btnConfirmAdd;
    int cptPlace = 0; // Compter nombre de place obtenu selon différentes catégories

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public SelectionCirque(Client cli, Commande commande, Spectacle spec, Configuration config) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel = new JLabel("Configuration concert");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 763, 48);
		contentPane.add(lblNewLabel);
		
		// Sous-titre
	    lblNewLabel_1 = new JLabel("Choisir nombre de place(s) :");
	    lblNewLabel_1.setBounds(51, 221, 162, 41);
	    contentPane.add(lblNewLabel_1);
	    
	    lblNewLabel_2 = new JLabel("Place disponible :");
	    lblNewLabel_2.setBounds(51, 99, 131, 18);
	    contentPane.add(lblNewLabel_2);
	    
	    // Contenu global	    	    
	    textNbrTicket = new JTextField();
	    textNbrTicket.setBounds(223, 231, 86, 20);
	    contentPane.add(textNbrTicket);	
	    
	    lblPlaceMax = new JLabel("");
	    lblPlaceMax.setBounds(183, 99, 86, 18);
	    contentPane.add(lblPlaceMax);
	    
	    lblNewLabel_4 = new JLabel("Prix/place : ");
	    lblNewLabel_4.setBounds(51, 153, 79, 32);
	    contentPane.add(lblNewLabel_4);
	    
	    lblPrixPlaceUnitaire = new JLabel("");
	    lblPrixPlaceUnitaire.setBounds(223, 153, 73, 32);
	    contentPane.add(lblPrixPlaceUnitaire);
	    
	    rdbtnDiamant = new JRadioButton("Diamant");
	    rdbtnDiamant.setBounds(542, 142, 109, 23);
	    contentPane.add(rdbtnDiamant);
	    
	    rdbtnOr = new JRadioButton("Or");
	    rdbtnOr.setSelected(true);
	    rdbtnOr.setBounds(542, 168, 109, 23);
	    contentPane.add(rdbtnOr);
	    
	    rdbtnArgent = new JRadioButton("Argent");
	    rdbtnArgent.setBounds(542, 202, 109, 23);
	    contentPane.add(rdbtnArgent);
	    
	    rdbtnBronze = new JRadioButton("Bronze");
	    rdbtnBronze.setBounds(542, 239, 109, 23);
	    contentPane.add(rdbtnBronze);
	    
	    lblNewLabel_3 = new JLabel("Choisir une cat\u00E9gorie : ");
	    lblNewLabel_3.setBounds(434, 100, 162, 17);
	    contentPane.add(lblNewLabel_3);
	    
	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnDiamant);
	    group.add(rdbtnOr);
	    group.add(rdbtnArgent);
	    group.add(rdbtnBronze);
	    
	    // Initialiser composants
	    initFrame(config, spec);
	    
	    // Créer place + calcul selon les différentes catégories
	    getPlaceFrame(commande, cli);
	    
	    // Valider le total des tickets et envoyer commande finale
	    btnValidateFrame(commande, cli);
	}
	
	private void initFrame(Configuration config, Spectacle spec) {
		listeCategorie = new LinkedList<Categorie>();	
		listeCategorie = config.getCategorieDeLaConfiguration(config.getIdConfiguration());
		for(int i = 0; i<listeCategorie.size(); i++) {
			if(listeCategorie.get(i).getType().equals("Diamant")) {
				valeurMaxDiamant = listeCategorie.get(i).getNbrPlaceDispo();
				catDiamant = listeCategorie.get(i);
			}
			
			if(listeCategorie.get(i).getType().equals("Or")) {
				valeurMaxOr = listeCategorie.get(i).getNbrPlaceDispo();
				catOr = listeCategorie.get(i);
			}
				
			if(listeCategorie.get(i).getType().equals("Argent")) {
				valeurMaxArgent = listeCategorie.get(i).getNbrPlaceDispo();
				catArgent = listeCategorie.get(i);
			}
				
			if(listeCategorie.get(i).getType().equals("Bronze")) {
				valeurMaxBronze = listeCategorie.get(i).getNbrPlaceDispo();
				catBronze = listeCategorie.get(i);
			}				
		}
		
		// Si pas de valeur de limitation saisie ==> Valeur catégorie
		if(spec.getNbrPlaceParClient() == 0) {
			valeurMax = valeurMaxDiamant + valeurMaxOr + valeurMaxArgent + valeurMaxBronze;
			lblPlaceMax.setText(valeurMax+"");
			statut = 1;
		}
		
		// Si valeur de limitation ==> Valeur spectacle
		else {
			lblPlaceMax.setText(spec.getNbrPlaceParClient()+"");	
			valeurMax = spec.getNbrPlaceParClient();
			statut = 2;
		}				
	}
	
	private void getPlaceFrame(Commande commande, Client cli) {
		JButton btnValider = new JButton("Ajouter ticket");
	    btnValider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		nbrClientPlace = Integer.parseInt(textNbrTicket.getText());
	    		boolean flag = false;
	    		place = new Place();
	    		
	    		// Ne faire l'update que si valeur max est toujours positif
	    		if(valeurMax > 0) {
	    			if(statut == 1) {
	    				// Si or
	    				if(rdbtnDiamant.isSelected()) {
	    					valeurMaxDiamant = catDiamant.diminuerNbreDePlaceTotal(nbrClientPlace);
	    					catDiamant.setNbrPlaceDispo(valeurMaxDiamant);
	    					catDiamant.update(catDiamant);
	    					
	    					// Creation des places
	    					for(int i = 0; i<nbrClientPlace; i++) {
				    			place = new Place(catDiamant.getPrix(), commande.getIdCommande());
				    			flag = place.add(place);
				    			cptPlace++;
				    			prixCatPlace += catDiamant.getPrix();
				    		}
	    				}
	    				
	    				// Si or
	    				if(rdbtnOr.isSelected()) {
	    					valeurMaxOr = catOr.diminuerNbreDePlaceTotal(nbrClientPlace);
	    					catOr.setNbrPlaceDispo(valeurMaxOr);
	    					catOr.update(catOr);
	    					
	    					// Creation des places
	    					for(int i = 0; i<nbrClientPlace; i++) {
				    			place = new Place(catOr.getPrix(), commande.getIdCommande());
				    			flag = place.add(place);
				    			cptPlace++;
				    			prixCatPlace += catOr.getPrix();
				    		}
	    				}

	    				// Si argent
	    				if(rdbtnArgent.isSelected()) {
	    					valeurMaxArgent = catArgent.diminuerNbreDePlaceTotal(nbrClientPlace);
	    					catArgent.setNbrPlaceDispo(valeurMaxArgent);
	    					catArgent.update(catArgent);
	    					
	    					// Creation des places
	    					for(int i = 0; i<nbrClientPlace; i++) {
				    			place = new Place(catArgent.getPrix(), commande.getIdCommande());
				    			flag = place.add(place);
				    			cptPlace++;
				    			prixCatPlace += catArgent.getPrix();
				    		}
	    				}
	    				
	    				// Si bronze
	    				if(rdbtnBronze.isSelected()) {
	    					valeurMaxBronze = catBronze.diminuerNbreDePlaceTotal(nbrClientPlace);
	    					catBronze.setNbrPlaceDispo(valeurMaxBronze);
	    					catBronze.update(catBronze);
	    					
	    					// Creation des places
	    					for(int i = 0; i<nbrClientPlace; i++) {
				    			place = new Place(catBronze.getPrix(), commande.getIdCommande());
				    			flag = place.add(place);
				    			cptPlace++;
				    			prixCatPlace += catBronze.getPrix();
				    		}
	    				}
	    				valeurMax = valeurMaxDiamant + valeurMaxOr + valeurMaxArgent + valeurMaxBronze;
			    		lblPlaceMax.setText(valeurMax+"");	    				    		
	    			}
	    			
	    			else {
	    				// Si diamant
	    				if(rdbtnDiamant.isSelected()) {
	    					valeurMaxDiamant = catDiamant.diminuerNbreDePlaceTotal(nbrClientPlace);
	    					catDiamant.setNbrPlaceDispo(valeurMaxDiamant);
	    					catDiamant.update(catDiamant);
	    					
	    					// Creation des places
	    					for(int i = 0; i<nbrClientPlace; i++) {
				    			place = new Place(catDiamant.getPrix(), commande.getIdCommande());
				    			flag = place.add(place);
				    			cptPlace++;
				    			prixCatPlace += catDiamant.getPrix();
				    		}
	    				}
	    				
	    				// Si or
	    				if(rdbtnOr.isSelected()) {
	    					valeurMaxOr = catOr.diminuerNbreDePlaceTotal(nbrClientPlace);
	    					catOr.setNbrPlaceDispo(valeurMaxOr);
	    					catOr.update(catOr);
	    					
	    					// Creation des places
	    					for(int i = 0; i<nbrClientPlace; i++) {
				    			place = new Place(catOr.getPrix(), commande.getIdCommande());
				    			flag = place.add(place);
				    			cptPlace++;
				    			prixCatPlace += catOr.getPrix();
				    		}
	    				}

	    				// Si argent
	    				if(rdbtnArgent.isSelected()) {
	    					valeurMaxArgent = catArgent.diminuerNbreDePlaceTotal(nbrClientPlace);
	    					catArgent.setNbrPlaceDispo(valeurMaxArgent);
	    					catArgent.update(catArgent);
	    					
	    					// Creation des places
	    					for(int i = 0; i<nbrClientPlace; i++) {
				    			place = new Place(catArgent.getPrix(), commande.getIdCommande());
				    			flag = place.add(place);
				    			cptPlace++;
				    			prixCatPlace += catArgent.getPrix();
				    		}
	    				}
	    				
	    				// Si bronze
	    				if(rdbtnBronze.isSelected()) {
	    					valeurMaxBronze = catBronze.diminuerNbreDePlaceTotal(nbrClientPlace);
	    					catBronze.setNbrPlaceDispo(valeurMaxBronze);
	    					catBronze.update(catBronze);
	    					
	    					// Creation des places
	    					for(int i = 0; i<nbrClientPlace; i++) {
				    			place = new Place(catBronze.getPrix(), commande.getIdCommande());
				    			flag = place.add(place);
				    			cptPlace++;
				    			prixCatPlace += catBronze.getPrix();
				    		}
	    				}
	    				int valeurMaxUpdate;
	    				valeurMax -= nbrClientPlace;
	    				valeurMaxUpdate = categorie.diminuerNbreDePlaceTotal(nbrClientPlace);
		    			categorie.setNbrPlaceDispo(valeurMaxUpdate);
			    		categorie.update(categorie);
			    		lblPlaceMax.setText(valeurMax+"");			    	
	    			}	    					    					    		
	    		}
	    		
	    		else {
	    			JOptionPane.showMessageDialog(null, "Vous ne pouvez plus commander !");
	    		}	    			    			   		
	    	}
	    });
	    btnValider.setBounds(336, 276, 144, 48);
	    contentPane.add(btnValider);
	}
	
	private void btnValidateFrame(Commande commande, Client cli) {
		btnConfirmAdd = new JButton("Valider");
	    btnConfirmAdd.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// Update cout commande
	    		totalPrixCatPlace = commande.calculTotalCommandeConCir(prixCatPlace, commande.getModeDePayement());
    			commande.setCout(totalPrixCatPlace);
    			commande.Update(commande);
    			ResumeCommandeClient frame = new ResumeCommandeClient(commande, cli);				
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);  
				dispose();
	    	}
	    });
	    btnConfirmAdd.setBounds(336, 335, 144, 48);
	    contentPane.add(btnConfirmAdd);
	    
	    
	}
}
