package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.ClientDAO;
import DAO.OrganisateurDAO;

public class Organisateur extends Personne {

	private static final long serialVersionUID = 1L;
	private List<Reservation> listeReservation;
	private String telephone;
	private OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public Organisateur() {
		
	}
	
	public Organisateur(int id,String nom, String prenom, String adresse, String password, String email, String role, String telephone) {
		super(id, nom, prenom, adresse, password, email, role);
		this.telephone=telephone;
		listeReservation = new LinkedList<Reservation>();
	}
	
	public Organisateur(String nom, String prenom, String adresse, String password, String email, String role, String telephone) {
		super(nom, prenom, adresse, password, email, role);
		this.telephone=telephone;
		listeReservation = new LinkedList<Reservation>();
	}
	
	//Accesseurs
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Reservation> getListeReservation() {
        return listeReservation;
    }
    public void setListeReservation(List<Reservation> listeReservation) {
        this.listeReservation = listeReservation;
    }

	public boolean add() {
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
		super.add();
		return DAO.create(this);
	}
	
	public boolean checkEmail(String email) {
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
		List<Organisateur> liste = new LinkedList<Organisateur>();
		liste = DAO.getAll();
		for(Organisateur organisateur : liste) {
			if(organisateur.getEmail().equals(email)){
				return true;
			}
		}	
		return false;
	}
	
	public Organisateur login(String email, String password) {
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
        List<Organisateur> liste = new LinkedList<Organisateur>();
        Organisateur orga = new Organisateur();

        liste = DAO.getAll();
        int found = 0;
        
        for(int i=0; i< liste.size(); i++) {
        	if(liste.get(i).getEmail().equals(email)  && liste.get(i).getPassword().equals(password)) {
                orga = liste.get(i);
                found = 1;
        	}
        }
        
        if(found == 0) {
        	return null;
        }
        
        return orga;
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public boolean update() {
		return DAO.update(this);
		
	}

	public Organisateur find(int id) {
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
		return DAO.find(id);	
	}
}
