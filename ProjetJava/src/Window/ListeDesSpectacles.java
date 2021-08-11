package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import POJO.PlanningSalle;
import POJO.Spectacle;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ListeDesSpectacles extends JFrame {
	private JPanel contentPane;
	private JScrollPane scrollPane;// = new JScrollPane();
	private DefaultListModel listModel = new DefaultListModel();
	private Spectacle spec;// = new Spectacle();
	private JList listeSpec;
	private List<Spectacle> list;
	private static final long serialVersionUID = 1L;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeDesSpectacles frame = new ListeDesSpectacles();
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
	public ListeDesSpectacles() {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTE DES SPECTACLES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(5, 5, 937, 41);
		contentPane.add(lblNewLabel);
		
		AffichageListe();
		
		JButton btnAccueil = new JButton("Retour \u00E0 l'accueil");
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnAccueil.setBounds(801, 490, 122, 23);
		contentPane.add(btnAccueil);
		
		// Vers enregistrement client
		JButton btnEnregistrer = new JButton("S'enregistrer");
		btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inscription frame = new Inscription();				
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnEnregistrer.setBounds(118, 255, 118, 33);
		contentPane.add(btnEnregistrer);
		
		// Bouton de connexion
		JButton btnConnecter = new JButton("Se connecter");
		btnConnecter.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection frame = new Connection();				
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnConnecter.setBounds(251, 255, 122, 33);
		contentPane.add(btnConnecter);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(407, 490, 89, 23);
		contentPane.add(btnQuitter);
		
		JLabel lblNewLabel_1 = new JLabel("Envie de voir un spectacle? Inscrivez/Connectez-vous d'abord");
		lblNewLabel_1.setBounds(98, 235, 442, 23);
		contentPane.add(lblNewLabel_1);
		
		//spec = new Spectacle();
		spec.afficherSpecEtArtiste();
	}
	
	private void AffichageListe() {
        spec = new Spectacle();
        listeSpec = new JList();

        list = new LinkedList<Spectacle>();
        list = spec.getAll();
        for (Spectacle spectacle : list) {
            listModel.addElement(spectacle);
        }
        
        listeSpec.setVisibleRowCount(3);
        listeSpec.setModel(listModel);
        listeSpec.setBounds(98, 86, 285, 119);

        scrollPane = new JScrollPane(listeSpec, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(98, 66, 285, 139);
        contentPane.add(scrollPane);
    }
}
