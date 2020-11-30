package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.ArtisteDAO;
import DAO.BosquetConnection;
import DAO.ConfigurationDAO;
import DAO.DAO;
import DAO.PlanningSalleDAO;
import DAO.SpectacleDAO;

public class Spectacle {
	/*Attributs*/
	private int idSpectacle;
	private int idPlanningSalle;	//FK idPlanningSalle
	private String titre;
	List<Artiste> listeArtiste = new LinkedList<Artiste>();
	private int nbrPlaceParClient;
	List<Configuration> listeConfiguration;
	SpectacleDAO DAOspec = new SpectacleDAO(BosquetConnection.getInstance());
	ArtisteDAO DAOart = new ArtisteDAO(BosquetConnection.getInstance());
	ConfigurationDAO DAOconf = new ConfigurationDAO(BosquetConnection.getInstance());
	PlanningSalleDAO DAOplan = new PlanningSalleDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public Spectacle() {
		
	}
	
	public Spectacle(int idPlanningSalle, String titre, int nbrPlaceParClient) {
		this.titre = titre;
		this.idPlanningSalle = idPlanningSalle;
		listeArtiste = new LinkedList<Artiste>();
		listeArtiste = DAOart.getAll();
		listeConfiguration = new LinkedList<Configuration>();
		this.nbrPlaceParClient = nbrPlaceParClient;
	}
	
	public Spectacle(int idSpectacle, int idPlanningSalle, String titre, int nbrPlaceParClient) {
		this.idSpectacle = idSpectacle;
		this.titre = titre;
		this.idPlanningSalle = idPlanningSalle;
		listeArtiste = new LinkedList<Artiste>();
		listeArtiste = DAOart.getAll();
		listeConfiguration = new LinkedList<Configuration>();
		this.nbrPlaceParClient = nbrPlaceParClient;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdSpectacle() {
        return idSpectacle;
    }
    public void setIdSpectacle(int idSpectacle) {
        this.idSpectacle = idSpectacle;
    }
    
    public int  getIdPlanningSalle() {
        return idPlanningSalle;
    }
    public void setIdPlanningSalle(int idPlanningSalle) {
        this.idPlanningSalle = idPlanningSalle;
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
    
    public List<Configuration>  getListeConfiguration() {
        return listeConfiguration;
    }
    public void setListeConfiguration(List<Configuration> listeConfiguration) {
        this.listeConfiguration = listeConfiguration;
    }
    
    public int  getNbrPlaceParClient() {
        return nbrPlaceParClient;
    }

    public void setNbrPlaceParClient(int nbrPlaceParClient) {
        this.nbrPlaceParClient = nbrPlaceParClient;
    }
    	
	
	/*METHODES*/
    public boolean add(Spectacle spec) {   	
		return DAOspec.create(spec);	
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
    	for (Object obj : listOBJ) {
    		System.out.println(obj);
        }
    	return listOBJ;
    }
    
    public boolean update(Spectacle spe) {
		return DAOspec.update(spe);
		
	}
    
    @Override
    public String toString() { 
        return String.format(titre); 
    }
    
    public boolean delete(Spectacle obj) {
        return DAOspec.delete(obj);
    }
    
    public Configuration getConfigDuSpectacle(int idSpec) {
    	listeConfiguration = new LinkedList<Configuration>();
    	listeConfiguration = DAOconf.getAll();
		Configuration confTrouvee = new Configuration();
		for(int i = 0; i<listeConfiguration.size(); i++) {
			if(listeConfiguration.get(i).getIdSpectacle() == idSpec) {
				confTrouvee = listeConfiguration.get(i);
			}
		}
		return confTrouvee;
	}
    
    public PlanningSalle getPlanningBySpec(int idPlanning) {
        List<PlanningSalle> list = new LinkedList<PlanningSalle>();
        list = DAOplan.getAll();
        PlanningSalle planSpec = new PlanningSalle();

        for(int i = 0; i<list.size(); i++) {
            if(list.get(i).getId() == idPlanning) {
                planSpec = list.get(i);
            }
        }
        return planSpec;
    }
    
    public List<Artiste> getArtisteDuSPectacle(int idSpectacle){
    	listeArtiste = new LinkedList<Artiste>();
    	listeArtiste = DAOart.getAll();
        List<Artiste> listeArtSpec = new LinkedList<Artiste>();

        for(int i = 0; i<listeArtiste.size(); i++) {
            if(listeArtiste.get(i).getIdSpectacle() == idSpectacle) {
            	listeArtSpec.add(listeArtiste.get(i));
            }
        }
        return listeArtSpec;
    }
}