package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.GestionnaireDAO;

public class Gestionnaire extends Personne {
	
	private static final long serialVersionUID = 1L;
	private String telephone;
	
	/*CONSTRUCTEURS*/
	public Gestionnaire() {
		
	}
	
	public Gestionnaire(int id,String nom, String prenom, String adresse, String password, String email, String role, String telephone) {
		super(id, nom, prenom, adresse, password, email, role);
		this.telephone=telephone;
	}
	
	public Gestionnaire(String nom, String prenom, String adresse, String password, String email, String role, String telephone) {
		super(nom, prenom, adresse, password, email, role);
		this.telephone=telephone;
	}
	
	//Accesseurs
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	//méthodes
	public boolean add() {
		super.add();
		return DAO.create(this);	
	}
	
    public boolean checkEmail(String email) {
		GestionnaireDAO DAO = new GestionnaireDAO(BosquetConnection.getInstance());
		List<Gestionnaire> liste = new LinkedList<Gestionnaire>();
		liste = DAO.getAll();
		for(Gestionnaire gestionnaire : liste) {
			if(gestionnaire.getEmail().equals(email)){
				return true;
			}
		}	
		return false;
	}

	public Gestionnaire login(String email, String password) {
		GestionnaireDAO DAO = new GestionnaireDAO(BosquetConnection.getInstance());
        List<Gestionnaire> liste = new LinkedList<Gestionnaire>();
        Gestionnaire gest = new Gestionnaire();

        liste = DAO.getAll();
        int found = 0;
        
        for(int i=0; i< liste.size(); i++) {
        	if(liste.get(i).getEmail().equals(email)  && liste.get(i).getPassword().equals(password)) {
                gest = liste.get(i);
                found = 1;
        	}
        }
        
        if(found == 0) {
        	return null;
        }
        
        return gest;
	}


	public Gestionnaire find(int id) {
		GestionnaireDAO DAO = new GestionnaireDAO(BosquetConnection.getInstance());
		return DAO.find(id);	
	}

}
