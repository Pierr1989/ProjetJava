package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.CommandeDAO;

public class Commande {
	/*Attributs*/
	private int idCommande;
	private String modeDePayement;
	private String modeDeLivraison;
	private double cout;
	private List<Place> listePlace;
	private int idClient;
	
	/*CONSTRUCTEURS*/
	public Commande() {
		
	}
	
	public Commande(String modeDePayement, String modeDeLivraison, double cout, int idClient) {
		this.modeDePayement = modeDePayement;
		this.modeDeLivraison = modeDeLivraison;
		this.cout = cout;
		listePlace = new LinkedList<Place>();
		this.idClient = idClient;
	}
	
	public Commande(int idCommande, String modeDePayement, String modeDeLivraison, double cout, int idClient) {
		this.idCommande = idCommande;
		this.modeDePayement = modeDePayement;
		this.modeDeLivraison = modeDeLivraison;
		this.cout = cout;
		listePlace = new LinkedList<Place>();
		this.idClient = idClient;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdCommande() {
        return idCommande;
    }
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }
    
    public int  getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
		
    public String  getModeDePayement() {
        return modeDePayement;
    }

    public void setModeDePayement(String modeDePayement) {
        this.modeDePayement = modeDePayement;
    }
    
    public String  getModeDeLivraison() {
        return modeDeLivraison;
    }

    public void setModeDeLivraison(String modeDeLivraison) {
        this.modeDeLivraison = modeDeLivraison;
    }
    
    public Double  getCout() {
        return cout;
    }
    public void setPrix(double cout) {
        this.cout = cout;
    }

  	public List<Place> getPlace() {
          return listePlace;
      }
      public void setPlacee(List<Place> listePlace) {
          this.listePlace = listePlace;
      }
	
	/*METHODES*/
    public boolean add(Commande com) {
		CommandeDAO DAO = new CommandeDAO(BosquetConnection.getInstance());
		return DAO.create(com);	
	}
}