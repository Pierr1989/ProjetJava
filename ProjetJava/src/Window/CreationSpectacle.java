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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import POJO.Artiste;
import POJO.Categorie;
import POJO.Configuration;
import POJO.Organisateur;
import POJO.Place;
import POJO.PlanningSalle;
import POJO.Reservation;
import POJO.Spectacle;

public class CreationSpectacle extends JFrame implements ItemListener{

	private static final long serialVersionUID = 1L;																			/*ATTRIBUTS*/
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
	private int statutLimite = 0;
	private JTextField textFieldDescription;
	private JTextField textFieldBronze;
	private JTextField textFieldArgent;
	private JTextField textFieldOr;
	private JTextField textFieldDiamant;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_5_1;
	private JLabel lblNewLabel_5_2;
	private JLabel lblNewLabel_5_2_1;
	JLabel lblNewLabel_10;
	private ButtonGroup group ;
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
	    group = new ButtonGroup();
	    group.add(rdbtnDebout);
	    group.add(rdbtnConcert);
	    group.add(rdbtnCirque);
	    
	    rdbtnDebout.addItemListener(this);
	    rdbtnConcert.addItemListener(this);
	    rdbtnCirque.addItemListener(this);
	    
	    
	    List<Artiste> list = new LinkedList<Artiste>();		
        list = artiste.getAll();
        for (Artiste artiste : list) {
            listModel.addElement(artiste);
        }
		
	    
	    JButton btnCrerSpectacle = new JButton("CREER SPECTACLE");
	    btnCrerSpectacle.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(rdbtnDebout.isSelected()) {
	    			if(txtPrix.getText().isEmpty() || txtTitre.getText().isEmpty() || textFieldDescription.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}
	    			else if(textPlaceParClient.getText().isEmpty()) {
	    				saisiePlace = 0;
	    			}
	    			else {
	    				saisiePlace = Integer.parseInt(textPlaceParClient.getText());
	    				config = new Configuration("Debout", "Place debout");
	    				if(config.add()) {
	    					spectacle = new Spectacle(plan, txtTitre.getText(),textFieldDescription.getText(), saisiePlace, config);
		    				if(spectacle.add()) {
		    					double prixLoca = plan.prixLocation();
				    			res = new Reservation(orga, 0.0,0.0,"",prixLoca);
				    			if(res.add()) {
				    				plan.setReservation(res);
					    			plan.Update();		    						    			
				    				double prix = numeroEntre;
				    				cat = new Categorie(config.getType(), prix, 8000, saisiePlace, config);
				    				if(cat.add()) {
				    					ValidationSpectacle frame = new ValidationSpectacle(spectacle, plan);
									    frame.setLocationRelativeTo(null);
									    frame.setVisible(true);  
									    dispose();
				    				}
				    				else
				    					JOptionPane.showMessageDialog(null, "Echec categorie !");
			    				}	
			    				else
			    					JOptionPane.showMessageDialog(null, "Echec reservation !");
				    			}
		    				else
		    					JOptionPane.showMessageDialog(null, "Echec spectacle !");
	    				}
	    				else
	    					JOptionPane.showMessageDialog(null, "Echec configuration !");
	    			}
	    				
	    		}
	    		
				if(rdbtnConcert.isSelected()) {	//TODO dans le if avec les verif, ajoutet categorie,...
					if(textFieldBronze.getText().isEmpty() || textFieldArgent.getText().isEmpty() || textFieldOr.getText().isEmpty() || txtTitre.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}	
					else if(textPlaceParClient.getText().isEmpty()) {
	    				saisiePlace = 0;
	    			}
	    			else {
	    				saisiePlace = Integer.parseInt(textPlaceParClient.getText());
	    				config = new Configuration("Concert", "Place concert");
	    				if(config.add()) {
	    					spectacle = new Spectacle(plan, txtTitre.getText(),textFieldDescription.getText(), saisiePlace, config);
		    				if(spectacle.add()) {
		    					double prixLoca = plan.prixLocation();
				    			res = new Reservation(orga, 0.0,0.0,"",prixLoca);
				    			if(res.add()) {
				    				boolean erreur = false;
				    				plan.setReservation(res);
					    			plan.Update();	
				    				cat = new Categorie(config.getType(), Integer.parseInt(textFieldBronze.getText()), 3000, saisiePlace, config);
				    				if(!cat.add()) {
				    					erreur = true;
				    				}
				    				cat = new Categorie(config.getType(), Integer.parseInt(textFieldArgent.getText()), 1500, saisiePlace, config);
				    				if(!cat.add()) {
				    					erreur = true;
				    				}
				    				cat = new Categorie(config.getType(), Integer.parseInt(textFieldOr.getText()), 500, saisiePlace, config);
				    				if(!cat.add()) {
				    					erreur = true;
				    				}
				    				if(!erreur) {
				    					ValidationSpectacle frame = new ValidationSpectacle(spectacle, plan);
									    frame.setLocationRelativeTo(null);
									    frame.setVisible(true);  
									    dispose();
				    				}
				    				else
				    					JOptionPane.showMessageDialog(null, "Echec categorie !");
			    				}	
			    				else
			    					JOptionPane.showMessageDialog(null, "Echec reservation !");
				    			}
		    				else
		    					JOptionPane.showMessageDialog(null, "Echec spectacle !");
	    				}
	    				else
	    					JOptionPane.showMessageDialog(null, "Echec configuration !");
	    			}
				}
				
				if(rdbtnCirque.isSelected()) {
					if(textFieldBronze.getText().isEmpty() || textFieldArgent.getText().isEmpty() || textFieldOr.getText().isEmpty() || textFieldDiamant.getText().isEmpty() || txtTitre.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}
					else if(textPlaceParClient.getText().isEmpty()) {
	    				saisiePlace = 0;
	    			}
	    			else {
	    				saisiePlace = Integer.parseInt(textPlaceParClient.getText());
	    				config = new Configuration("Cirque", "Place cirque");
	    				if(config.add()) {
	    					spectacle = new Spectacle(plan, txtTitre.getText(),textFieldDescription.getText(), saisiePlace, config);
		    				if(spectacle.add()) {
		    					double prixLoca = plan.prixLocation();
				    			res = new Reservation(orga, 0.0,0.0,"",prixLoca);
				    			if(res.add()) {
				    				boolean erreur = false;
				    				plan.setReservation(res);
					    			plan.Update();	
				    				cat = new Categorie(config.getType(), Integer.parseInt(textFieldBronze.getText()), 1500, saisiePlace, config);
				    				if(!cat.add()) {
				    					erreur = true;
				    				}
				    				cat = new Categorie(config.getType(), Integer.parseInt(textFieldArgent.getText()), 1500, saisiePlace, config);
				    				if(!cat.add()) {
				    					erreur = true;
				    				}
				    				cat = new Categorie(config.getType(), Integer.parseInt(textFieldOr.getText()), 2000, saisiePlace, config);
				    				if(!cat.add()) {
				    					erreur = true;
				    				}
				    				cat = new Categorie(config.getType(), Integer.parseInt(textFieldDiamant.getText()), 1000, saisiePlace, config);
				    				if(!cat.add()) {
				    					erreur = true;
				    				}
				    				if(!erreur) {
				    					ValidationSpectacle frame = new ValidationSpectacle(spectacle, plan);
									    frame.setLocationRelativeTo(null);
									    frame.setVisible(true);  
									    dispose();
				    				}
				    				else
				    					JOptionPane.showMessageDialog(null, "Echec categorie !");
			    				}	
			    				else
			    					JOptionPane.showMessageDialog(null, "Echec reservation !");
				    			}
		    				else
		    					JOptionPane.showMessageDialog(null, "Echec spectacle !");
	    				}
	    				else
	    					JOptionPane.showMessageDialog(null, "Echec configuration !");
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
	    
	    lblNewLabel_10 = new JLabel("Prix place basique :");
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
	    
	    JButton btnQuitter = new JButton("QUITTER");
	    btnQuitter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		plan.AnnulerReservation(plan);
	    		System.exit(0);
	    	}
	    });
	    btnQuitter.setBounds(539, 463, 102, 30);
	    contentPane.add(btnQuitter);
	    
	    JLabel lblNewLabel_3 = new JLabel("Qtt\u00E9 places par Client :");
	    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblNewLabel_3.setBounds(377, 215, 223, 21);
	    contentPane.add(lblNewLabel_3);
	    
	    textPlaceParClient = new JTextField();
	    textPlaceParClient.setBounds(409, 252, 109, 20);
	    contentPane.add(textPlaceParClient);
	    textPlaceParClient.setColumns(10);
	    
	    JLabel lblNewLabel_2_1 = new JLabel("Description du spectacle :");
	    lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblNewLabel_2_1.setBounds(633, 296, 259, 19);
	    contentPane.add(lblNewLabel_2_1);
	    
	    textFieldDescription = new JTextField();
	    textFieldDescription.setBounds(633, 326, 259, 19);
	    contentPane.add(textFieldDescription);
	    textFieldDescription.setColumns(10);
	    
	    lblNewLabel_5 = new JLabel("Bronze :");
	    lblNewLabel_5.setBounds(10, 370, 46, 14);
	    lblNewLabel_5.setVisible(false);
	    contentPane.add(lblNewLabel_5);
	    
	    lblNewLabel_5_1 = new JLabel("Argent :");
	    lblNewLabel_5_1.setBounds(10, 392, 46, 14);
	    lblNewLabel_5_1.setVisible(false);
	    contentPane.add(lblNewLabel_5_1);
	    
	    lblNewLabel_5_2 = new JLabel("Or :");
	    lblNewLabel_5_2.setBounds(10, 414, 46, 14);
	    lblNewLabel_5_2.setVisible(false);
	    contentPane.add(lblNewLabel_5_2);
	    
	    lblNewLabel_5_2_1 = new JLabel("Diamant :");
	    lblNewLabel_5_2_1.setBounds(10, 439, 46, 14);
	    lblNewLabel_5_2_1.setVisible(false);
	    contentPane.add(lblNewLabel_5_2_1);
	    
	    textFieldBronze = new JTextField();
	    textFieldBronze.setBounds(66, 367, 109, 17);
	    textFieldBronze.setVisible(false);
	    contentPane.add(textFieldBronze);
	    textFieldBronze.setColumns(10);
	    
	    textFieldArgent = new JTextField();
	    textFieldArgent.setBounds(66, 389, 109, 17);
	    textFieldArgent.setVisible(false);
	    contentPane.add(textFieldArgent);
	    textFieldArgent.setColumns(10);
	    
	    textFieldOr = new JTextField();
	    textFieldOr.setBounds(66, 411, 109, 17);
	    textFieldOr.setVisible(false);
	    contentPane.add(textFieldOr);
	    textFieldOr.setColumns(10);
	    
	    textFieldDiamant = new JTextField();
	    textFieldDiamant.setBounds(66, 439, 109, 21);
	    textFieldDiamant.setVisible(false);
	    contentPane.add(textFieldDiamant);
	    textFieldDiamant.setColumns(10);
	    
	    JButton btnRetour = new JButton("Retour");
	    btnRetour.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		EspaceOrganisateur frame = new EspaceOrganisateur(orga);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
	    	}
	    });
	    btnRetour.setBounds(396, 467, 89, 23);
	    contentPane.add(btnRetour);
	    
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
	
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				Object source = e.getSource();
				if(source == rdbtnDebout) {
						lblNewLabel_5.setVisible(false);
		    			lblNewLabel_5_1.setVisible(false);
		    			lblNewLabel_5_2.setVisible(false);
		    			lblNewLabel_5_2_1.setVisible(false);
		    			textFieldBronze.setVisible(false);
		    			textFieldArgent.setVisible(false);
		    			textFieldOr.setVisible(false);
		    			textFieldDiamant.setVisible(false);
		    			lblNewLabel_10.setVisible(true);
		    			txtPrix.setVisible(true);
				}
				else if(source == rdbtnConcert) {
						lblNewLabel_5.setVisible(true);
		    			lblNewLabel_5_1.setVisible(true);
		    			lblNewLabel_5_2.setVisible(true);
		    			lblNewLabel_5_2_1.setVisible(false);
		    			textFieldBronze.setVisible(true);
		    			textFieldArgent.setVisible(true);
		    			textFieldOr.setVisible(true);
		    			textFieldDiamant.setVisible(false);
		    			lblNewLabel_10.setVisible(false);
		    			txtPrix.setVisible(false);
				}
				else {
						lblNewLabel_5.setVisible(true);
		    			lblNewLabel_5_1.setVisible(true);
		    			lblNewLabel_5_2.setVisible(true);
		    			lblNewLabel_5_2_1.setVisible(true);
		    			textFieldBronze.setVisible(true);
		    			textFieldArgent.setVisible(true);
		    			textFieldOr.setVisible(true);
		    			textFieldDiamant.setVisible(true);
		    			lblNewLabel_10.setVisible(false);
		    			txtPrix.setVisible(false);
				}
			}
			
		}
	
	private void afficherPrixLocation(PlanningSalle plan) {
		if(plan == null) {
			JLabel lblMontantLocation = new JLabel("0");
		    lblMontantLocation.setBounds(212, 108, 46, 14);
		    contentPane.add(lblMontantLocation);
		}
		else {
			double prixLocation = plan.prixLocation();
			String prixLocaString = "" + prixLocation;
			
			JLabel lblMontantLocation = new JLabel(prixLocaString);
		    lblMontantLocation.setBounds(212, 108, 46, 14);
		    contentPane.add(lblMontantLocation);
		}		
	}
}
