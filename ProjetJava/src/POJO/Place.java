package POJO;

import java.util.List;

import DAO.BosquetConnection;
import DAO.PlaceDAO;

public class Place {
	/*Attributs*/
	private int numPlace;
	private double prix;
	
	/*CONSTRUCTEURS*/
	public Place() {
		
	}
	
	public Place(double prix) {
		this.prix = prix;
	}
	
	public Place(int numPlace, double prix) {
		this.numPlace = numPlace;
		this.prix = prix;
	}
	
	
	/*ACCESSEURS*/
	public int  getNumPlace() {
        return numPlace;
    }
    public void setNumPlace(int numPLace) {
        this.numPlace = numPLace;
    }
    
    public int  getIdCommande() {
        return idCommande;
    }
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }
    
    public Double  getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
	
	
	/*METHODES*/
    public boolean add(Place place) {
    	PlaceDAO DAO = new PlaceDAO(BosquetConnection.getInstance());
		return DAO.create(place);	
	}
    
    public List<Place> getAll(){
		return DAO.getAll();
    }
}