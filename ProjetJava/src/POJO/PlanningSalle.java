package POJO;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import java.text.ParseException;

import DAO.BosquetConnection;
import DAO.PlanningSalleDAO;
import DAO.SpectacleDAO;

public class PlanningSalle {
	/*Attributs*/
	private int idPlanning;
	private Gestionnaire gestionnaire;	//FK Gestionnaire
	private Reservation reservation;  //FK
	private String dateDebutR;
	private String dateFinR;
	private boolean reserve;
	private List<Spectacle> listeSpectacle;
	PlanningSalleDAO DAO = new PlanningSalleDAO(BosquetConnection.getInstance());
	SpectacleDAO DAOspec = new SpectacleDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public PlanningSalle() {
		
	}
	
	public PlanningSalle(String dateDebutR, String dateFinR, Gestionnaire gestionnaire, Reservation reservation, boolean reserve) {
		this.dateDebutR = dateDebutR;
		this.dateFinR = dateFinR;
		this.gestionnaire = gestionnaire;
		this.reservation = reservation;
		this.reserve = reserve;
	}
	
	public PlanningSalle(int idPlanning, String dateDebutR, String dateFinR, Gestionnaire gestionnaire, Reservation reservation, boolean reserve) {
		this.idPlanning = idPlanning;
		this.dateDebutR = dateDebutR;
		this.dateFinR = dateFinR;
		this.gestionnaire = gestionnaire;
		this.reservation = reservation;
		this.reserve = reserve;
	}
	
	
	/*ACCESSEURS*/
    
    public boolean  getReserve() {
        return reserve;
    }

    public int getIdPlanning() {
		return idPlanning;
	}

	public void setIdPlanning(int idPlanning) {
		this.idPlanning = idPlanning;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getDateDebutR() {
		return dateDebutR;
	}

	public void setDateDebutR(String dateDebutR) {
		this.dateDebutR = dateDebutR;
	}

	public String getDateFinR() {
		return dateFinR;
	}

	public void setDateFinR(String dateFinR) {
		this.dateFinR = dateFinR;
	}

	public void setReserve(boolean reserve) {
        this.reserve = reserve;
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
		
	public boolean Update() {
		return DAO.update(this);
	}
	
	
	 @Override
	    public String toString() { 
	        return String.format(idPlanning + " - " + dateDebutR + " - " + dateFinR); 
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
   
   
   public PlanningSalle getPlanningParSpectacle(int idSpectacle) {
       List<PlanningSalle> list = new LinkedList<PlanningSalle>();
       list = DAO.getAll();
       PlanningSalle planningSpec = new PlanningSalle();

       for(int i = 0; i<list.size(); i++) {
           if(list.get(i).getIdPlanning() == idSpectacle) {
        	   planningSpec = list.get(i);
           }
       }
       return planningSpec;
   }
   
   @SuppressWarnings("deprecation")
   public double prixLocation() {
           Date dateModif = null;
           try {
        	   dateModif = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateDebutR);
           } catch (ParseException e) {
               e.printStackTrace();
           }
           if(dateModif.getDay() == 5 || dateModif.getDay() == 6) {
               return 4500;
           }
           return 3000;
    }
   
}