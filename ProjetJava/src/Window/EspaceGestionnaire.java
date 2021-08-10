package Window;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Gestionnaire;
import POJO.PlanningSalle;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import java.util.Calendar;
import java.util.Date;

public class EspaceGestionnaire extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gestionnaire gest;
	private Date  day; 
	private Date dayPlusUn;
	private Date dateToday = new Date();
	private PlanningSalle planningS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public EspaceGestionnaire(Gestionnaire gest) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//here
		initierFrame(gest);
		
		JLabel lblNewLabel = new JLabel("Salut");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(277, 11, 106, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Que souhaitez-vous faire?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(40, 105, 262, 30);
		contentPane.add(lblNewLabel_1);
		
		JButton btnVoirSpectacle = new JButton("Voir les spectacles");
		btnVoirSpectacle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListeDesSpectacles frame = new ListeDesSpectacles();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnVoirSpectacle.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVoirSpectacle.setBounds(40, 281, 199, 23);
		contentPane.add(btnVoirSpectacle);
		
		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(393, 464, 89, 23);
		contentPane.add(btnNewButton_2);
		
		
		JLabel lblNewLabel_2 = new JLabel("Ajouter date disponible :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(40, 160, 173, 23);
		contentPane.add(lblNewLabel_2);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(40, 194, 174, 20);
		contentPane.add(dateChooser);
		
		JButton btnValider = new JButton("valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cast obligatoire sinon problème de compatibilité entre les "date" des import;
				dateToday = aujourdhui();
				Date dateChooserFormated = dateChooser.getDate();
				
				if(dateChooser.getDate() == null)
                    JOptionPane.showMessageDialog(null, "Date vide !");
				else {
					java.util.Date day = new java.util.Date();
					day = dateChooser.getDate();
                    Date dayReserved = day;
                    Date dayAfter = day;
                    Calendar c = Calendar.getInstance();
                    Calendar t = Calendar.getInstance();
                    t.setTime(dayReserved);
                    c.setTime(dayAfter);
                    c.add(Calendar.DATE, 1);

                    t.setTime(day);
                    t.set( Calendar.HOUR_OF_DAY, 12 ); // Plage horaire de 12h jour j à 12h jour j+1
                    t.set( Calendar.MINUTE, 0 );
                    t.set( Calendar.SECOND, 0 );
                    t.set( Calendar.MILLISECOND, 0 );

                    c.set( Calendar.HOUR_OF_DAY, 12 );
                    c.set( Calendar.MINUTE, 0 );
                    c.set( Calendar.SECOND, 0 );
                    c.set( Calendar.MILLISECOND, 0 );

                    dayReserved = t.getTime();
                    dayAfter = c.getTime();   
                    
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    String strDayReserved = dateFormat.format(dayReserved);
                    String strDayAfter = dateFormat.format(dayAfter);

					planningS = new PlanningSalle(strDayReserved, strDayAfter, gest, null, false);
					
					if(planningS.checkDate(planningS)) {
						JOptionPane.showMessageDialog(null, "Date déjà réservée !");
					}
					else if(dateChooserFormated.before(dateToday)) {
						JOptionPane.showMessageDialog(null, "Date antérieur !");
					}
					else {
						if(planningS.AddPlanningSalle(planningS)) {
							JOptionPane.showMessageDialog(null, "Plannification réussie !");
						}
						else
							JOptionPane.showMessageDialog(null, "Plannification a échoué  !");
					}
				}					
				
			}
		});
		btnValider.setBounds(235, 194, 89, 23);
		contentPane.add(btnValider);
		
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
	}

	private void initierFrame(Gestionnaire donnees) {
        // Informations client autre frame
        gest = donnees;
        JLabel lblNomGestionnaire = new JLabel(gest.toString());
        lblNomGestionnaire.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomGestionnaire.setBounds(393, 11, 149, 32);
        contentPane.add(lblNomGestionnaire);
    }
	
	private Date aujourdhui() {
	       Long millis = System.currentTimeMillis();
	       Date date = new Date(millis);
	       return date;
	}
}
