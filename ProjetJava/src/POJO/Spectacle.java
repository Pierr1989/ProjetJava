package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.ArtisteDAO;
import DAO.BosquetConnection;
import DAO.PlanningSalleDAO;
import DAO.RepresentationDAO;
import DAO.SpectacleDAO;

public class Spectacle {
	/*Attributs*/
	private int idSpectacle;
	private PlanningSalle planning;	//FK idPlanningSalle
	private Configuration conf;
	private String titre;
	private String description;
	private int nbrPlaceParClient;
	List<Artiste> listeArtiste = new LinkedList<Artiste>();
	List<Representation> listeRepresentation;
	SpectacleDAO DAOspec = new SpectacleDAO(BosquetConnection.getInstance());
	ArtisteDAO DAOart = new ArtisteDAO(BosquetConnection.getInstance());
	RepresentationDAO DAOrepresentation = new RepresentationDAO(BosquetConnection.getInstance());
	PlanningSalleDAO DAOplan = new PlanningSalleDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public Spectacle() {
		
	}
	
	public Spectacle(int idSpectacle, PlanningSalle planning, String titre, String description, int nbrPlaceParClient, Configuration conf) {
		this.idSpectacle=idSpectacle;
		this.planning=planning;
		this.titre = titre;
		this.description=description;
		this.nbrPlaceParClient = nbrPlaceParClient;	
		this.conf = conf;
	}
	
	public Spectacle(PlanningSalle planning, String titre, String description, int nbrPlaceParClient, Configuration conf) {
		this.planning=planning;
		this.titre = titre;
		this.description=description;
		this.nbrPlaceParClient = nbrPlaceParClient;
		this.conf = conf;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdSpectacle() {
        return idSpectacle;
    }
    public void setIdSpectacle(int idSpectacle) {
        this.idSpectacle = idSpectacle;
    }
    
    public PlanningSalle getPlanning() {
		return planning;
	}

	public Configuration getConf() {
		return conf;
	}

	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	public void setPlanning(PlanningSalle planning) {
		this.planning = planning;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Representation> getListeRepresentation() {
		return listeRepresentation;
	}

	public void setListeRepresentation(List<Representation> listeRepresentation) {
		this.listeRepresentation = listeRepresentation;
	}
		
	public String  getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
      
    public List<Artiste>  getListeArtiste() {
        return listeArtiste;
    }
    public void setListeArtiste(List<Artiste> listeArtiste) {
        this.listeArtiste = listeArtiste;
    }
    
    public int  getNbrPlaceParClient() {
        return nbrPlaceParClient;
    }

    public void setNbrPlaceParClient(int nbrPlaceParClient) {
        this.nbrPlaceParClient = nbrPlaceParClient;
    }
    	
	
	/*METHODES*/
    public boolean add() {   	
		return DAOspec.create(this);	
	}
    
    public List<Spectacle> getAll(){
		return DAOspec.getAll();
    }
    
    
    public List<Object> afficherSpecEtArtiste(){
    	List<Object> listOBJ = new LinkedList<Object>();
    	List<Spectacle> list = new LinkedList<Spectacle>();
    	list  = DAOspec.getAll();
    	listeArtiste = DAOart.getAll();
    	listOBJ.addAll(list);
    	listOBJ.addAll(listeArtiste);
    	return listOBJ;
    }
    
    public boolean update() {
		return DAOspec.update(this);
		
	}
    
    @Override
    public String toString() { 
        return String.format(titre); 
    }
    
    public boolean delete() {
        return DAOspec.delete(this);
    }

    
    public List<Artiste> getArtisteDuSPectacle(){
    	listeArtiste = new LinkedList<Artiste>();
    	listeArtiste = DAOart.getAll();
        List<Artiste> listeArtSpec = new LinkedList<Artiste>();

        for(int i = 0; i<listeArtiste.size(); i++) {
            if(listeArtiste.get(i).getSpectacle().getIdSpectacle() == idSpectacle) {
            	listeArtSpec.add(listeArtiste.get(i));
            }
        }
        return listeArtSpec;
    }
}