package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Client;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class EspaceClient extends JFrame {

	private JPanel contentPane;
	private Client cli;

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
		btnNewButton_1.setBounds(347, 148, 199, 23);
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
