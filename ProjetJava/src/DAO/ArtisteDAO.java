package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Artiste;
import POJO.Client;
import POJO.Personne;
import POJO.PlanningSalle;

public class ArtisteDAO extends DAO<Artiste>{
	public int idSpectacle;
	
    public ArtisteDAO(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(Artiste obj) {
    	int idRecup = 0;
        try
        {
        	int idPersonne = 0;
        	
        	DAO<Personne> personneDAO = new PersonneDAO(BosquetConnection.getInstance());
        	personneDAO.create(obj);
        	       
        	
            PreparedStatement state = connect.prepareStatement("INSERT INTO Artiste(idArtiste, arti_spec_k) VALUES (?,?)");
            state.setInt(1, idPersonne);
            state.setInt(2, obj.getIdSpectacle());
            state.execute();
            ResultSet rs = state.getGeneratedKeys();
            if(rs.next()) {
                idRecup = rs.getInt(1);
                obj.setId(idRecup);
            }
            return true;
        }

        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public boolean delete(Artiste obj){
        return false;
    }

    @Override
	public boolean update(Artiste obj) {
		try { 	
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT MAX(idSpectacle) FROM Spectacle");
			if(result.next())
				idSpectacle = result.getInt(1);
			
			PreparedStatement state = connect.prepareStatement("UPDATE Artiste SET idArtiste =?, arti_spec_k =? WHERE idArtiste = " + obj.getId());
            state.setInt(1,  obj.getId());
			state.setInt(1, idSpectacle);
		    state.executeUpdate();
		    return true;
		}	      
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
		return false;
	}

    @Override
    public Artiste find(int id){
    	Artiste artiste = new Artiste();
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Artiste WHERE id = " + id);
            if(result.first())
            	artiste = new Artiste(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("telephone"), result.getString("pseudo"), result.getString("password"), result.getInt("arti_spec_k"));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return artiste;
    }

    @Override
    public List<Artiste> getAll() {
        List<Artiste> list = new LinkedList<Artiste>();
         try {
             String sql ="SELECT * FROM Personne INNER JOIN Artiste ON Artiste.idArtiste = Personne.id";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Artiste artiste = new Artiste();
            	 artiste.setNom(result.getString("nom"));
            	 artiste.setAdresse(result.getString("adresse"));
                 list.add(artiste);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
