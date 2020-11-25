package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.OrganisateurDAO;

public class Organisateur extends Personne {

	private static final long serialVersionUID = 1L;
	private List<Reservation> listeReservation;
	/*CONSTRUCTEURS*/
	public Organisateur() {
		
	}
	
	public Organisateur(int id, String nom, String prenom, String adresse, String telephone, String pseudo, String password) {
		super(id, nom, prenom, adresse, telephone, pseudo, password);
		listeReservation = new LinkedList<Reservation>();
	}
	
	public Organisateur(String nom, String prenom, String adresse, String telephone, String pseudo, String password) {
		super(nom, prenom, adresse, telephone, pseudo, password);
		listeReservation = new LinkedList<Reservation>();
	}
	
	//Accesseurs
	public List<Reservation> getListeReservation() {
        return listeReservation;
    }
    public void setListeReservation(List<Reservation> listeReservation) {
        this.listeReservation = listeReservation;
    }

	public boolean add(Organisateur org) {
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
		return DAO.create(org);
	}
	
	public boolean checkTelephone(String telephone) {
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
		List<Organisateur> liste = new LinkedList<Organisateur>();
		liste = DAO.getAll();
		for(Organisateur organisateur : liste) {
			if(organisateur.getTelephone().equals(telephone)){
				return true;
			}
		}	
		return false;
	}
	
	public Organisateur login(String telephone, String password) {
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
        List<Organisateur> liste = new LinkedList<Organisateur>();
        Organisateur orga = new Organisateur();

        liste = DAO.getAll();
        int found = 0;
        
        for(int i=0; i< liste.size(); i++) {
        	if(liste.get(i).getTelephone().equals(telephone)  && liste.get(i).getPassword().equals(password)) {
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

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public Organisateur find(int id) {
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
		return DAO.find(id);	
	}

}
