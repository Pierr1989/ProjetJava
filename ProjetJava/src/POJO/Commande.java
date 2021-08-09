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
	private double total;
	private List<Place> listePlace;
	CommandeDAO DAO = new CommandeDAO(BosquetConnection.getInstance());
	Client cli = new Client();
	
	/*CONSTRUCTEURS*/
	public Commande() {
		
	}
	
	public Commande(String modeDePayement, String modeDeLivraison, double total, Client cli) {
		this.modeDePayement = modeDePayement;
		this.modeDeLivraison = modeDeLivraison;
		this.total = total;
		this.cli = cli;
		listePlace = new LinkedList<Place>();
	}
	
	public Commande(int idCommande, String modeDePayement, String modeDeLivraison, double total, Client cli) {
		this.idCommande = idCommande;
		this.modeDePayement = modeDePayement;
		this.modeDeLivraison = modeDeLivraison;
		this.total = total;
		this.cli = cli;
		listePlace = new LinkedList<Place>();
	}
	
	
	/*ACCESSEURS*/
	public int  getIdCommande() {
        return idCommande;
    }
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
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

    public Client getCli() {
		return cli;
	}

	public void setCli(Client cli) {
		this.cli = cli;
	}

	public void setModeDeLivraison(String modeDeLivraison) {
        this.modeDeLivraison = modeDeLivraison;
    }
    
    public Double  getTotal() {
        return total;
    }
    public void setTotal(double cout) {
        this.total = cout;
    }

  	public List<Place> getPlace() {
          return listePlace;
      }
      public void setPlacee(List<Place> listePlace) {
          this.listePlace = listePlace;
      }
	
	/*METHODES*/
    public boolean add(Commande com) {		
		return DAO.create(com);	
	}
    
    @Override
    public String toString() { 
        return String.format("id: " + idCommande + " - Mode de livraison: " + modeDeLivraison + " - Cout: " + total); 
    }
    
    public boolean Update(Commande commande) {	
		return DAO.update(commande);
	}
    
    public double calculTotalCommandeDebout(double prixCat, int nbrPlace, String moyenPayement) {
    	Double prix = 0.0;
    	//5€ de frais de dossier et 10€ si payement sécurisé (sepa)
    	if(moyenPayement.equals("Sepa"))
    		return prix = (prixCat* nbrPlace) + 15 ;
    	return prix = (prixCat* nbrPlace) + 5 ;
    }
    
    public double calculTotalCommandeConCir(double prixCat, String moyenPayement) {
    	Double prix = 0.0;
    	//5€ de frais de dossier et 10€ si payement sécurisé (sepa)
    	if(moyenPayement.equals("Sepa"))
    		return prix = prixCat + 15 ;
    	return prix = prixCat + 5 ;
    }
}