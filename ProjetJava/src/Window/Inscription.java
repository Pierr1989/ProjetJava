package Window;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import POJO.Client;
import POJO.Organisateur;

public class Inscription extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textFirstName;
	private JTextField textEmail;
	private JTextField textTel;
	private JTextField textAddress;
	private JRadioButton rdBtnClient = new JRadioButton("Client");
	private JRadioButton rdBtnOrganisateur = new JRadioButton("Organisateur");
	private JPasswordField textPassword;
	private Organisateur org = new Organisateur();
	private JComboBox comboGenre;
	private String[] tabGenre = {"Homme", "Femme", "Autre"};
	private String genre;
	private JDateChooser dateChooser = new JDateChooser();
	private String telRegexp = "^[0-9]{4}[- .]?[0-9]{2}[- .]?[0-9]{2}[- .]?[0-9]{2}$";

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
		
		JLabel lblNewLabel_6 = new JLabel("Email :");
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
		textName.setBounds(111, 95, 86, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(111, 123, 86, 20);
		contentPane.add(textFirstName);
		textFirstName.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(111, 201, 86, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(111, 148, 86, 20);
		contentPane.add(textTel);
		textTel.setColumns(10);
		
		textAddress = new JTextField();
		textAddress.setBounds(111, 173, 86, 20);
		contentPane.add(textAddress);
		textAddress.setColumns(10);
		
		// Valider l'enregistrement + en tant que client ou organisateur
		JButton btnValidate = new JButton("Valider");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdBtnClient.isSelected()) {
					Client cli = new Client();
					Date dateChooserFormated = dateChooser.getDate();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
					if(textName.getText().isEmpty() || textFirstName.getText().isEmpty() || textAddress.getText().isEmpty() || textTel.getText().isEmpty() || textEmail.getText().isEmpty() || textPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}	
					else if(dateChooser.getDate() == null)
	                    JOptionPane.showMessageDialog(null, "Date vide !");
					else if(!textTel.getText().matches(telRegexp))
	                    JOptionPane.showMessageDialog(null, "Numéro invalide !");
					else if(cli.checkEmail(textEmail.getText()))
						JOptionPane.showMessageDialog(null, "Email existant !");
					else {
						genre = comboGenre.getSelectedItem().toString();
						String birthdate = dateFormat.format(dateChooserFormated);
						cli = new Client(textName.getText(), textFirstName.getText(), textAddress.getText(), textPassword.getText(), textEmail.getText(), "Client", textTel.getText(), genre, birthdate);
						if(cli.add()) {
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
					Organisateur orga = new Organisateur();
					if(textName.getText().isEmpty() || textFirstName.getText().isEmpty() || textAddress.getText().isEmpty() || textTel.getText().isEmpty() || textEmail.getText().isEmpty() || textPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
					}	
					else if(!textTel.getText().matches(telRegexp))
	                    JOptionPane.showMessageDialog(null, "Numéro invalide !");
					else if(orga.checkEmail(textEmail.getText()))
						JOptionPane.showMessageDialog(null, "Email existant !");
					else {
						org = new Organisateur(textName.getText(), textFirstName.getText(), textAddress.getText(), textPassword.getText(), textEmail.getText(), "Organisateur", textTel.getText());
						if(org.add()) {							
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
		textPassword.setBounds(109, 229, 88, 20);
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
	    
	    JLabel lblNewLabel_10 = new JLabel("Genre (client) :");
	    lblNewLabel_10.setBounds(10, 295, 72, 14);
	    contentPane.add(lblNewLabel_10);
	    
	    comboGenre = new JComboBox(tabGenre);
	    comboGenre.setBounds(109, 287, 88, 30);
	    contentPane.add(comboGenre);
	    
	    JLabel lblNewLabel_11 = new JLabel("Date de naissance :");
	    lblNewLabel_11.setBounds(10, 254, 107, 14);
	    contentPane.add(lblNewLabel_11);
	    
	    dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 254, 174, 20);
		contentPane.add(dateChooser);
		
	}
}
