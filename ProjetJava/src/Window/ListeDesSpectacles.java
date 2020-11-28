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
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(407, 490, 89, 23);
		contentPane.add(btnQuitter);
		
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
