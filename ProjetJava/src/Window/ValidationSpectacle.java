package Window;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import POJO.Artiste;
import POJO.Organisateur;
import POJO.PlanningSalle;
import POJO.Representation;
import POJO.Spectacle;

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
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public ValidationSpectacle(Spectacle spectacle, PlanningSalle plan, Organisateur orga) {
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
					artiste = new Artiste("","","","","","Artiste",textName.getText(), spectacle);
					if(artiste.add(artiste)) {
						JOptionPane.showMessageDialog(null, "Enregistrement réussi !");	
						listModel.removeAllElements();
			    		List<Artiste> list = new LinkedList<Artiste>();		
			            list = artiste.getAll();
			    		listModel.addAll(list);
			    		textName.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Enregistrement échoué !");
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
				if(spectacle.getPlanning().getReservation().delete()) {
					if(spectacle.getConf().delete()) {
						if(spectacle.delete()) {
							Accueil frame = new Accueil();
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);  
						    dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Supression spectacle impossible !");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Supression réservation impossible !");
				
			}
		});
		btnAnnuler.setBounds(357, 448, 89, 23);
		contentPane.add(btnAnnuler);
		
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
		
		JButton btnValiderRep = new JButton("Valider et terminer");
		btnValiderRep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(artisteExistant) {
					if(chckbx1.isSelected()) {						
						definitionHeure(16, 18, plan, spectacle, plan.getDateDebutR());
						retourAccueil(orga);
					}
					if(chckbx2.isSelected()) {
						definitionHeure(20, 22, plan, spectacle, plan.getDateDebutR());
						retourAccueil(orga);
					}
					if(chckbx3.isSelected()) {
						definitionHeure(8, 10, plan, spectacle, plan.getDateFinR());
						retourAccueil(orga);
					}
					if(chckbx4.isSelected()) {
						definitionHeure(10, 12, plan, spectacle, plan.getDateFinR());
						retourAccueil(orga);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Un artiste doit être ajouté !");
				}
				
			}
		});
		btnValiderRep.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnValiderRep.setBounds(754, 243, 165, 23);
		contentPane.add(btnValiderRep);
	}
	
	private void definitionHeure(int heureDebut, int heureFin, PlanningSalle plan, Spectacle spectacle, String date) {
		Calendar calDebut = Calendar.getInstance();
        Calendar calFin = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        
        calDebut.set( Calendar.HOUR_OF_DAY, heureDebut );
        calDebut.set( Calendar.MINUTE, 0 );
        calDebut.set( Calendar.SECOND, 0 );
        calDebut.set( Calendar.MILLISECOND, 0 );

        calFin.set( Calendar.HOUR_OF_DAY, heureFin );
        calFin.set( Calendar.MINUTE, 0 );
        calFin.set( Calendar.SECOND, 0 );
        calFin.set( Calendar.MILLISECOND, 0 );
        
        String hDebut;
        String hFin;
	
		 Date date1;
		 date1 = calDebut.getTime();
		 hDebut = dateFormat.format(date1);
   
    	 Date date2;
    	 date2 = calFin.getTime();
    	 hFin = dateFormat.format(date2);
	    
		representation = new Representation(date, hDebut, hFin, spectacle, null);
	    representation.add(representation);
	}
	
	private void retourAccueil(Organisateur orga) {
		EspaceOrganisateur frame = new EspaceOrganisateur(orga);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);  
	    dispose();
	}
}
