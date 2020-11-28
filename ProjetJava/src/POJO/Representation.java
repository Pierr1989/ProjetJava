package POJO;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;



import DAO.BosquetConnection;
import DAO.RepresentationDAO;
import DAO.ReservationDAO;

public class Representation {
	/*Attributs*/
	private int idRepresentation;
	private Date date;
	private Date heureDebut;
	private Date heureFin;
	RepresentationDAO DAO = new RepresentationDAO(BosquetConnection.getInstance());
	private int idSpectacle;
	private int idPlace;
	private int numeroRepresentation;
	
	/*CONSTRUCTEURS*/
	public Representation() {
		
	}
	
	public Representation(Date date, Date heureDebut, Date heureFin, int idSpectacle, int idPlace, int numeroRepresentation) {
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.idSpectacle = idSpectacle;
		this.idPlace = idPlace;
		this.numeroRepresentation = numeroRepresentation;
	}
	
	public Representation(int idRepresentation, Date date, Date heureDebut, Date heureFin, int idSpectacle, int idPlace, int numeroRepresentation) {
		this.idRepresentation = idRepresentation;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.idSpectacle = idSpectacle;
		this.idPlace = idPlace;
		this.numeroRepresentation = numeroRepresentation;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdRepresentation() {
        return idRepresentation;
    }
    public void setIdRepresentation(int idRepresentation) {
        this.idRepresentation = idRepresentation;
    }
    public int  getIdSpectacle() {
        return idSpectacle;
    }
    public void setIdSpectacle(int idSpectacle) {
        this.idSpectacle = idSpectacle;
    }
    public int  getIdPlace() {
        return idPlace;
    }
    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }
    
    public int  getNumeroRepresentation() {
        return numeroRepresentation;
    }
    public void setNumeroRepresentation(int numeroRepresentation) {
        this.numeroRepresentation = numeroRepresentation;
    }
    
    public Date  getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
		
	public Date  getHeureDebut() {
        return heureDebut;
    }
    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }
       
    public Date  getHeureFin() {
        return heureFin;
    }
    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }
    
	
	
	/*METHODES*/
    public boolean add(Representation repre) {
		return DAO.create(repre);	
	}
    
	public List<PlanningSalle> ChoixPlageHoraire(){
		
		return null;
	}
	
	public List<Representation> getAll(){
		return DAO.getAll();
    }
	
	public List<Representation> getRepresentationDuSpectacle(int idSpec) {
		List<Representation> listToutesRep = new LinkedList<Representation>();
		listToutesRep = DAO.getAll();
		List<Representation> listRepSpec = new LinkedList<Representation>();
		for(int i = 0; i<listToutesRep.size(); i++) {
			if(listToutesRep.get(i).getIdSpectacle() == idSpec) {
				listRepSpec.add(listToutesRep.get(i));
				//listRepSpec.add(i, listToutesRep.get(i));
			}
		}
		return listRepSpec;
	}
	
	@Override
    public String toString() { 
		int heureD=0;
		if(numeroRepresentation == 1)
			heureD = 16;
		if(numeroRepresentation == 2)
			heureD =20;
		if(numeroRepresentation == 3)
			heureD =8;
		if(numeroRepresentation == 4)
			heureD =10;
		
		
        return ("Date: " + date + " de " + heureD + "h à " + (heureD+=2) + "h    (id : " + idRepresentation + ")"); 
    }
}