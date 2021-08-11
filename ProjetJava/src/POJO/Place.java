package POJO;

import java.util.List;

import DAO.BosquetConnection;
import DAO.PlaceDAO;

public class Place {
	/*Attributs*/
	private int idPlace;
	private double prix;
	Commande com = new Commande();
	PlaceDAO DAO = new PlaceDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public Place() {
		
	}
	
	public Place(double prix, Commande com) {
		this.prix = prix;
		this.com = com;
	}
	
	public Place(int idPlace, double prix, Commande com) {
		this.idPlace = idPlace;
		this.prix = prix;
		this.com = com;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdPlace() {
        return idPlace;
    }
    public void setIdPlace(int numPLace) {
        this.idPlace = numPLace;
    }
    
    public Commande getCommande() {
		return com;
	}

	public void setCommande(Commande com) {
		this.com = com;
	}

	public Double  getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

	/*METHODES*/
    public boolean add() {  	
		return DAO.create(this);	
	}
    
    public boolean delete() {  		
		return DAO.delete(this);
	}
    
    public List<Place> getAll(){
		return DAO.getAll();
    }
    
    public void CalculerPrixPlace(Configuration conf, Categorie cat) {
        if(conf.getType().equals("Debout")) {
            this.prix = cat.getPrix() * 1.05;
        }
        else if(conf.getType().equals("Concert")) {
            switch(cat.getType()) {
                case "Bronze":       
                    this.prix = cat.getPrix() * 1.10;
                    break;
                case "Argent":      
                    this.prix = cat.getPrix() * 1.20;
                    break;
                case "Or":      
                    this.prix  = cat.getPrix() * 1.30;
                    break;
            }
        }
        else {
            switch(cat.getType()) {
                case "Bronze":   
                    this.prix = cat.getPrix() * 1.15;
                    break;
                case "Argent":   
                    this.prix = cat.getPrix() * 1.25;
                    break;
                case "Or":      
                    this.prix = cat.getPrix() * 1.35;
                    break;
                case "Diamant":      
                    this.prix = cat.getPrix() * 1.50;
                    break;
            }
        }
    }
}