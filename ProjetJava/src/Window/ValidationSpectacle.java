package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import POJO.Artiste;
import POJO.PlanningSalle;
import POJO.Representation;
import POJO.Spectacle;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JCheckBox;

public class ValidationSpectacle extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private Artiste artiste = new Artiste();
	private DefaultListModel listModel = new DefaultListModel();
	private DefaultListModel listModelSpec = new DefaultListModel();
	private JScrollPane scrollPane = new JScrollPane();
	private JScrollPane scrollPaneSpec = new JScrollPane();
	private boolean artisteExistant = false;
	private Representation representation;
	private JCheckBox chckbx1;
	private JCheckBox chckbx2;
	private JCheckBox chckbx3;
	private JCheckBox chckbx4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public ValidationSpectacle(Spectacle spectacle, PlanningSalle plan) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Artiste et repr\u00E9sentation(s)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(328, 11, 432, 39);
		contentPane.add(lblNewLabel);
		
		
		textName = new JTextField();
		textName.setBounds(21, 228, 175, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Inscrivez l'artiste :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(27, 193, 169, 30);
		contentPane.add(lblNewLabel_5);
	    
	    List<Spectacle> listS = new LinkedList<Spectacle>();		
        listS = spectacle.getAll();
        for (Spectacle spect: listS) {
            listModelSpec.addElement(spect);
        }
	    
	    
	    List<Artiste> list = new LinkedList<Artiste>();		
        list = artiste.getAll();
        for (Artiste artiste : list) {
            listModel.addElement(artiste);
        }
		
		JButton btnEnregistrerArtiste = new JButton("Valider artiste");
		btnEnregistrerArtiste.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEnregistrerArtiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(textName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");
				}
				else {
					artisteExistant = true;
					artiste = new Artiste(textName.getText(), spectacle.getIdSpectacle());
					if(artiste.add(artiste)) {
						JOptionPane.showMessageDialog(null, "Enregistrement r�ussi !");	
						listModel.removeAllElements();
			    		List<Artiste> list = new LinkedList<Artiste>();		
			            list = artiste.getAll();
			    		listModel.addAll(list);
			    		textName.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Enregistrement �chou� !");
					}
				}				
			}
		});
		btnEnregistrerArtiste.setBounds(31, 259, 151, 23);
		contentPane.add(btnEnregistrerArtiste);
		
		JLabel lblNewLabel_1 = new JLabel("R\u00E9capitulatif du spectacle :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 80, 264, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblRecap = new JLabel(spectacle.toString());
		lblRecap.setBounds(10, 123, 247, 48);
		contentPane.add(lblRecap);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spectacle.delete(spectacle);
			}
		});
		btnAnnuler.setBounds(357, 448, 89, 23);
		contentPane.add(btnAnnuler);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				System.exit(0);
			}
		});
		btnQuitter.setBounds(494, 448, 89, 23);
		contentPane.add(btnQuitter);
		
		JButton btnAcceuil = new JButton("Annuler et retourner \u00E0 l'acceuil");
		btnAcceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spectacle.delete(spectacle);
				Accueil frame = new Accueil();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnAcceuil.setBounds(357, 401, 226, 23);
		contentPane.add(btnAcceuil);
		
		JLabel lblNewLabel_2 = new JLabel("S\u00E9lectionnez les repr\u00E9sentations :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(434, 193, 352, 30);
		contentPane.add(lblNewLabel_2);
		
		chckbx1 = new JCheckBox("16h-18h");
		chckbx1.setSelected(true);
		chckbx1.setBounds(554, 228, 97, 23);
		contentPane.add(chckbx1);
		
		chckbx2 = new JCheckBox("20h-22h");
		chckbx2.setBounds(653, 228, 97, 23);
		contentPane.add(chckbx2);
		
		JLabel lblJour1 = new JLabel(plan.getDateDebutR().toString());
		lblJour1.setBounds(434, 231, 97, 17);
		contentPane.add(lblJour1);
		
		JLabel lblJour2 = new JLabel(plan.getDateFinR().toString());
		lblJour2.setBounds(434, 263, 97, 19);
		contentPane.add(lblJour2);
		
		chckbx3 = new JCheckBox("08h-10h");
		chckbx3.setBounds(554, 259, 97, 23);
		contentPane.add(chckbx3);
		
		chckbx4 = new JCheckBox("10h-12h");
		chckbx4.setBounds(653, 259, 97, 23);
		contentPane.add(chckbx4);
		
		JButton btnValiderRep = new JButton("Valider");
		btnValiderRep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(artisteExistant) {
					if(chckbx1.isSelected()) {						
						definitionHeure(16, 18, plan, spectacle, plan.getDateDebutR(), 1, 1);
					}
					if(chckbx2.isSelected()) {
						definitionHeure(20, 22, plan, spectacle, plan.getDateDebutR(), 1, 2);
					}
					if(chckbx3.isSelected()) {
						definitionHeure(8, 10, plan, spectacle, plan.getDateFinR(), 2, 3);
					}
					if(chckbx4.isSelected()) {
						definitionHeure(10, 12, plan, spectacle, plan.getDateFinR(), 2, 4);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Un artiste doit �tre ajout� !");
				}
			}
		});
		btnValiderRep.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnValiderRep.setBounds(754, 243, 89, 23);
		contentPane.add(btnValiderRep);
	}
	
	private void definitionHeure(int heureDebut, int heureFin, PlanningSalle plan, Spectacle spectacle, Date date, int statut, int numeroRepresentation) {
		java.util.Date heureDeDebut = new java.util.Date();
	    java.util.Date heureDeFin = new java.util.Date();	    
	    
	    if(statut == 1) {
	    	heureDeDebut = plan.getDateDebutR();
		    heureDeFin = plan.getDateDebutR();
	    }
	    if(statut == 2) {
	    	heureDeDebut = plan.getDateFinR();
		    heureDeFin = plan.getDateFinR();
	    }
	    

		Calendar c = Calendar.getInstance();
		Calendar t = Calendar.getInstance();
		t.setTime(heureDeDebut);
		c.setTime(heureDeFin);
		
		t.setTime(heureDeDebut);
        t.set( Calendar.HOUR_OF_DAY, heureDebut );
		
		c.setTime(heureDeFin);
        c.set( Calendar.HOUR_OF_DAY, heureFin );
        				              
        heureDeDebut = c.getTime();
		heureDeFin = t.getTime();
		
		java.sql.Date date_sql_debut = new java.sql.Date(heureDeFin.getTime());
		java.sql.Date date_sql_fin = new java.sql.Date(heureDeDebut.getTime());	
		java.sql.Date dateReserve = new java.sql.Date(date.getTime());	
		
		representation = new Representation(dateReserve, date_sql_debut, date_sql_fin, spectacle.getIdSpectacle(), 0, numeroRepresentation);
	    representation.add(representation);
	}
}
