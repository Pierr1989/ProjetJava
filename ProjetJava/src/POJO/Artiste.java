package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.ArtisteDAO;
import DAO.BosquetConnection;
import DAO.ClientDAO;
import DAO.DAO;

public class Artiste extends Personne {

	private static final long serialVersionUID = 1L;
	ArtisteDAO DAO = new ArtisteDAO(BosquetConnection.getInstance());
	private int idSpectacle;
	private int id;
	
	/*CONSTRUCTEURS*/
	public Artiste() {
		
	}
	
	public Artiste(String nom) {
		super(nom);
	}
	
	public Artiste(int id, String nom, String prenom, String adresse, String telephone, String pseudo, String password, int idSpectacle) {
		super(id, nom, prenom, adresse, telephone, pseudo, password);
		this.idSpectacle = idSpectacle;
	}
	
	public Artiste(String nom, String prenom, String adresse, String telephone, String pseudo, String password, int idSpectacle) {
		super(nom, prenom, adresse, telephone, pseudo, password);
		this.idSpectacle = idSpectacle;
	}
	
	//get set
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getIdSpectacle() {
		return idSpectacle;
	}
	public void setidSpectacle(int idSpectacle) {
		this.idSpectacle = idSpectacle;
	}
	
	
	//Méthodes

	public boolean add(Artiste art) {
		return DAO.create(art);
	}
	
	public boolean checkNom(String nom) {
        List<Artiste> liste = new LinkedList<Artiste>();
        int find = 0;

        liste = DAO.getAll();

        for(int i = 0; i < liste.size(); i++) {
            if(liste.get(i).nom.equals(nom)) {
                find = 1;
            }
        }

        if(find == 1) {
            return true;
        }

        return false;
    }

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public boolean update(Artiste arti) {
		return DAO.update(arti);
		
	}

	public void find() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Artiste> getAll() {
        return DAO.getAll();
    }
}
