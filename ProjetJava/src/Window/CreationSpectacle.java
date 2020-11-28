package Window;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Artiste;
import POJO.Categorie;
import POJO.Configuration;
import POJO.Organisateur;
import POJO.Place;
import POJO.PlanningSalle;
import POJO.Reservation;
import POJO.Spectacle;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class CreationSpectacle extends JFrame {

																				/*ATTRIBUTS*/
	private JPanel contentPane;
	private JTextField txtTitre;
	private Artiste artiste = new Artiste();
	private JTextField textPseudo;
	private Spectacle spectacle = new Spectacle();
	private Configuration config = new Configuration();
	private Categorie cat = new Categorie();
	private JTextField txtPrix;
	private Reservation res = new Reservation();
	private Place place = new Place();
	private DefaultListModel listModel = new DefaultListModel();
	private JScrollPane scrollPane = new JScrollPane();
	private double numeroEntre;
	private int saisiePlace;
	private JRadioButton rdbtnDebout;
    private JRadioButton rdbtnConcert;
    private JRadioButton rdbtnCirque;
    private JRadioButton rdbtnBronze;
    private JRadioButton rdbtnArgent;
    private JRadioButton rdbtnOr;
    private JRadioButton rdbtnDiamant;
    private int statut;
	private JTextField textPlaceParClient;
																				/*FIN ATTRIBUTS*/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public CreationSpectacle(PlanningSalle plan, Organisateur orga) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel label = new JLabel(plan.toString());
		label.setBounds(23, 98, 182, 35);
		contentPane.add(label);
		
		// Retour menu
		JButton btnAccueil = new JButton("Annuler cr\u00E9ation");
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plan.AnnulerReservation(plan);
				Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnAccueil.setBounds(386, 461, 123, 30);
		contentPane.add(btnAccueil);
		
		JLabel lblNewLabel = new JLabel("Rappel date s\u00E9lectionn\u00E9e:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 73, 182, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cr\u00E9ation de spectacle");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(359, 11, 348, 41);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Titre du spectacle :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(633, 215, 188, 19);
		contentPane.add(lblNewLabel_2);
		
		txtTitre = new JTextField();
		txtTitre.setBounds(682, 254, 102, 20);
		contentPane.add(txtTitre);
		txtTitre.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Configuration :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(6, 215, 143, 19);
		contentPane.add(lblNewLabel_4);
		
		rdbtnDebout = new JRadioButton("Debout");
		rdbtnDebout.setSelected(true);
		rdbtnDebout.setBounds(6, 253, 109, 23);
		contentPane.add(rdbtnDebout);
		
		rdbtnConcert = new JRadioButton("Concert");
		rdbtnConcert.setBounds(6, 279, 109, 23);
		contentPane.add(rdbtnConcert);
		
		rdbtnCirque = new JRadioButton("Cirque");
		rdbtnCirque.setBounds(6, 304, 109, 23);
		contentPane.add(rdbtnCirque);
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnDebout);
	    group.add(rdbtnConcert);
	    group.add(rdbtnCirque);
	    
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
	    
	    ItemListener itemListenerConcert = new ItemListener() {
	        public void itemStateChanged(ItemEvent itemEvent) {
	          //AbstractButton rdbtnConcert = (AbstractButton)itemEvent.getSource();
	          int state = itemEvent.getStateChange();

	          if (state == ItemEvent.SELECTED) {
	        	  rdbtnBronze.setVisible(true);
	        	  rdbtnArgent.setVisible(true);
	        	  rdbtnOr.setVisible(true);
		      }  
	          else {
	        	  rdbtnBronze.setVisible(false);
	        	  rdbtnArgent.setVisible(false);
	        	  rdbtnOr.setVisible(false);
	          }
	        }
	      };
	    rdbtnConcert.addItemListener(itemListenerConcert);
	    
	    ItemListener itemListenerCirque = new ItemListener() {
	        public void itemStateChanged(ItemEvent itemEvent) {
	          //AbstractButton rdbtnCirque = (AbstractButton)itemEvent.getSource();
	          int state = itemEvent.getStateChange();

	          if (state == ItemEvent.SELECTED) {
	        	  rdbtnBronze.setVisible(true);
	        	  rdbtnArgent.setVisible(true);
	        	  rdbtnOr.setVisible(true);
	        	  rdbtnDiamant.setVisible(true);
		      }  
	          else {
	        	  rdbtnBronze.setVisible(false);
	        	  rdbtnArgent.setVisible(false);
	        	  rdbtnOr.setVisible(false);
	        	  rdbtnDiamant.setVisible(false);
	          }
	        }
	      };
	    rdbtnCirque.addItemListener(itemListenerCirque);
	    
	    List<Artiste> list = new LinkedList<Artiste>();		
        list = artiste.getAll();
        for (Artiste artiste : list) {
            listModel.addElement(artiste);
        }
		
	    
	    JButton btnCrerSpectacle = new JButton("CREER SPECTACLE");
	    btnCrerSpectacle.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(rdbtnDebout.isSelected()) {
	    			if(txtPrix.getText().isEmpty() || textPlaceParClient.getText().isEmpty() || txtTitre.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}
	    			else {
	    				spectacle = new Spectacle(plan.getId(), txtTitre.getText(), saisiePlace);
	    				if(spectacle.add(spectacle)) {
	    					double prixLoca = res.prixLocation(plan);
			    			res = new Reservation(0.0,0.0,"",prixLoca,orga.getId());
			    			res.add(res);		    			
			    			plan.setIdReservation(res.getIdReservation());
			    			plan.Update(plan);		    						    			
			    			config = new Configuration("Debout", "place debout");
			    			config.add(config);
		    				double prix = numeroEntre;
		    				cat = new Categorie(config.getType(), prix, 5000, 5000, config.getIdConfiguration());
			    			cat.add(cat);		    						    			
			    			ValidationSpectacle frame = new ValidationSpectacle(spectacle, plan);
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);  
						    dispose();
	    				}	
	    				else
	    					JOptionPane.showMessageDialog(null, "Echec création !");
	    			}
	    		}
				if(rdbtnConcert.isSelected()) {	//TODO dans le if avec les verif, ajoutet categorie,...
					if(txtPrix.getText().isEmpty() || textPlaceParClient.getText().isEmpty() || txtTitre.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}				
					else {
						statut = selectionCategorie();
	    				spectacle = new Spectacle(plan.getId(), txtTitre.getText(), saisiePlace);
	    				if(spectacle.add(spectacle)) {
	    					double prixLoca = res.prixLocation(plan);
			    			res = new Reservation(0.0,0.0,"",prixLoca,orga.getId());
			    			res.add(res);		    			
			    			plan.setIdReservation(res.getIdReservation());
			    			plan.Update(plan);		    						    			
			    			config = new Configuration("Concert", "place assise");
			    			config.add(config);
			    			double prix = numeroEntre;
			    			prix = cat.prixPlace(statut, prix);
		    				cat = new Categorie(config.getType(), prix, 5000, 5000, config.getIdConfiguration());
			    			cat.add(cat);		    			
			    			
			    			ValidationSpectacle frame = new ValidationSpectacle(spectacle, plan);
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);  
						    dispose();
			    			
			    			JOptionPane.showMessageDialog(null, "Spectacle créé avec succès !");
	    				}	
	    				else
	    					JOptionPane.showMessageDialog(null, "Echec création !");
	    			}
					
				}
				if(rdbtnCirque.isSelected()) {
					if(txtPrix.getText().isEmpty() || textPlaceParClient.getText().isEmpty() || txtTitre.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}
					else {
						statut = selectionCategorie();
	    				spectacle = new Spectacle(plan.getId(), txtTitre.getText(), saisiePlace);
	    				if(spectacle.add(spectacle)) {
	    					double prixLoca = res.prixLocation(plan);
			    			res = new Reservation(0.0,0.0,"",prixLoca,orga.getId());
			    			res.add(res);		    			
			    			plan.setIdReservation(res.getIdReservation());
			    			plan.Update(plan);
							config = new Configuration("Cirque", "place assise");
			    			config.add(config);
			    			double prix = numeroEntre;
		    				cat = new Categorie(config.getType(), prix, 5000, 5000, config.getIdConfiguration());
			    			cat.add(cat);		    			
			    			
			    			ValidationSpectacle frame = new ValidationSpectacle(spectacle, plan);
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);  
						    dispose();
			    			
			    			JOptionPane.showMessageDialog(null, "Spectacle créé avec succès !");
	    				}	
	    				else
	    					JOptionPane.showMessageDialog(null, "Echec création !");
	    			}
					
				}
	    	}
	    });
	    btnCrerSpectacle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    btnCrerSpectacle.setBounds(386, 390, 149, 60);
	    contentPane.add(btnCrerSpectacle);
	    
	    JLabel lblNewLabel_8 = new JLabel("Espace cr\u00E9ation de spectacle");
	    lblNewLabel_8.setBounds(409, 181, 158, 14);
	    contentPane.add(lblNewLabel_8);
	    
	    JLabel lblNewLabel_10 = new JLabel("Prix place basique :");
	    lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblNewLabel_10.setBounds(159, 215, 182, 19);
	    contentPane.add(lblNewLabel_10);
	    
	    txtPrix = new JTextField();
	    txtPrix.setBounds(191, 252, 109, 20);
	    contentPane.add(txtPrix);
	    txtPrix.setColumns(10);
	    
	 // Vérification entrée number only
	    txtPrix.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    numeroEntre = Long.parseLong(txtPrix.getText());
                } 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Veuillez écrire un prix valide !");
                    txtPrix.setText("");
                }
            }
        });

	    
	    JLabel lblNewLabel_11 = new JLabel("Montant du pour location salle :");
	    lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNewLabel_11.setBounds(202, 73, 242, 14);
	    contentPane.add(lblNewLabel_11);
	    
	    JLabel lblNewLabel_12 = new JLabel("Sous-cat\u00E9gorie :");
	    lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblNewLabel_12.setBounds(6, 340, 169, 20);
	    contentPane.add(lblNewLabel_12);
	    
	    JButton btnQuitter = new JButton("QUITTER");
	    btnQuitter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		plan.AnnulerReservation(plan);
	    		System.exit(0);
	    	}
	    });
	    btnQuitter.setBounds(537, 461, 102, 30);
	    contentPane.add(btnQuitter);
	    
	    JLabel lblNewLabel_3 = new JLabel("Qtt\u00E9 places par Client :");
	    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblNewLabel_3.setBounds(377, 215, 223, 21);
	    contentPane.add(lblNewLabel_3);
	    
	    textPlaceParClient = new JTextField();
	    textPlaceParClient.setBounds(409, 252, 109, 20);
	    contentPane.add(textPlaceParClient);
	    textPlaceParClient.setColumns(10);
	    
	    textPlaceParClient.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                	saisiePlace = Integer.parseInt(textPlaceParClient.getText());
                } 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Veuillez écrire un nombre valide !");
                    textPlaceParClient.setText("");
                }
            }
        });

	    afficherPrixLocation(plan);
	}
	
	private void afficherPrixLocation(PlanningSalle prix) {
		if(prix == null) {
			JLabel lblMontantLocation = new JLabel("0");
		    lblMontantLocation.setBounds(212, 108, 46, 14);
		    contentPane.add(lblMontantLocation);
		}
		else {
			double prixLocation = res.prixLocation(prix);
			String prixLocaString = "" + prixLocation;
			
			JLabel lblMontantLocation = new JLabel(prixLocaString);
		    lblMontantLocation.setBounds(212, 108, 46, 14);
		    contentPane.add(lblMontantLocation);
		}	
	}
	
	private int selectionCategorie() {

		if(rdbtnConcert.isSelected() && rdbtnBronze.isSelected())
			return 1;
		if(rdbtnConcert.isSelected() && rdbtnArgent.isSelected())
			return 2;
		if(rdbtnConcert.isSelected() && rdbtnOr.isSelected())
			return 3;
		if(rdbtnCirque.isSelected() && rdbtnBronze.isSelected())
			return 4;
		if(rdbtnCirque.isSelected() && rdbtnArgent.isSelected())
			return 5;
		if(rdbtnCirque.isSelected() && rdbtnOr.isSelected())
			return 6;
		if(rdbtnCirque.isSelected() && rdbtnDiamant.isSelected())
			return 7;
		return 0;
	}
}
