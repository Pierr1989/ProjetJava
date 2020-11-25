package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Gestionnaire;
import POJO.Personne;
import POJO.PlanningSalle;

// Agit comme un admin ==> Pr�sent en dur dans le code
public class GestionnaireDAO extends DAO<Gestionnaire> {

	public GestionnaireDAO(Connection conn){
        super(conn);
    }

	@Override
    public boolean create(Gestionnaire obj) {
        try
        {
        	int idPersonne = 0;
        	
        	// On cr�e d'abord une personne
        	DAO<Personne> personneDAO = new PersonneDAO(BosquetConnection.getInstance());
        	personneDAO.create(obj);
        	  
        	// On r�cup�re le plus grand id de la base de donn�e(La derni�re entr�e)
        	ResultSet result = this.connect.createStatement(
        			ResultSet.TYPE_SCROLL_INSENSITIVE,
        		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT MAX(id) FROM Personne");
        
        	if(result.next())
            	idPersonne = result.getInt(1);
        	
        	// On insert cette id dans la table correspondante
            PreparedStatement state = connect.prepareStatement("INSERT INTO Gestionnaire(idGestionnaire) VALUES (?)");
            state.setInt(1, idPersonne);
            state.execute();
            return true;
        }

        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
	@Override
    public boolean delete(Gestionnaire obj){
        return false;
    }

	@Override
    public boolean update(Gestionnaire obj){
        return false;
    }
	
	/*public Gestionnaire login(String telephone, String password) {
		Gestionnaire gestionnaire = new Gestionnaire();
    	try {
    		String sql ="SELECT * FROM Personne INNER JOIN Gestionnaire ON Gestionnaire.idGestionnaire = Personne.id WHERE telephone = '" + telephone + "'AND password = '" + password + "'";
    		ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	gestionnaire = new Gestionnaire(result.getInt("idGestionnaire"),result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("telephone"), result.getString("pseudo"), result.getString("password"));      	
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return gestionnaire;
    }*/


}
