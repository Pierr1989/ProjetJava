package Window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Client;
import POJO.Organisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Font;

public class Inscription extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textFirstName;
	private JTextField textPseudo;
	private JTextField textTel;
	private JTextField textAddress;
	JRadioButton rdBtnClient = new JRadioButton("Client");
	JRadioButton rdBtnOrganisateur = new JRadioButton("Organisateur");
	private JPasswordField textPassword;
	Client cli = new Client();
	Organisateur org = new Organisateur();
	private long numeroEntre;
	private String tel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription frame = new Inscription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inscription() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INSCRIPTION");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(182, 11, 175, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom : ");
		lblNewLabel_1.setBounds(10, 95, 88, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom :");
		lblNewLabel_2.setBounds(10, 120, 88, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Adresse :");
		lblNewLabel_3.setBounds(10, 173, 88, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Num\u00E9ro tel : ");
		lblNewLabel_4.setBounds(10, 148, 89, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Mot de passe : ");
		lblNewLabel_5.setBounds(10, 229, 88, 14);
		contentPane.add(lblNewLabel_5);
		
		// Quitter programme
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(242, 308, 95, 30);
		contentPane.add(btnQuitter);
		
		JLabel lblNewLabel_6 = new JLabel("Pseudonyme :");
		lblNewLabel_6.setBounds(10, 198, 88, 14);
		contentPane.add(lblNewLabel_6);
		
		// Retour menu
		JButton btnAccueil = new JButton("Retour");
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnAccueil.setBounds(446, 308, 88, 30);
		contentPane.add(btnAccueil);
		
		textName = new JTextField();
		textName.setBounds(89, 92, 86, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(89, 120, 86, 20);
		contentPane.add(textFirstName);
		textFirstName.setColumns(10);
		
		textPseudo = new JTextField();
		textPseudo.setBounds(89, 198, 86, 20);
		contentPane.add(textPseudo);
		textPseudo.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(89, 145, 86, 20);
		contentPane.add(textTel);
		textTel.setColumns(10);
		
		// Vérification entrée telephone, number only
				textTel.addKeyListener(new java.awt.event.KeyAdapter() {

		            public void keyReleased(java.awt.event.KeyEvent evt) {
		                try {
		                    numeroEntre = Long.parseLong(textTel.getText());
		                } 
		                catch (Exception e) {
		                    JOptionPane.showMessageDialog(rootPane, "Veuillez écrire des nombres !");
		                    textTel.setText("");
		                }
		            }
		        });
		
		textAddress = new JTextField();
		textAddress.setBounds(89, 170, 86, 20);
		contentPane.add(textAddress);
		textAddress.setColumns(10);
		
		// Valider l'enregistrement + en tant que client ou organisateur
		JButton btnValidate = new JButton("Valider");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdBtnClient.isSelected()) {
					tel = Long.toString(numeroEntre);
					if(textName.getText().isEmpty() || textFirstName.getText().isEmpty() || textAddress.getText().isEmpty() || textTel.getText().isEmpty() || textPseudo.getText().isEmpty() || textPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}
					
					else if(cli.VerifTel("0"+tel)) {
                        JOptionPane.showMessageDialog(null, "Ce téléphone est déjà lié à un compte existant!");
                    }
					
					else {
						tel = Long.toString(numeroEntre);
						cli = new Client(textName.getText(), textFirstName.getText(), textAddress.getText(), "0"+tel, textPseudo.getText(), textPassword.getText());
						if(cli.add(cli)) {
							JOptionPane.showMessageDialog(null, "Enregistrement réussi !");		
							Accueil frame = new Accueil();
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Enregistrement échoué !");
						}
					}	
				}
				
				if(rdBtnOrganisateur.isSelected()) {					
					tel = Long.toString(numeroEntre);
					if(textName.getText().isEmpty() || textFirstName.getText().isEmpty() || textAddress.getText().isEmpty() || textTel.getText().isEmpty() || textPseudo.getText().isEmpty() || textPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}	
					
					else if(org.VerifTel("0"+tel)) {
                        JOptionPane.showMessageDialog(null, "Ce téléphone est déjà lié à un compte existant!");
                    }
					
					else {
						tel = Long.toString(numeroEntre);
						org = new Organisateur(textName.getText(), textFirstName.getText(), textAddress.getText(), "0"+tel, textPseudo.getText(), textPassword.getText());
						if(org.add(org)) {							
							JOptionPane.showMessageDialog(null, "Enregistrement réussi !");
							Accueil frame = new Accueil();
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Enregistrement échoué !");
						}	
					}
				}														
			}
		});
		btnValidate.setBounds(420, 146, 88, 30);
		contentPane.add(btnValidate);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(87, 226, 88, 20);
		contentPane.add(textPassword);
		rdBtnClient.setSelected(true);
				
		rdBtnClient.setBounds(262, 150, 109, 23);
		contentPane.add(rdBtnClient);
		
		rdBtnOrganisateur.setBounds(262, 175, 109, 23);
		contentPane.add(rdBtnOrganisateur);
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdBtnClient);
	    group.add(rdBtnOrganisateur);
	    
	    JLabel lblNewLabel_7 = new JLabel("Type de compte :");
	    lblNewLabel_7.setBounds(265, 132, 106, 14);
	    contentPane.add(lblNewLabel_7);
	    
	    JLabel lblNewLabel_8 = new JLabel("--->");
	    lblNewLabel_8.setBounds(207, 154, 30, 14);
	    contentPane.add(lblNewLabel_8);
	    
	    JLabel lblNewLabel_9 = new JLabel("--->");
	    lblNewLabel_9.setBounds(380, 154, 30, 14);
	    contentPane.add(lblNewLabel_9);
	}
}
