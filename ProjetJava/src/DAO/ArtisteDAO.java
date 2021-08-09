package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Artiste;
import POJO.Spectacle;

public class ArtisteDAO extends DAO<Artiste>{
	
    public ArtisteDAO(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(Artiste obj) {
    	try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Artiste(idArtiste, nomDeScene, arti_spec_k) VALUES (?,?,?)");
            state.setLong(1, obj.getId());
            state.setString(2, obj.getNomDeScene());
            state.setInt(3, obj.getSpectacle().getIdSpectacle());
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
    public boolean delete(Artiste obj){
        return false;
    }

    @Override
	public boolean update(Artiste obj) {
    	
		return false;
	}

    @Override
    public Artiste find(int id){
    	Artiste artiste = null;
    	Spectacle spec = new Spectacle();
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Artiste WHERE idArtiste = " + id);
            if(result.first()) {
            	SpectacleDAO DAO = new SpectacleDAO(BosquetConnection.getInstance());
            	spec = DAO.find(result.getInt("arti_spec_k"));
            	artiste = new Artiste(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("password"), result.getString("email"), result.getString("role"), result.getString("nomDeScene"), spec);
            }
            	
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
            	 Spectacle spec = new Spectacle();
            	 SpectacleDAO DAO = new SpectacleDAO(BosquetConnection.getInstance());
             	 spec = DAO.find(result.getInt("arti_spec_k"));      	 
            	 artiste.setNom(result.getString("nom"));
            	 artiste.setAdresse(result.getString("adresse"));
            	 artiste.setSpectacle(spec);
                 list.add(artiste);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
