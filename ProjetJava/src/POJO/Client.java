package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.ClientDAO;

public class Client extends Personne {
	
	private static final long serialVersionUID = 1L;
	private List<Commande> listeCommande;
	ClientDAO DAO = new ClientDAO(BosquetConnection.getInstance());
			
	/*CONSTRUCTEURS*/
	public Client() {
		
	}
	
	public Client(int id, String nom, String prenom, String adresse, String telephone, String pseudo, String password) {
		super(id, nom, prenom, adresse, telephone, pseudo, password);
		listeCommande = new LinkedList<Commande>();
	}
	
	public Client(String nom, String prenom, String adresse, String telephone, String pseudo, String password) {
		super(nom, prenom, adresse, telephone, pseudo, password);
		listeCommande = new LinkedList<Commande>();
	}

	public boolean add(Client cli) {
		return DAO.create(cli);	
	}
	
	public boolean checkTelephone(String telephone) {
		List<Client> liste = new LinkedList<Client>();
		liste = DAO.getAll();
		for(Client client : liste) {
			if(client.getTelephone().equals(telephone)){
				return true;
			}
		}	
		return false;
	}
	
	public Client login(String telephone, String password) {
	        List<Client> liste = new LinkedList<Client>();
	        Client cli = new Client();

	        liste = DAO.getAll();
	        int found = 0;
	        
	        for(int i=0; i< liste.size(); i++) {
	        	if(liste.get(i).getTelephone().equals(telephone)  && liste.get(i).getPassword().equals(password)) {
	                cli = liste.get(i);
	                found = 1;
	        	}
	        }
	        
	        if(found == 0) {
	        	return null;
	        }
	        
	        return cli;

	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public Client find(int id) {
		return DAO.find(id);	
	}
	
	public List<Client> getAll() {
        return DAO.getAll();
    }

	
	//Accesseurs
	public List<Commande> getCommande() {
        return listeCommande;
    }
    public void setCommande(List<Commande> listeCommande) {
        this.listeCommande = listeCommande;
    }
	
}
