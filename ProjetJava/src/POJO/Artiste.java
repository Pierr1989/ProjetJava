package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.ArtisteDAO;
import DAO.BosquetConnection;


public class Artiste extends Personne {

	private static final long serialVersionUID = 1L;
	ArtisteDAO DAO = new ArtisteDAO(BosquetConnection.getInstance());
	private String nomDeScene;
	private Spectacle spectacle;
	
	/*CONSTRUCTEURS*/
	public Artiste() {
		
	}
	
	public Artiste(int id,String nom, String prenom, String adresse, String password, String email, String role, String nomDeScene, Spectacle spectacle) {
		super(id, nom, prenom, adresse, password, email, role);
		this.nomDeScene=nomDeScene;
		this.spectacle=spectacle;
	}
	
	public Artiste(String nom, String prenom, String adresse, String password, String email, String role, String nomDeScene, Spectacle spectacle) {
		super(nom, prenom, adresse, password, email, role);
		this.nomDeScene=nomDeScene;
		this.spectacle=spectacle;
	}
	
	//get set	
	public String getNomDeScene() {
		return nomDeScene;
	}

	public void setNomDeScene(String nomDeScene) {
		this.nomDeScene = nomDeScene;
	}
	public Spectacle getSpectacle() {
		return spectacle;
	}

	public void setSpectacle(Spectacle spectacle) {
		this.spectacle = spectacle;
	}

	//Méthodes


	public boolean add(Artiste art) {
		super.add();
		return DAO.create(art);
	}
	
	@Override
    public String toString() { 
        return String.format(nomDeScene); 
    }
	
	public boolean checkNomDeScene(String nomDeScene) {
        List<Artiste> liste = new LinkedList<Artiste>();
        int find = 0;

        liste = DAO.getAll();

        for(int i = 0; i < liste.size(); i++) {
            if(liste.get(i).nomDeScene.equals(nomDeScene)) {
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
