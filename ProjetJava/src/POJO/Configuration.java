package POJO;


import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.CategorieDAO;
import DAO.ConfigurationDAO;

public class Configuration {
	/*Attributs*/
	private int idConfiguration;
	private String type;
	private String description;
	List<Categorie> listeCategorie;
	
	ConfigurationDAO DAO = new ConfigurationDAO(BosquetConnection.getInstance());
	CategorieDAO DAOcategorie = new CategorieDAO(BosquetConnection.getInstance());
	
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
    
    
    public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}

	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
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
    public boolean add() {
		return DAO.create(this);	
	}
    
    public boolean delete() {
    	return DAO.delete(this);
    }
    
    public List<Configuration> getAll(){
		return DAO.getAll();
    }
    
    @Override
    public String toString() { 
        return String.format(type + "  " + description); 
    }
    
    public List<Categorie> getCategorieDeLaConfiguration(int idConf) {
    	listeCategorie = new LinkedList<Categorie>();
    	listeCategorie = DAOcategorie.getAll();
    	List<Categorie> ListCategorieTrouvee = new LinkedList<Categorie>();
		for(int i = 0; i<listeCategorie.size(); i++) {
			if(listeCategorie.get(i).getConfiguration().getIdConfiguration() == idConf) {
				ListCategorieTrouvee.add(listeCategorie.get(i));
			}
		}
		return ListCategorieTrouvee;
	}
    
    public Categorie getlimiteDebout(int idConf) {
    	Categorie cat = new Categorie();
    	listeCategorie = DAOcategorie.getAll();
		for(int i = 0; i<listeCategorie.size(); i++) {
			if(listeCategorie.get(i).getConfiguration().getIdConfiguration() == idConf) {
				cat = listeCategorie.get(i);
			}
		}
		return cat;
	}
}