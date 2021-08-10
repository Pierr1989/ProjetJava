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