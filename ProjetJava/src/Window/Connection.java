package Window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Client;
import POJO.Gestionnaire;
import POJO.Organisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Connection extends JFrame {

	private JPanel contentPane;
	private JTextField textTelephone;
	private JPasswordField textPassword;
	private long numeroEntre;
	private String tel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection frame = new Connection();
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
	public Connection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Veuillez vous connectez");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(188, 11, 424, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00E9l\u00E9phone :");
		lblNewLabel_1.setBounds(289, 202, 71, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe : ");
		lblNewLabel_2.setBounds(288, 227, 87, 14);
		contentPane.add(lblNewLabel_2);
		
		textTelephone = new JTextField();
		textTelephone.setBounds(430, 199, 86, 20);
		contentPane.add(textTelephone);
		textTelephone.setColumns(10);
		
		// Vérification entrée telephone, number only
		textTelephone.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    numeroEntre = Long.parseLong(textTelephone.getText());
                } 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Veuillez écrire des nombres !");
                    textTelephone.setText("");
                }
            }
        });
		
		
		textPassword = new JPasswordField();
		textPassword.setBounds(430, 224, 86, 20);
		contentPane.add(textPassword);
		
		JRadioButton rdBtnClient = new JRadioButton("Client");
		rdBtnClient.setSelected(true);
		rdBtnClient.setBounds(407, 108, 63, 23);
		contentPane.add(rdBtnClient);
		
		JRadioButton rdBtnOrganisateur = new JRadioButton("Organisateur");
		rdBtnOrganisateur.setBounds(483, 108, 127, 23);
		contentPane.add(rdBtnOrganisateur);
		
		JRadioButton rdBtnGestionnaire = new JRadioButton("Gestionnaire (admin)");
		rdBtnGestionnaire.setBounds(618, 108, 195, 23);
		contentPane.add(rdBtnGestionnaire);
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdBtnClient);
	    group.add(rdBtnOrganisateur);
	    group.add(rdBtnGestionnaire);
		
		// Button de connexion 
		JButton btnValide = new JButton("Valider");
		btnValide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdBtnClient.isSelected()) {
					tel = Long.toString(numeroEntre);
					Client cli = new Client();
					cli = cli.login("0"+tel, textPassword.getText());
					if(cli != null) {
						EspaceClient frame = new EspaceClient(cli);
					    frame.setLocationRelativeTo(null);
					    frame.setVisible(true);  
					    dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Erreur de connection");
					
				}
				if(rdBtnOrganisateur.isSelected()) {
					tel = Long.toString(numeroEntre);
					Organisateur orga = new Organisateur();
					orga = orga.login("0"+tel, textPassword.getText());
					if(orga != null) {
						EspaceOrganisateur frame = new EspaceOrganisateur(orga);
					    frame.setLocationRelativeTo(null);
					    frame.setVisible(true);  
					    dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Erreur de connection");
				}
				if(rdBtnGestionnaire.isSelected()) {
					tel = Long.toString(numeroEntre);
					Gestionnaire gest = new Gestionnaire();
					gest = gest.login("0"+tel, textPassword.getText());
					if(gest != null) {
						EspaceGestionnaire frame = new EspaceGestionnaire(gest);
					    frame.setLocationRelativeTo(null);
					    frame.setVisible(true);  
					    dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Erreur de connection");
				}
			}
		});
		btnValide.setBounds(353, 252, 89, 23);
		contentPane.add(btnValide);
		
		// Bouton retour vers menu
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnBack.setBounds(641, 413, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_3 = new JLabel("Se connecter en tant que :");
		lblNewLabel_3.setBounds(262, 112, 139, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnQuitter = new JButton("QUITTER");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(746, 413, 89, 23);
		contentPane.add(btnQuitter);
	
		
	}
}
