package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import POJO.Client;
import POJO.Personne;

public class ClientDAO extends DAO<Client>{
	public int idPersonne;
    public ClientDAO(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(Client obj) {
        try
        {       	
        	// On crée d'abord une personne
        	DAO<Personne> personneDAO = new PersonneDAO(BosquetConnection.getInstance());
        	personneDAO.create(obj);
        	  
        	// On récupère le plus grand id de la base de donnée(La dernière entrée)
        	ResultSet result = this.connect.createStatement(
        			ResultSet.TYPE_SCROLL_INSENSITIVE,
        		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT MAX(id) FROM Personne");
        
        	if(result.next())
            	idPersonne = result.getInt(1);
        	
        	
        	// On insert cette id dans la table correspondante
            PreparedStatement state = connect.prepareStatement("INSERT INTO Client(idClient) VALUES (?)");
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
    public boolean delete(Client obj){
        return false;
    }

    @Override
    public boolean update(Client obj){
        return false;
    }


}

