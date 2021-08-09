package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.ClientDAO;

public class Client extends Personne {
	
	private static final long serialVersionUID = 1L;
	private String telephone;
	private String genre;
	private String dateDeNaissance;
	private List<Commande> listeCommande;
	private ClientDAO DAO = new ClientDAO(BosquetConnection.getInstance());
			
	/*CONSTRUCTEURS*/
	public Client() {
		
	}
	
	public Client(int id,String nom, String prenom, String adresse, String password, String email, String role, String telephone, String genre, String dateDeNaissance) {
		super(id, nom, prenom, adresse, password, email, role);
		listeCommande = new LinkedList<Commande>();
		this.telephone=telephone;
		this.genre=genre;
		this.dateDeNaissance=dateDeNaissance;
	}
	
	public Client(String nom, String prenom, String adresse, String password, String email, String role, String telephone, String genre, String dateDeNaissance) {
		super(nom, prenom, adresse, password, email, role);
		listeCommande = new LinkedList<Commande>();
		this.telephone=telephone;
		this.genre=genre;
		this.dateDeNaissance=dateDeNaissance;
	}
	
	
	//get set
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public List<Commande> getListeCommande() {
		return listeCommande;
	}

	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}

	
	//methods
	public boolean add() {
		super.add();
		return DAO.create(this);	
	}
	
	public boolean checkEmail(String email) {
		List<Client> liste = new LinkedList<Client>();
		liste = DAO.getAll();
		for(Client client : liste) {
			if(client.getEmail().equals(email)){
				return true;
			}
		}	
		return false;
	}
	
	public Client login(String email, String password) {
	        List<Client> liste = new LinkedList<Client>();
	        Client cli = new Client();

	        liste = DAO.getAll();
	        int found = 0;
	        
	        for(int i=0; i< liste.size(); i++) {
	        	if(liste.get(i).getEmail().equals(email)  && liste.get(i).getPassword().equals(password)) {
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

	public boolean update() {
		return DAO.update(this);
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
