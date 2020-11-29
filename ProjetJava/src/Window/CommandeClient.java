package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import POJO.Categorie;
import POJO.Configuration;
import POJO.Representation;
import POJO.Spectacle;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CommandeClient extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnBronze;
    private JRadioButton rdbtnArgent;
    private JRadioButton rdbtnOr;
    private JRadioButton rdbtnDiamant;
    private Configuration config = new Configuration();

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
	    rdbtnBronze.setBounds(6, 367, 109, 23);
	    contentPane.add(rdbtnBronze);
	    
	    rdbtnArgent = new JRadioButton("Argent");
	    rdbtnArgent.setVisible(false);
	    rdbtnArgent.setBounds(6, 393, 109, 23);
	    contentPane.add(rdbtnArgent);
	    
	    rdbtnOr = new JRadioButton("Or");
	    rdbtnOr.setVisible(false);
	    rdbtnOr.setBounds(6, 421, 109, 23);
	    contentPane.add(rdbtnOr);
	    
	    rdbtnDiamant = new JRadioButton("Diamant");
	    rdbtnDiamant.setVisible(false);
	    rdbtnDiamant.setBounds(6, 448, 109, 23);
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
	    
	    // Affichage de diamant selon configuration
	    Configuration confChoisie = new Configuration();
	    confChoisie = spec.getConfigDuSpectacle(spec.getIdSpectacle());
	    List<Categorie> catChoisie = new LinkedList<Categorie>();
	    catChoisie = config.getCategorieDeLaConfiguration(confChoisie.getIdConfiguration());
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
}
