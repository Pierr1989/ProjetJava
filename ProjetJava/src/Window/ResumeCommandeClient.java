package Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Client;
import POJO.Commande;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResumeCommandeClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPaiment;
	private JLabel lblLivraison;
	private JLabel lblCoutTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public ResumeCommandeClient(Commande commande, Client cli) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel = new JLabel("R\u00E9sum\u00E9 commande ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 599, 40);
		contentPane.add(lblNewLabel);
		
		// Sous-titre
		JLabel lblNewLabel_1 = new JLabel("Mode de paiement : ");
		lblNewLabel_1.setBounds(10, 71, 113, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mode de livraison : ");
		lblNewLabel_2.setBounds(10, 98, 113, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total :");
		lblNewLabel_3.setBounds(416, 71, 111, 30);
		contentPane.add(lblNewLabel_3);
		
		// Contenu global
		lblPaiment = new JLabel("");
		lblPaiment.setBounds(125, 79, 160, 14);
		contentPane.add(lblPaiment);
		
		lblLivraison = new JLabel("");
		lblLivraison.setBounds(125, 106, 160, 14);
		contentPane.add(lblLivraison);
		
		lblCoutTotal = new JLabel("");
		lblCoutTotal.setBounds(426, 106, 162, 14);
		contentPane.add(lblCoutTotal);
		
		initFrame(commande);
		
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspaceClient frame = new EspaceClient(cli);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);  
				dispose();
			}
		});
		btnBack.setBounds(125, 263, 120, 40);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(347, 263, 113, 40);
		contentPane.add(btnExit);
	}
	
	private void initFrame(Commande commande) {
		lblPaiment.setText(commande.getModeDePayement());
		lblLivraison.setText(commande.getModeDeLivraison());
		lblCoutTotal.setText(commande.getCout()+"");
	}
}
