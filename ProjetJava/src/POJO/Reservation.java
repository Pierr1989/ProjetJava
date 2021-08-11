package POJO;

import java.util.List;

import DAO.BosquetConnection;
import DAO.PlanningSalleDAO;
import DAO.ReservationDAO;

public class Reservation {
	/*Attributs*/
	private int idReservation;
	private Organisateur organisateur;
	private double acompte = 0;	//à zéro de base, à implémenter par la suite car optionnel
	private double solde;
	private String statut;
	private double prix;
	ReservationDAO DAO = new ReservationDAO(BosquetConnection.getInstance());
	PlanningSalleDAO DAOPlanning = new PlanningSalleDAO(BosquetConnection.getInstance());
	
	/*CONSTRUCTEURS*/
	public Reservation() {
		
	}
	
	public Reservation(Organisateur organisateur, double acompte, double solde, String statut, double prix) {
		this.acompte = acompte;
		this.solde = solde;
		this.statut = statut;
		this.prix = prix;
		this.organisateur = organisateur;
	}
	
	public Reservation(int idReservation, Organisateur organisateur, double acompte, double solde, String statut, double prix) {
		this.idReservation = idReservation;
		this.acompte = acompte;
		this.solde = solde;
		this.statut = statut;
		this.prix = prix;
		this.organisateur = organisateur;
	}
	
	
	/*ACCESSEURS*/
	public int  getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

	public Organisateur getOrganisateur() {
		return organisateur;
	}

	public void setOrganisateur(Organisateur organisateur) {
		this.organisateur = organisateur;
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
    public boolean add() {
		return DAO.create(this);	
	}
    
    public boolean delete() {
    	return DAO.delete(this);
    }
    
    public List<Reservation> getAll() {
    	return DAO.getAll();
    }
}