package POJO;


import java.util.LinkedList;
import java.util.List;

import DAO.BosquetConnection;
import DAO.DAO;
import DAO.PlanningSalleDAO;
import DAO.ReservationDAO;

public class Reservation {
	/*Attributs*/
	private int idReservation;
	private int idOrganisateur;
	private double acompte = 0;	//à zéro de base, à implémenter par la suite car optionnel
	private double solde;
	private String statut;
	private double prix;
	List<PlanningSalle> listePlanningSalle;
	ReservationDAO DAO = new ReservationDAO(BosquetConnection.getInstance());
	PlanningSalleDAO DAOPlanning = new PlanningSalleDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public Reservation() {
		
	}
	
	public Reservation(double acompte, double solde, String statut, double prix, int idOrganisateur) {
		this.acompte = acompte;
		this.solde = solde;
		listePlanningSalle = new LinkedList<PlanningSalle>();
		this.statut = statut;
		this.prix = prix;
		this.idOrganisateur = idOrganisateur;
	}
	
	public Reservation(int idReservation, double acompte, double solde, String statut, double prix, int idOrganisateur) {
		this.idReservation = idReservation;
		this.acompte = acompte;
		this.solde = solde;
		listePlanningSalle = new LinkedList<PlanningSalle>();
		this.statut = statut;
		this.prix = prix;
		this.idOrganisateur = idOrganisateur;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
    
    public int  getIdOrganisateur() {
        return idOrganisateur;
    }
    public void setIdOrganisateur(int idOrganisateur) {
        this.idOrganisateur = idOrganisateur;
    }
	
    public List<PlanningSalle>  getListArtiste() {
        return listePlanningSalle;
    }
    public void setListePlanningSalle(List<PlanningSalle> listePlanningSalle) {
        this.listePlanningSalle = listePlanningSalle;
    }
	
	public double  getAcompte() {
        return acompte;
    }
    public void setAcompte(double acompte) {
        this.acompte = acompte;
    }
    
    
    public double  getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    public String  getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public Double  getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
	
	
	/*METHODES*/
    public boolean add(Reservation res) {
		ReservationDAO DAO = new ReservationDAO(BosquetConnection.getInstance());
		return DAO.create(res);	
	}
    
    public List<Reservation> getAll() {
    	return DAO.getAll();
    }
    
    public double prixLocation(PlanningSalle prixPlan) {
    	if(prixPlan.getDateDebutR().getDay() == 5 || prixPlan.getDateDebutR().getDay() == 6) {
    		return 4500;
    	}
    	return 3000;
    }
    
}