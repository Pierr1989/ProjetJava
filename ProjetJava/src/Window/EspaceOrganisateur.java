package Window;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Artiste;
import POJO.Organisateur;
import POJO.PlanningSalle;

public class EspaceOrganisateur extends JFrame  {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Organisateur orga;
	private PlanningSalle planningS = new PlanningSalle();
	private JButton btnReserverSalle;
	private Artiste artiste = new Artiste();
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultListModel listModel = new DefaultListModel();
	private JList<PlanningSalle> listeDatesDispo = new JList<PlanningSalle>();

	
	ListSelectionModel sm;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	
	public EspaceOrganisateur(Organisateur orga) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		
		initierFrame(orga);		

		JLabel lblNewLabel = new JLabel("Salut : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(214, 11, 195, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("R\u00E9servez des plages horaires :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 80, 360, 30);
		contentPane.add(lblNewLabel_1);

		
		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(393, 464, 89, 23);
		contentPane.add(btnNewButton_2);
		
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
				btnAccueil.setBounds(826, 460, 88, 30);
				contentPane.add(btnAccueil);
		
		JButton btnNewButton_3 = new JButton("Editer les informations du compte");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditionOrganisateur frame = new EditionOrganisateur(orga);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setBounds(681, 50, 233, 23);
		contentPane.add(btnNewButton_3);
						
		List<PlanningSalle> list = new LinkedList<PlanningSalle>();		
        list = planningS.getLibre();
        for (PlanningSalle plan : list) {
            listModel.addElement(plan);
        }
        listeDatesDispo.setVisibleRowCount(3);
        listeDatesDispo.setModel(listModel);
		listeDatesDispo.setBounds(10, 136, 326, 50);
		
        scrollPane = new JScrollPane(listeDatesDispo, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 116, 326, 70);	
		contentPane.add(scrollPane);
		
		JButton btnReserverSalle = new JButton("Valider");
		btnReserverSalle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
					if(listeDatesDispo.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(null, "Veuillez sélectionner une plage horaire !");
					}
					else {
			            PlanningSalle dataToOtherFRame = listeDatesDispo.getSelectedValue();
		            	CreationSpectacle frame = new CreationSpectacle(dataToOtherFRame, orga);
					    frame.setLocationRelativeTo(null);
					    frame.setVisible(true);  
					    dispose();
					}              
			}
		});
		btnReserverSalle.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReserverSalle.setBounds(76, 197, 149, 23);
		contentPane.add(btnReserverSalle);
		
	}			
	
	private void initierFrame(Organisateur donnees) {
        // Informations client autre frame
        orga = donnees;
        JLabel lblNomOrganisateur = new JLabel(orga.toString());
        lblNomOrganisateur.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomOrganisateur.setBounds(393, 2, 241, 32);
        contentPane.add(lblNomOrganisateur);
        
    }
}
