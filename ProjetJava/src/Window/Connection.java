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
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textEmail;
	private JPasswordField textPassword;	

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
		
		JLabel lblNewLabel_1 = new JLabel("Email :");
		lblNewLabel_1.setBounds(289, 202, 71, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe : ");
		lblNewLabel_2.setBounds(288, 227, 87, 14);
		contentPane.add(lblNewLabel_2);
		
		textEmail = new JTextField();
		textEmail.setBounds(430, 199, 86, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
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
				if(textEmail.getText().isEmpty() || textPassword.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
				else {
					if(rdBtnClient.isSelected()) {
						Client cli = new Client();
						cli = cli.login(textEmail.getText(), textPassword.getText());
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
						Organisateur orga = new Organisateur();
						orga = orga.login(textEmail.getText(), textPassword.getText());
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
						Gestionnaire gest = new Gestionnaire();
						gest = gest.login(textEmail.getText(), textPassword.getText());
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
