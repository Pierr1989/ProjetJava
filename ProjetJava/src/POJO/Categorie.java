package POJO;

import DAO.BosquetConnection;
import DAO.CategorieDAO;

public class Categorie {
	/*Attributs*/
	private int idCategorie;
	private String type;
	private double prix;
	private int nbrPlaceDispo;
	private int nbrPlaceMax;
	private int idConfiguration;
	
	/*CONSTRUCTEURS*/
	public Categorie() {
		
	}
	
	public Categorie(String type, double prix, int nbrPlaceDispo, int nbrPlaceMax, int idConfiguration) {
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
		this.idConfiguration = idConfiguration; 
	}
	
	public Categorie(int idCategorie, String type, double prix, int nbrPlaceDispo, int nbrPlaceMax, int idConfiguration) {
		this.idCategorie = idCategorie;
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
		this.idConfiguration = idConfiguration;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    
    public int  getIdConfiguration() {
        return idConfiguration;
    }
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }
	
	
	public String  getType() {
        return type;
    }
    public void setAcompte(String type) {
        this.type = type;
    }
    
    
    public double  getPrix() {
        return prix;
    }
    public void setSolde(double prix) {
        this.prix = prix;
    }
    
    public int  getNbrPlaceDispo() {
        return nbrPlaceDispo;
    }
    public void setNbrPlaceDispo(int nbrPlaceDispo) {
        this.nbrPlaceDispo = nbrPlaceDispo;
    }
    
    public int  getNbrPlaceMax() {
        return nbrPlaceMax;
    }
    public void setNbrPlaceMax(int nbrPlaceMax) {
        this.nbrPlaceMax = nbrPlaceMax;
    }
    
	
	/*METHODES*/
    public boolean add(Categorie cat) {
    	CategorieDAO DAO = new CategorieDAO(BosquetConnection.getInstance());
		return DAO.create(cat);	
	}
}