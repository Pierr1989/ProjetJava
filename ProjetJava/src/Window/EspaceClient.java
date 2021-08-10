package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import POJO.Artiste;
import POJO.Client;
import POJO.PlanningSalle;
import POJO.Spectacle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class EspaceClient extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Client cli;
	private Spectacle spec;
	private JScrollPane scrollPane;
	private DefaultListModel<Spectacle> listModel = new DefaultListModel<>();
	private JList<Spectacle> listeSpectacles = new JList<Spectacle>();
	private PlanningSalle planS;
	private List<Spectacle> list;
	private DefaultListModel<Artiste> listModelArtiste = new DefaultListModel<>();
	private JScrollPane scrollPaneArtiste;
	private List<Artiste> listArtiste;
	private JList<Artiste> listeObj;
	private JLabel lblPlanning;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public EspaceClient(Client cli) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//here
		initierFrame(cli);
		
		JLabel lblNewLabel = new JLabel("Salut");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(277, 11, 106, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNomClient = new JLabel("");
		lblNomClient.setBounds(416, 11, 155, 27);
		contentPane.add(lblNomClient);
		
		JLabel lblNewLabel_1 = new JLabel("Que souhaitez-vous faire?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(40, 105, 262, 30);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Consulter mes r\u00E9servations");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(612, 149, 199, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(393, 464, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Voir les spectacles :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(40, 153, 121, 14);
		contentPane.add(lblNewLabel_2);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnRetour.setBounds(277, 464, 89, 23);
		contentPane.add(btnRetour);
		
		//AFFICHAGE SPECTACLES
		
		//affichageListe();
		
		spec = new Spectacle();
		List<Spectacle> list = new LinkedList<Spectacle>();		
        list = spec.getAll();
        for (Spectacle spe : list) {
            listModel.addElement(spe);
        }
		
        scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 179, 326, 209);	
		contentPane.add(scrollPane);
		scrollPane.setViewportView(listeSpectacles);
		listeSpectacles.setVisibleRowCount(3);
		listeSpectacles.setModel(listModel);
		
		JButton btnNewButton = new JButton("S\u00E9lectionnez le spectacle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listeSpectacles.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un spectacle !");
				}
				else {
		            Spectacle dataToOtherFRame = listeSpectacles.getSelectedValue();
		           
	            	ChoixRepresentation frame = new ChoixRepresentation(dataToOtherFRame, cli);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);  
				    dispose();
				}             
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(81, 398, 183, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblDateSpecDynamique = new JLabel("Date du spectacle s\u00E9lectionn\u00E9e:");
		lblDateSpecDynamique.setVisible(false);
		lblDateSpecDynamique.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDateSpecDynamique.setBounds(346, 321, 196, 14);
		contentPane.add(lblDateSpecDynamique);
		
		lblPlanning = new JLabel("");		
		lblPlanning.setVisible(false);
		lblPlanning.setBounds(346, 346, 335, 35);
        contentPane.add(lblPlanning);
		
		JButton btnInfoSpectacle = new JButton("Infos du spectacle s\u00E9lectionn\u00E9");
		btnInfoSpectacle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(lblPlanning);
				if(listeSpectacles.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un spectacle !");
				}
				else {	
					contentPane.add(lblPlanning);
			        Spectacle spec = listeSpectacles.getSelectedValue();
			       
			        lblDateSpecDynamique.setVisible(true);
			        lblPlanning.setVisible(true);
			        lblPlanning.setText(spec.getPlanning().toString());
			        
				}					
			}
		});
		btnInfoSpectacle.setBounds(346, 189, 242, 23);
		contentPane.add(btnInfoSpectacle);		
		
		JButton btnNewButton_3 = new JButton("Editer les informations du compte");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditionClient frame = new EditionClient(cli);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setBounds(612, 54, 233, 23);
		contentPane.add(btnNewButton_3);
		
		
	}

	private void initierFrame(Client donnees) {
        // Informations client autre frame
        cli = donnees;
        JLabel lblNomClient = new JLabel(cli.toString());
        lblNomClient.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomClient.setBounds(393, 11, 149, 32);
        contentPane.add(lblNomClient);
    }
}
