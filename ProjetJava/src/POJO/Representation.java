package POJO;

import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.RepresentationDAO;

public class Representation {
	/*Attributs*/
	private int idRepresentation;
	private String date;
	private String heureDebut;
	private String heureFin;
	private Spectacle spectacle;
	private Place place = new Place();
	RepresentationDAO DAO = new RepresentationDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public Representation() {
		
	}
	
	public Representation(String date, String heureDebut, String heureFin, Spectacle spectacle, Place place) {
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.spectacle = spectacle;
		this.place = place;
	}
	
	public Representation(int idRepresentation, String date, String heureDebut, String heureFin, Spectacle spectacle, Place place) {
		this.idRepresentation = idRepresentation;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.spectacle = spectacle;
		this.place = place;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdRepresentation() {
        return idRepresentation;
    }
    public void setIdRepresentation(int idRepresentation) {
        this.idRepresentation = idRepresentation;
    }     
    
    public String  getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
		
	public Spectacle getSpectacle() {
		return spectacle;
	}

	public void setSpectacle(Spectacle spectacle) {
		this.spectacle = spectacle;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String  getHeureDebut() {
        return heureDebut;
    }
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }
       
    public String  getHeureFin() {
        return heureFin;
    }
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }
    
	
	/*METHODES*/
    public boolean add(Representation repre) {
		return DAO.create(repre);	
	}
    
	public List<PlanningSalle> ChoixPlageHoraire(){
		
		return null;
	}
	
	@Override
    public String toString() { 
		String.format("[%1$.10s]", date);
        return String.format("Date-Heure : " + date + "  -   " + heureDebut + " - " + heureFin); 
    }
	
	public List<Representation> getAll(){
		return DAO.getAll();
    }
	
	public List<Representation> getRepresentationDuSpectacle(int idSpec) {
		List<Representation> listToutesRep = new LinkedList<Representation>();
		listToutesRep = DAO.getAll();
		List<Representation> listRepSpec = new LinkedList<Representation>();
		for(int i = 0; i<listToutesRep.size(); i++) {
			if(listToutesRep.get(i).spectacle.getIdSpectacle() == idSpec) {
				listRepSpec.add(listToutesRep.get(i));
				//listRepSpec.add(i, listToutesRep.get(i));
			}
		}
		return listRepSpec;
	}
}