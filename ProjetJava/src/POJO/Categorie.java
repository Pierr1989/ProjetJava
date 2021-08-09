package POJO;

import java.util.List;

import DAO.BosquetConnection;
import DAO.CategorieDAO;

public class Categorie {
	/*Attributs*/
	private int idCategorie;
	private String type;
	private double prix;
	private int nbrPlaceDispo;
	private int nbrPlaceMax;
	private Configuration configuration;
	CategorieDAO DAO = new CategorieDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public Categorie() {
		
	}
	
	public Categorie(String type, double prix, int nbrPlaceDispo, int nbrPlaceMax, Configuration configuration) {
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
		this.configuration = configuration; 
	}
	
	public Categorie(int idCategorie, String type, double prix, int nbrPlaceDispo, int nbrPlaceMax, Configuration configuration) {
		this.idCategorie = idCategorie;
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
		this.configuration = configuration;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public String  getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
      
    public double  getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
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
    public boolean add() {
    	return DAO.create(this);	
	}
    
    public boolean update(Categorie cat) {
    	return DAO.update(cat);	
	}
    
    public List<Categorie> getAll() {
    	return DAO.getAll();
    }
    
    @Override
    public String toString() { 
        return String.format(type); 
    }
    
    public int diminuerNbreDePlaceClient(int nbrPlace) {
    	int placeMaxRestanteClient = nbrPlaceMax - nbrPlace;
    	
    	return placeMaxRestanteClient;
    }
    
    public int diminuerNbreDePlaceTotal(int nbrPlace) {
    	int placeMaxTotal = nbrPlaceDispo - nbrPlace;
    	
    	return placeMaxTotal;
    }
}