package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectionDebout extends JFrame {

	private JPanel contentPane;
    private JLabel lblTypeConfig;
    private JLabel lblNewLabel_1;
    private JTextField txtFieldNbreDePlace;
    private Categorie categorie;
    private int nombreMax;
    private JLabel lblNewLabel_2;
    private JLabel lblPlaceMax;
    private Place place;
    private JLabel lblNewLabel_3;
    private JLabel lblTotalCommande;
    private JButton btnValiderCommande;
    private int nbrClientPlace;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public SelectionDebout(Client cli, Commande commande, Spectacle spec) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		////////////////////////////////////////////////////////////////////////////////// LABELS ///////////////////////////////////////////////////////////////////////////////
		
		JLabel lblNewLabel = new JLabel("S\u00E9lection des places Debout");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(290, 23, 370, 37);
		contentPane.add(lblNewLabel);
	    
	    /////////////TYPE DE CONFIGURATION///////////////
	    lblTypeConfig = new JLabel("");
	    lblTypeConfig.setBounds(10, 282, 116, 14);
	    contentPane.add(lblTypeConfig);

	    Configuration confChoisie = new Configuration();
	    confChoisie = spec.getConfigDuSpectacle(spec.getIdSpectacle());
	    lblTypeConfig.setText(confChoisie.getType());
	    
	    //////////NOMBRE DE PLACE SAISIE/////////////////
	    lblNewLabel_1 = new JLabel("Choisir nombre de place(s):");
	    lblNewLabel_1.setBounds(105, 307, 148, 14);
	    contentPane.add(lblNewLabel_1);
	    	    
	    txtFieldNbreDePlace = new JTextField();
	    txtFieldNbreDePlace.setBounds(127, 330, 86, 20);
	    contentPane.add(txtFieldNbreDePlace);
	    txtFieldNbreDePlace.setColumns(10);
	    
	    
	    //////////AFFICHAGE LIMITE CLIENT/////////////////
	    lblNewLabel_2 = new JLabel("Nbre de place limite:");
	    lblNewLabel_2.setBounds(105, 361, 137, 14);
	    contentPane.add(lblNewLabel_2);
	    
	    lblPlaceMax = new JLabel("");
	    lblPlaceMax.setBounds(127, 388, 86, 14);
	    contentPane.add(lblPlaceMax);
	    
	    categorie = confChoisie.getlimiteDebout(confChoisie.getIdConfiguration());
	    nombreMax = categorie.getNbrPlaceMax();
	    if(nombreMax >= 0)
	    	lblPlaceMax.setText(nombreMax+"");
	    
	    
	    //////////////////VALIDATION PLACE/////////////////	    
	    JButton btnValider = new JButton("Valider");
	    if(categorie.getNbrPlaceDispo() == 0)
	    	btnValider.setVisible(false);
	    btnValider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		nbrClientPlace = Integer.parseInt(txtFieldNbreDePlace.getText());
	    		int resultatDiminutionClient = categorie.diminuerNbreDePlaceClient(nbrClientPlace);
	    		int resultatDiminutionTotale = categorie.diminuerNbreDePlaceTotal(nbrClientPlace);
	    		categorie.setNbrPlaceMax(resultatDiminutionClient);
	    		categorie.setNbrPlaceDispo(resultatDiminutionTotale);
	    		categorie.update(categorie);
	    		nombreMax = categorie.getNbrPlaceMax();	//maj places restantes max par client	    		
	    		lblPlaceMax.setText(nombreMax+"");
	    		
	    		//CREATION PLACE//
	    		if(nombreMax > 0) {
	    			OperationPlace(commande);
	    		}
	    			
	    		else if(nombreMax == 0 || nombreMax < 0) {
	    			lblPlaceMax.setText("");
	    			OperationPlace(commande);
	    		}	    		
	    	}
	    });
	    btnValider.setBounds(236, 329, 89, 23);
	    contentPane.add(btnValider);
	    
	    lblTotalCommande = new JLabel("");		
	    lblTotalCommande.setBounds(440, 333, 148, 14);
	    contentPane.add(lblTotalCommande);
	    
	    
	    //////////////////VALIDATION COMMANDE/////////////////
	    lblNewLabel_3 = new JLabel("Montant total de la commande : ");
	    lblNewLabel_3.setBounds(440, 307, 247, 14);
	    contentPane.add(lblNewLabel_3);
	    	    
	    btnValiderCommande = new JButton("VALIDER COMMANDE");
	    btnValiderCommande.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		double totalCommande = commande.calculTotalCommandeDebout(categorie.getPrix(), nbrClientPlace, commande.getModeDePayement());  	
				lblTotalCommande.setText(totalCommande+"");
	    		commande.setCout(totalCommande);
				commande.Update(commande);
				JOptionPane.showMessageDialog(null, "Commande validée !");
				Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
	    	}
	    });
	    btnValiderCommande.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
	    btnValiderCommande.setBounds(440, 357, 162, 23);
	    contentPane.add(btnValiderCommande);
	    
	   /* JButton btnAnnuler = new JButton("Annuler");
	    btnAnnuler.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		place = new Place();
	    		for(int i=0; i<nbrClientPlace; i++) {	    			
	    			place.delete(place);    			
	    		}	
	    		categorie.setNbrPlaceDispo(categorie.getNbrPlaceDispo()+nbrClientPlace);
	    		categorie.setNbrPlaceMax(categorie.getNbrPlaceMax()+nbrClientPlace);
	    		categorie.update(categorie);
	    		Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
	    	}
	    });
	    btnAnnuler.setBounds(236, 499, 224, 23);
	    contentPane.add(btnAnnuler);*/
	}
	
	private void OperationPlace(Commande commande) {
		Place place = new Place();
		for(int i = 0; i<nbrClientPlace; i++) {
			place = new Place(categorie.getPrix(), commande.getIdCommande());
			place.add(place);
		}  	
		double totalCommande = commande.calculTotalCommandeDebout(categorie.getPrix(), nbrClientPlace, commande.getModeDePayement());  	
		lblTotalCommande.setText(totalCommande+"");
		commande.setCout(totalCommande);
		commande.Update(commande);
	}
}
