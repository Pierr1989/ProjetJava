package POJO;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import DAO.BosquetConnection;
import DAO.PlanningSalleDAO;

public class PlanningSalle {
	/*Attributs*/
	private int id;
	private int idGestionnaire;	//FK Gestionnaire
	private int idReservation;  //FK
	private Date dateDebutR;
	private Date dateFinR;
	private boolean reserve;
	private List<Spectacle> listeSpectacle;
	PlanningSalleDAO DAO = new PlanningSalleDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public PlanningSalle() {
		
	}
	
	public PlanningSalle(Date dateDebutR, Date dateFinR, int idGestionnaire, int idReservation, boolean reserve) {
		this.dateDebutR = dateDebutR;
		this.dateFinR = dateFinR;
		listeSpectacle = new LinkedList<Spectacle>();
		this.idGestionnaire = idGestionnaire;
		this.idReservation = idReservation;
		this.reserve = reserve;
	}
	
	public PlanningSalle(int id, Date dateDebutR, Date dateFinR, int idGestionnaire, int idReservation, boolean reserve) {
		this.id = id;
		this.dateDebutR = dateDebutR;
		this.dateFinR = dateFinR;
		listeSpectacle = new LinkedList<Spectacle>();
		this.idGestionnaire = idGestionnaire;
		this.idReservation = idReservation;
		this.reserve = reserve;
	}
	
	
	/*ACCESSEURS*/

	public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int  getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }
    
    public int  getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
    
    public boolean  getReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }
    
    public Date  getDateDebutR() {
        return dateDebutR;
    }

    public void setDateDebutR(Date dateDebutR) {
        this.dateDebutR = dateDebutR;
    }
    public Date  getDateFinR() {
        return dateFinR;
    }

    public void setDateFinR(Date dateFinR) {
        this.dateFinR = dateFinR;
    }
	
    public List<Spectacle> getListeSpectacle() {
        return listeSpectacle;
    }
    public void setListeSpectacle(List<Spectacle> listeSpectacle) {
        this.listeSpectacle = listeSpectacle;
    }
    
    
    public List<PlanningSalle> getAll() {
        return DAO.getAll();
    }
    
    public List<PlanningSalle> getLibre() {
    	List<PlanningSalle> liste = new LinkedList<PlanningSalle>();
    	List<PlanningSalle> listeDateDispo = new LinkedList<PlanningSalle>();
    	liste = DAO.getAll();
		for(PlanningSalle planS : liste) {
			if(planS.getReserve() == false){
				listeDateDispo.add(planS);
			}
		}	
		return listeDateDispo;
    }
	
	/*METHODES*/
    public boolean checkDate(PlanningSalle date) {
    	List<PlanningSalle> liste = new LinkedList<PlanningSalle>();
		liste = DAO.getAll();
		for(PlanningSalle planS : liste) {
			if(planS.equals(date)){
				return true;
			}
		}			
		return false;
	}

	public boolean AddPlanningSalle(PlanningSalle salle) {	
		return DAO.create(salle);		
	}
	
	public boolean DeletePlanningSalle(PlanningSalle salle) {	
		return DAO.delete(salle);		
	}
	
	public boolean AnnulerReservation(PlanningSalle salle) {
		salle.setReserve(false);	
		return DAO.update(salle);
	}
		
	public boolean Update(PlanningSalle salle) {
		salle.setReserve(true);		
		return DAO.update(salle);
	}
	
	
	 @Override
	    public String toString() { 
	        return String.format(id + " - " + dateDebutR + " - " + dateFinR); 
	    }
	 @Override
     public boolean equals(Object obj) { // compare les valeurs des objets
       PlanningSalle plaS;
       if (obj ==null || obj.getClass()!=this.getClass()){
           return false;
       }

       else {
           plaS =(PlanningSalle)obj;
           if(
                   plaS.getDateDebutR().equals(getDateDebutR()) //vu que type primitif == pas equals
                   & plaS.getDateFinR().equals(getDateFinR())) { 
               {
                   return true;
               }
           }

           else {
                   return false;
           }
       }
   }

   // Surcharge de hashCode
   @Override
   public int hashCode() {
       return Objects.hash(dateDebutR, dateFinR);
   }	
}
