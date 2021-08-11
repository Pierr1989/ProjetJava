package POJO;

import java.io.Serializable;
import java.util.List;

import DAO.BosquetConnection;
import DAO.PersonneDAO;

public abstract class Personne implements Serializable{
    private static final long serialVersionUID = 7787170877756499146L;
    protected int id;
    protected String nom = "";
    protected String prenom = "";
    protected String adresse ="";
    protected String password ="";
    protected String email ="";
    protected String role ="";
    protected PersonneDAO DAO = new PersonneDAO(BosquetConnection.getInstance());
    
    public Personne() {
    	
    }
    
    public Personne(int id,String nom, String prenom, String adresse, String password, String email, String role) {
    	this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    
    public Personne(String nom, String prenom, String adresse, String password, String email, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() { 
        return String.format(nom + " " + prenom); 
    }
    
    public boolean add() {
    	return DAO.create(this);
    }
    
    public boolean delete(Personne obj) {
        return DAO.delete(obj);
    }
    
    public List<? extends Personne> getAll() {	//solution pour que filles héritent de la méthode getAll()
        return DAO.getAll();
    }
    
}