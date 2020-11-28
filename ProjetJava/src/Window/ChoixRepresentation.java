package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import POJO.PlanningSalle;
import POJO.Representation;
import POJO.Spectacle;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChoixRepresentation extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private DefaultListModel<Representation> listModel = new DefaultListModel<>();
	private JList<Representation> listeRepresentations = new JList<Representation>();
	private Representation repre;
	private List<Representation> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public ChoixRepresentation(Spectacle spectacle) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("S\u00E9lectionnez une repr\u00E9sentation :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(303, 11, 428, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Votre s\u00E9lection :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(45, 126, 160, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNomSpectacle = new JLabel(spectacle.getTitre());
		lblNomSpectacle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNomSpectacle.setBounds(180, 124, 329, 24);
		contentPane.add(lblNomSpectacle);
		
		JLabel lblNewLabel_2 = new JLabel("Veuillez choisir une repr\u00E9sentation : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(20, 161, 320, 24);
		contentPane.add(lblNewLabel_2);
		
		
		//affichageListe();
		
		repre = new Representation();
		List<Representation> list = new LinkedList<Representation>();		
        list = repre.getRepresentationDuSpectacle(spectacle.getIdSpectacle());
        for (Representation rep : list) {
            listModel.addElement(rep);
        }
		
        scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 196, 326, 209);	
		contentPane.add(scrollPane);
		scrollPane.setViewportView(listeRepresentations);
		listeRepresentations.setVisibleRowCount(3);
		listeRepresentations.setModel(listModel);
		
		JButton btnValider = new JButton("Valider s\u00E9lection");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listeRepresentations.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un spectacle !");
				}
				else {
					String s = (String) listeRepresentations.getSelectedValue().toString();
		            Representation dataToOtherFRame = listeRepresentations.getSelectedValue();
		           
	            	CommandeClient frame = new CommandeClient(dataToOtherFRame, spectacle);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);  
				    dispose();
				}             
		
			}
		});
		btnValider.setBounds(103, 416, 134, 23);
		contentPane.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnAnnuler.setBounds(456, 502, 89, 23);
		contentPane.add(btnAnnuler);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(865, 502, 89, 23);
		contentPane.add(btnQuitter);
	}
}
