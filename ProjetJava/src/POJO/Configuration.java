package POJO;


import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.ConfigurationDAO;

public class Configuration {
	/*Attributs*/
	private int idConfiguration;
	private String type;
	private String description;
	List<Categorie> listeCategorie;
	
	/*CONSTRUCTEURS*/
	public Configuration() {
		
	}
	
	public Configuration(String type, String description) {
		this.type = type;
		this.description = description;
		listeCategorie = new LinkedList<Categorie>();
	}
	
	public Configuration(int idConfiguration, String type, String description) {
		this.idConfiguration = idConfiguration;
		this.type = type;
		this.description = description;
		listeCategorie = new LinkedList<Categorie>();
	}
	
	
	/*ACCESSEURS*/
	public int  getIdConfiguration() {
        return idConfiguration;
    }
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }
    
    public List<Categorie>  getListCategorie() {
        return listeCategorie;
    }
    public void setListeArtiste(List<Categorie> listeCategorie) {
        this.listeCategorie = listeCategorie;
    }
		
    public String  getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String  getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
	
	
	/*METHODES*/
    public boolean add(Configuration conf) {
    	ConfigurationDAO DAO = new ConfigurationDAO(BosquetConnection.getInstance());
		return DAO.create(conf);	
	}
    
}