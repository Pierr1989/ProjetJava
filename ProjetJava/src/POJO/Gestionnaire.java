package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.GestionnaireDAO;
import DAO.PlanningSalleDAO;

public class Gestionnaire extends Personne {
	
	private static final long serialVersionUID = 1L;
	PlanningSalle planningS = new PlanningSalle();
	PlanningSalleDAO DAO = new PlanningSalleDAO(BosquetConnection.getInstance());
	private List<PlanningSalle> listePlanningSalle;
	/*CONSTRUCTEURS*/
	public Gestionnaire() {
		
	}
	
	public Gestionnaire(int id, String nom, String prenom, String adresse, String telephone, String pseudo, String password) {
		super(id, nom, prenom, adresse, telephone, pseudo, password);
		listePlanningSalle = new LinkedList<PlanningSalle>();
	}
	
	public Gestionnaire(String nom, String prenom, String adresse, String telephone, String pseudo, String password) {
		super(nom, prenom, adresse,telephone, pseudo, password);
		listePlanningSalle = new LinkedList<PlanningSalle>();
	}
	
	//Accesseurs
		public List<PlanningSalle> getListePlanningSalle() {
	        return listePlanningSalle;
	    }
	    public void setListePlanningSalle(List<PlanningSalle> listePlanningSalle) {
	        this.listePlanningSalle = listePlanningSalle;
	    }

	/*public boolean add(Gestionnaire gest) {		//inutile car créé en dur
		GestionnaireDAO DAO = new GestionnaireDAO(BosquetConnection.getInstance());
		return DAO.create(gest);
	}*/
	    
    public boolean checkTelephone(String telephone) {
		GestionnaireDAO DAO = new GestionnaireDAO(BosquetConnection.getInstance());
		List<Gestionnaire> liste = new LinkedList<Gestionnaire>();
		liste = DAO.getAll();
		for(Gestionnaire gestionnaire : liste) {
			if(gestionnaire.getTelephone().equals(telephone)){
				return true;
			}
		}	
		return false;
	}
	
	public Gestionnaire login(String telephone, String password) {
		GestionnaireDAO DAO = new GestionnaireDAO(BosquetConnection.getInstance());
        List<Gestionnaire> liste = new LinkedList<Gestionnaire>();
        Gestionnaire gest = new Gestionnaire();

        liste = DAO.getAll();
        int found = 0;
        
        for(int i=0; i< liste.size(); i++) {
        	if(liste.get(i).getTelephone().equals(telephone)  && liste.get(i).getPassword().equals(password)) {
                gest = liste.get(i);
                found = 1;
        	}
        }
        
        if(found == 0) {
        	return null;
        }
        
        return gest;
	}



	
	public void annulerDateDispo() {
		// TODO Auto-generated method stub
		
	}

	public Gestionnaire find(int id) {
		GestionnaireDAO DAO = new GestionnaireDAO(BosquetConnection.getInstance());
		return DAO.find(id);	
	}

}
