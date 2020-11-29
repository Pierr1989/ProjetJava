package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import POJO.Categorie;
import POJO.Configuration;
import POJO.Place;
import POJO.Representation;
import POJO.Spectacle;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class CommandeClient extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnBronze;
    private JRadioButton rdbtnArgent;
    private JRadioButton rdbtnOr;
    private JRadioButton rdbtnDiamant;
    private Configuration config = new Configuration();
    private int statut = 0;
    private JTextField textNombrePlaceSouhaitee;
    private JScrollPane scrollPane;
	private DefaultListModel listModel = new DefaultListModel();
	private Place place;// = new Spectacle();
	private JList listePlace;
	private List<Place> liste;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public CommandeClient(Representation rep, Spectacle spec) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 992, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Commande");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(393, 11, 177, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Repr\u00E9sentation choisie : ");
		lblNewLabel_1.setBounds(10, 88, 157, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblRepresentation = new JLabel(spec.toString() + "  -  " + rep.toString());
		lblRepresentation.setBounds(177, 82, 453, 26);
		contentPane.add(lblRepresentation);
		
		rdbtnBronze = new JRadioButton("Bronze");
	    rdbtnBronze.setVisible(false);
	    rdbtnBronze.setSelected(true);
	    rdbtnBronze.setBounds(6, 303, 93, 23);
	    contentPane.add(rdbtnBronze);
	    
	    rdbtnArgent = new JRadioButton("Argent");
	    rdbtnArgent.setVisible(false);
	    rdbtnArgent.setBounds(6, 329, 93, 23);
	    contentPane.add(rdbtnArgent);
	    
	    rdbtnOr = new JRadioButton("Or");
	    rdbtnOr.setVisible(false);
	    rdbtnOr.setBounds(6, 357, 93, 23);
	    contentPane.add(rdbtnOr);
	    
	    rdbtnDiamant = new JRadioButton("Diamant");
	    rdbtnDiamant.setVisible(false);
	    rdbtnDiamant.setBounds(6, 384, 93, 23);
	    contentPane.add(rdbtnDiamant);	
	    
	  //Group the radio buttons.
	    ButtonGroup groupCat = new ButtonGroup();
	    groupCat.add(rdbtnBronze);
	    groupCat.add(rdbtnArgent);
	    groupCat.add(rdbtnOr);
	    groupCat.add(rdbtnDiamant);
	    
	    JLabel lblNewLabel_2 = new JLabel("Nombre de places restantes : ");
	    lblNewLabel_2.setBounds(10, 142, 177, 26);
	    contentPane.add(lblNewLabel_2);
	    
	    JButton btnAnnuler = new JButton("Annuler");
	    btnAnnuler.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
	    	}
	    });
	    btnAnnuler.setBounds(416, 578, 89, 23);
	    contentPane.add(btnAnnuler);
	    
	    JButton btnQuitter = new JButton("Quitter");
	    btnQuitter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}
	    });
	    btnQuitter.setBounds(625, 578, 89, 23);
	    contentPane.add(btnQuitter);
	    
	    JLabel lblBronze = new JLabel("Places Bronze");
	    lblBronze.setBounds(10, 179, 89, 14);
	    contentPane.add(lblBronze);
	    
	    JLabel lblArgent = new JLabel("Places Argent");
	    lblArgent.setBounds(109, 179, 78, 14);
	    contentPane.add(lblArgent);
	    
	    JLabel lblOr = new JLabel("Places Or");
	    lblOr.setBounds(220, 179, 95, 14);
	    contentPane.add(lblOr);
	    
	    JLabel lblDiamant = new JLabel("Places Diamant");
	    lblDiamant.setBounds(330, 179, 95, 14);
	    contentPane.add(lblDiamant);	    
	    
	    JLabel lblPlacesBronze = new JLabel("");
	    lblPlacesBronze.setBounds(20, 204, 46, 14);
	    contentPane.add(lblPlacesBronze);
	    
	    JLabel lblPlacesArgent = new JLabel("");
	    lblPlacesArgent.setBounds(119, 204, 46, 14);
	    contentPane.add(lblPlacesArgent);
	    
	    JLabel lblPlacesOr = new JLabel("");
	    lblPlacesOr.setBounds(218, 204, 46, 14);
	    contentPane.add(lblPlacesOr);
	    
	    JLabel lblPlacesDiamant = new JLabel("");
	    lblPlacesDiamant.setBounds(340, 204, 46, 14);
	    contentPane.add(lblPlacesDiamant);
	    
	    JLabel lblNewLabel_3 = new JLabel("Type place:");
	    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	    lblNewLabel_3.setBounds(10, 282, 105, 14);
	    contentPane.add(lblNewLabel_3);
	    
	    JLabel lblNewLabel_4 = new JLabel("RESERVER :");
	    lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNewLabel_4.setBounds(10, 259, 109, 14);
	    contentPane.add(lblNewLabel_4);
	    
	    JLabel lblNewLabel_5 = new JLabel("Nombre de place(s):");
	    lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	    lblNewLabel_5.setBounds(121, 282, 124, 14);
	    contentPane.add(lblNewLabel_5);
	    
	    textNombrePlaceSouhaitee = new JTextField();
	    textNombrePlaceSouhaitee.setBounds(131, 304, 86, 20);
	    contentPane.add(textNombrePlaceSouhaitee);
	    textNombrePlaceSouhaitee.setColumns(10);
	    
	    JLabel lblNewLabel_6 = new JLabel("Contenu actuel de votre panier:");
	    lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	    lblNewLabel_6.setBounds(311, 282, 194, 14);
	    contentPane.add(lblNewLabel_6);
	    
	    AffichageListePlace();
	    
	    JLabel lblNewLabel_7 = new JLabel("Mode de livraison :");
	    lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	    lblNewLabel_7.setBounds(515, 282, 124, 14);
	    contentPane.add(lblNewLabel_7);
	    
	    JRadioButton rdbtnSurPlace = new JRadioButton("Sur place");
	    rdbtnSurPlace.setSelected(true);
	    rdbtnSurPlace.setBounds(521, 303, 109, 23);
	    contentPane.add(rdbtnSurPlace);
	    
	    JRadioButton rdbtnPrior = new JRadioButton("Envoi \"prior\"");
	    rdbtnPrior.setBounds(521, 329, 109, 23);
	    contentPane.add(rdbtnPrior);
	    
	    JRadioButton rdbtnSecurise = new JRadioButton("Envoi \"s\u00E9curis\u00E9\" (+10\u20AC)");
	    rdbtnSecurise.setBounds(521, 357, 144, 23);
	    contentPane.add(rdbtnSecurise);
	    
	  //Group the radio buttons.
	    ButtonGroup groupLivraison = new ButtonGroup();
	    groupLivraison.add(rdbtnSurPlace);
	    groupLivraison.add(rdbtnPrior);
	    groupLivraison.add(rdbtnSecurise);
	    
	    JLabel lblNewLabel_8 = new JLabel("Mode de payement:");
	    lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	    lblNewLabel_8.setBounds(683, 282, 130, 14);
	    contentPane.add(lblNewLabel_8);
	    
	    JRadioButton rdbtnVisa = new JRadioButton("Visa");
	    rdbtnVisa.setSelected(true);
	    rdbtnVisa.setBounds(683, 303, 109, 23);
	    contentPane.add(rdbtnVisa);
	    
	    JRadioButton rdbtnPaypal = new JRadioButton("Paypal");
	    rdbtnPaypal.setBounds(683, 329, 109, 23);
	    contentPane.add(rdbtnPaypal);
	    
	    JRadioButton rdbtnSepa = new JRadioButton("SEPA");
	    rdbtnSepa.setBounds(683, 357, 109, 23);
	    contentPane.add(rdbtnSepa);
	    
	  //Group the radio buttons.
	    ButtonGroup groupPayement = new ButtonGroup();
	    groupPayement.add(rdbtnVisa);
	    groupPayement.add(rdbtnPaypal);
	    groupPayement.add(rdbtnSepa);
	    
	    JButton btnValiderNombrePlace = new JButton("OK");
	    btnValiderNombrePlace.setBounds(226, 303, 46, 23);
	    contentPane.add(btnValiderNombrePlace);
	    
	    JButton btnNewButton = new JButton("VALIDER");
	    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnNewButton.setBounds(836, 329, 118, 23);
	    contentPane.add(btnNewButton);
	    
	    // Affichage de diamant selon configuration
	    Configuration confChoisie = new Configuration();
	    confChoisie = spec.getConfigDuSpectacle(spec.getIdSpectacle());
	    List<Categorie> catChoisie = new LinkedList<Categorie>();
	    catChoisie = config.getCategorieDeLaConfiguration(confChoisie.getIdConfiguration());
	    System.out.println(catChoisie);

	    if(getStatut(confChoisie) == 1)	{
	    	 rdbtnBronze.setVisible(false);
	         rdbtnArgent.setVisible(false);
	         rdbtnOr.setVisible(false);
	         rdbtnDiamant.setVisible(false);
	         lblBronze.setVisible(false);
	         lblPlacesBronze.setVisible(false);
	         lblArgent.setVisible(false);
	         lblPlacesArgent.setVisible(false);
	         lblOr.setVisible(false);
	         lblPlacesOr.setVisible(false);
	         lblDiamant.setVisible(false);
	         lblPlacesDiamant.setVisible(false);
	    }
	    else if(getStatut(confChoisie) == 2) {
	    	 rdbtnBronze.setVisible(true);
	         rdbtnArgent.setVisible(true);
	         rdbtnOr.setVisible(true);
	         rdbtnDiamant.setVisible(false);
	         lblArgent.setVisible(true);
	         lblPlacesArgent.setVisible(true);
	         lblOr.setVisible(true);
	         lblPlacesOr.setVisible(true);
	         lblDiamant.setVisible(false);
	         lblPlacesDiamant.setVisible(false);
	         
	         for(int i = 0; i< catChoisie.size(); i++) {
	        	 if(catChoisie.get(i).getType().equals("Bronze"))
	        		 lblPlacesBronze.setText(catChoisie.get(i).getNbrPlaceDispo()+"");
	        	 if(catChoisie.get(i).getType().equals("Argent"))
	        		 lblPlacesArgent.setText(catChoisie.get(i).getNbrPlaceDispo()+"");
	        	 if(catChoisie.get(i).getType().equals("Or"))
	        		 lblPlacesOr.setText(catChoisie.get(i).getNbrPlaceDispo()+"");
	         }
	    }
	    
	    else {//(getStatut(confChoisie) == 3) {	             
	    	 rdbtnBronze.setVisible(true);
	         rdbtnArgent.setVisible(true);
	         rdbtnOr.setVisible(true);
	         rdbtnDiamant.setVisible(true);
	         lblArgent.setVisible(true);
	         lblPlacesArgent.setVisible(true);
	         lblOr.setVisible(true);
	         lblPlacesOr.setVisible(true);
	         lblDiamant.setVisible(true);
	         lblPlacesDiamant.setVisible(true);
	         
	         for(int i = 0; i< catChoisie.size(); i++) {
	        	 if(catChoisie.get(i).getType().equals("Bronze"))
	        		 lblPlacesBronze.setText(catChoisie.get(i).getNbrPlaceDispo()+"");
	        	 if(catChoisie.get(i).getType().equals("Argent"))
	        		 lblPlacesArgent.setText(catChoisie.get(i).getNbrPlaceDispo()+"");
	        	 if(catChoisie.get(i).getType().equals("Or"))
	        		 lblPlacesOr.setText(catChoisie.get(i).getNbrPlaceDispo()+"");
	        	 if(catChoisie.get(i).getType().equals("Diamant"))
	        		 lblPlacesDiamant.setText(catChoisie.get(i).getNbrPlaceDispo()+"");
	         }
	    }
	}
	
	private int getStatut(Configuration config) {
		if(config.getType().equals("Debout"))
			return 1;
		if(config.getType().equals("Concert"))
			return 2;
		if(config.getType().equals("Cirque"))
			return 3;
		return 0;
	}
	
	private void AffichageListePlace() {
        place = new Place();

        liste = new LinkedList<Place>();
        liste = place.getAll();
        for (Place place : liste) {
            listModel.addElement(place);
        }

        scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(282, 303, 233, 137);
        contentPane.add(scrollPane);
        listePlace = new JList();
        scrollPane.setViewportView(listePlace);
        
        listePlace.setVisibleRowCount(3);
        listePlace.setModel(listModel);
    }
}
