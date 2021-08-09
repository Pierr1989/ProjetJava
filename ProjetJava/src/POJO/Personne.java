package POJO;

import java.io.Serializable;

import DAO.BosquetConnection;
import DAO.PersonneDAO;

public abstract class Personne implements Serializable{
    private static final long serialVersionUID = 7787170877756499146L;
    protected int id;
    protected String nom = "";
    protected String prenom = "";
    protected String adresse ="";
    protected String telephone = "";
    protected String pseudo ="";
    protected String password ="";
    
    public Personne() {
    	
    }
    
    public Personne(String nom) {
        this.nom = nom;
    }
    
    public Personne(int id,String nom, String prenom, String adresse, String telephone, String pseudo, String password) {
    	this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.pseudo = pseudo;
        this.password = password;
    }
    
    public Personne(String nom, String prenom, String adresse, String telephone, String pseudo, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.pseudo = pseudo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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
    
    public boolean VerifTel(String telephone) {
    	PersonneDAO DAO = new PersonneDAO(BosquetConnection.getInstance());
		return DAO.VerifTel(telephone);
    }
}
