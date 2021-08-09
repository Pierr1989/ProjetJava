package Window;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Accueil extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil frame = new Accueil();
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
	public Accueil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 496);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenue au Bosquet Wallon !");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(147, 11, 424, 40);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		// Vers enregistrement client
		JButton btnEnregistrer = new JButton("S'enregistrer");
		btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inscription frame = new Inscription();				
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnEnregistrer.setBounds(639, 160, 118, 33);
		contentPane.add(btnEnregistrer);
		
		// Bouton de connexion
		JButton btnConnecter = new JButton("Se connecter");
		btnConnecter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection frame = new Connection();				
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnConnecter.setBounds(625, 65, 140, 33);
		contentPane.add(btnConnecter);
		
		JButton btnSpectacles = new JButton("Liste des spectacles");
		btnSpectacles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListeDesSpectacles frame = new ListeDesSpectacles();				
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnSpectacles.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnSpectacles.setBounds(10, 65, 197, 33);
		contentPane.add(btnSpectacles);
		
		// Quitter programme proprement
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(341, 413, 100, 33);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("Pas encore inscrit ?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(639, 138, 126, 21);
		contentPane.add(lblNewLabel_1);
	}
}
