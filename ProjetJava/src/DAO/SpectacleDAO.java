package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Client;
import POJO.PlanningSalle;
import POJO.Spectacle;

public class SpectacleDAO extends DAO<Spectacle> {
	private int idRecup = 0;	
	
	public SpectacleDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Spectacle obj) {
		int id = 0;
		try
        {			
			
            PreparedStatement state = connect.prepareStatement("INSERT INTO Spectacle(idSpectacle, titre, nbrePlaceParCLient, spec_plan_k) VALUES (?,?,?,?)");
            state.setInt(1, obj.getIdSpectacle());
            state.setString(2, obj.getTitre());                      
            state.setInt(3, obj.getNbrPlaceParClient());
            state.setInt(4,  obj.getIdPlanningSalle()); 
            state.execute();
            ResultSet rs = state.getGeneratedKeys();
            if(rs.next()) {
            	id = rs.getInt(1);
                obj.setIdSpectacle(id);
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
	public boolean delete(Spectacle obj) {		
		try
        {
            PreparedStatement state = connect.prepareStatement("DELETE FROM Spectacle WHERE idSpectacle = ?");
			//PreparedStatement state = connect.prepareStatement("CASCADE ON DELETE FROM Spectacle INNER JOIN Artiste ON Spectacle.idSpectacle = Artiste.arti_spe_k INNER JOIN Configuration ON Spectacle.idSpectacle = Configuration.conf_spec_k INNER JOIN Categorie ON Configuration.idConfiguration = Categorie.cat_conf_k WHERE idSpectacle = ?");
            state.setInt(1, obj.getIdSpectacle());
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
	public boolean update(Spectacle obj) {
		
		return false;
	}

	@Override
	public Spectacle find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<Spectacle> getAll() {
        List<Spectacle> list = new LinkedList<Spectacle>();
         try {
             String sql ="SELECT * FROM Spectacle";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Spectacle spectacle = new Spectacle();
            	 spectacle.setIdSpectacle(result.getInt("idSpectacle"));
            	 spectacle.setTitre(result.getString("titre"));
            	 spectacle.setNbrPlaceParClient(result.getInt("nbrePlaceParClient"));
            	 spectacle.setIdPlanningSalle(result.getInt("spec_plan_k"));
                 list.add(spectacle);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
