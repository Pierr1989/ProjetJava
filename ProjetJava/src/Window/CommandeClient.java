package Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Client;
import POJO.Commande;
import POJO.Configuration;
import POJO.Representation;
import POJO.Spectacle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CommandeClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Commande commande;
	private String modeDePayement;
	private String modeDeLivraison;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public CommandeClient(Representation rep, Spectacle spec, Client cli) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel = new JLabel("Commande");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 741, 46);
		contentPane.add(lblNewLabel);
		
		// Sous-titre
		JLabel lblNewLabel_1 = new JLabel("Mode paiement : ");
		lblNewLabel_1.setBounds(24, 78, 131, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mode de livraison : ");
		lblNewLabel_2.setBounds(24, 229, 131, 21);
		contentPane.add(lblNewLabel_2);
		
		// Contenu global
		JRadioButton rdbtnVisa = new JRadioButton("Visa");
		rdbtnVisa.setSelected(true);
		rdbtnVisa.setBounds(24, 108, 109, 23);
		contentPane.add(rdbtnVisa);
		
		JRadioButton rdbtnPaypal = new JRadioButton("Paypal");
		rdbtnPaypal.setBounds(25, 134, 109, 23);
		contentPane.add(rdbtnPaypal);
		
		JRadioButton rdbtnSepa = new JRadioButton("SEPA");
		rdbtnSepa.setBounds(25, 160, 109, 23);
		contentPane.add(rdbtnSepa);
		
		ButtonGroup groupPayement = new ButtonGroup();
        groupPayement.add(rdbtnVisa);
        groupPayement.add(rdbtnPaypal);
        groupPayement.add(rdbtnSepa);
		
		JRadioButton rdbtnSurPlace = new JRadioButton("Sur place");
		rdbtnSurPlace.setSelected(true);
		rdbtnSurPlace.setBounds(24, 257, 109, 23);
		contentPane.add(rdbtnSurPlace);
		
		JRadioButton rdbtnPrior = new JRadioButton("Prior");
		rdbtnPrior.setBounds(24, 283, 109, 23);
		contentPane.add(rdbtnPrior);
		
		JRadioButton rdbtnSecurise = new JRadioButton("Livraison \"s\u00E9curis\u00E9\"");
		rdbtnSecurise.setBounds(24, 309, 174, 23);
		contentPane.add(rdbtnSecurise);
        
        ButtonGroup groupLivraison = new ButtonGroup();
        groupLivraison.add(rdbtnSurPlace);
        groupLivraison.add(rdbtnPrior);
        groupLivraison.add(rdbtnSecurise);
        
        JLabel lblNewLabel_3 = new JLabel("Comment payerez-vous?");
		lblNewLabel_3.setBounds(24, 57, 450, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quel moyen de livraison utiliserez-vous?");
		lblNewLabel_4.setBounds(24, 204, 240, 14);
		contentPane.add(lblNewLabel_4);
		
		// Bouton vers ticket
        JButton btnValider = new JButton("S\u00E9lection des tickets");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnVisa.isSelected()) {
					modeDePayement = "Visa";
				}
				if(rdbtnPaypal.isSelected()) {
					modeDePayement = "Paypal";
				}
				if(rdbtnSepa.isSelected()) {
					modeDePayement = "Sepa";
				}
				
				if(rdbtnSurPlace.isSelected()) {
					modeDeLivraison = "SurPlace";
				}
				if(rdbtnPrior.isSelected()) {
					modeDeLivraison = "Prior";
				}
				if(rdbtnSecurise.isSelected()) {
					modeDeLivraison = "Securise";
				}
				
				commande = new Commande(modeDePayement, modeDeLivraison, 0, cli.getId());
				if(commande.add(commande)) {
					JOptionPane.showMessageDialog(null, "Moyen de commande enregistré");
				}
				else
					JOptionPane.showMessageDialog(null, "Moyen de commande râté");
				
				Configuration config = new Configuration();
				config = spec.getConfigDuSpectacle(spec.getIdSpectacle());				
				if(config.getType().equals("Debout")) {
					SelectionDebout frame = new SelectionDebout(cli, commande, spec);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);  
				    dispose();
				}
				else if(config.getType().equals("Concert")) {
					SelectionCirque frame = new SelectionCirque(cli, commande, spec, config);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);  
				    dispose();
				}
				else {
					SelectionCirque frame = new SelectionCirque(cli, commande, spec, config);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);  
				    dispose();
				}			
			}
		});
		btnValider.setBounds(24, 339, 174, 46);
		contentPane.add(btnValider);
		
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAnnuler.setBounds(559, 336, 131, 46);
		contentPane.add(btnAnnuler);
	}	
		
}
