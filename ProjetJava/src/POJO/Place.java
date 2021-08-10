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