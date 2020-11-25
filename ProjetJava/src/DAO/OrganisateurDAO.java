package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Organisateur;
import POJO.Personne;

// Un organisateur va créer des artistes
public class OrganisateurDAO extends DAO<Organisateur>{

	public OrganisateurDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Organisateur obj) {
		try
        {
        	int idPersonne = 0;
        	
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
            PreparedStatement state = connect.prepareStatement("INSERT INTO Organisateur(idOrganisateur) VALUES (?)");
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
	
	/*public Organisateur login(String telephone, String password) {
		Organisateur organisateur = new Organisateur();
    	try {
    		String sql ="SELECT * FROM Personne INNER JOIN Organisateur ON Organisateur.idOrganisateur = Personne.id WHERE telephone = '" + telephone + "'AND password = '" + password + "'";
    		ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	organisateur = new Organisateur(result.getInt("idOrganisateur"),result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("telephone"), result.getString("pseudo"), result.getString("password"));      	
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return organisateur;
    }*/

	@Override
	public boolean delete(Organisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Organisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
    public Organisateur find(int id){
		Organisateur organisateur = new Organisateur();
        try{
        	String sql ="SELECT * FROM Personne INNER JOIN Organisateur ON Organisateur.idOrganisateur = Personne.id WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	organisateur = new Organisateur(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("telephone"), result.getString("pseudo"), result.getString("password"));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return organisateur;
    }

	@Override
	public List<Organisateur> getAll() {
		List<Organisateur> list = new LinkedList<Organisateur>();
        try {
            String sql ="SELECT * FROM Personne INNER JOIN Organisateur ON Organisateur.idOrganisateur = Personne.id";
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            while(result.next()) {
            	Organisateur organisateur = new Organisateur();
            	organisateur.setId(result.getInt("idOrganisateur"));
            	organisateur.setNom(result.getString("nom"));
            	organisateur.setPrenom(result.getString("prenom"));
            	organisateur.setAdresse(result.getString("adresse"));
            	organisateur.setTelephone(result.getString("telephone"));
            	organisateur.setPseudo(result.getString("pseudo"));
            	organisateur.setPassword(result.getString("password"));
                list.add(organisateur);
           }
        } catch (SQLException e) {
           e.printStackTrace();
       }
        return list;
	}
}
