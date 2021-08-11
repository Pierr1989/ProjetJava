package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Configuration;
import POJO.PlanningSalle;
import POJO.Spectacle;

public class SpectacleDAO extends DAO<Spectacle> {	
	
	public SpectacleDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Spectacle obj) {
		int id = 0;
		try
        {			
            PreparedStatement state = connect.prepareStatement("INSERT INTO Spectacle(idSpectacle, titre, description, nbrePlaceParCLient, spec_plan_k, spec_conf_k) VALUES (?,?,?,?,?,?)");
            state.setInt(1, obj.getIdSpectacle());
            state.setString(2, obj.getTitre());   
            state.setString(3, obj.getDescription()); 
            state.setInt(4, obj.getNbrPlaceParClient());
            state.setInt(5,  obj.getPlanning().getIdPlanning()); 
            state.setInt(6,  obj.getConf().getIdConfiguration());
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
		Spectacle spectacle = null;
		PlanningSalle plan = null;
		Configuration conf = null;
		PlanningSalleDAO planDAO = new PlanningSalleDAO(BosquetConnection.getInstance());
		ConfigurationDAO confDAO = new ConfigurationDAO(BosquetConnection.getInstance());
        try{
        	String sql ="SELECT * FROM Spectacle WHERE idSpectacle = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first()) {
            	plan = planDAO.find(result.getInt("spec_plan_k"));
            	conf = confDAO.find(result.getInt("spec_conf_k"));
            	spectacle = new Spectacle(id, plan, result.getString("titre"), result.getString("description"), result.getInt("nbrePlaceParClient"), conf);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return spectacle;
	}

	@Override
    public List<Spectacle> getAll() {
        List<Spectacle> list = new LinkedList<Spectacle>();
        PlanningSalle plann = new PlanningSalle();
        Configuration conf = null;
        PlanningSalleDAO DAO = new PlanningSalleDAO(BosquetConnection.getInstance());
        ConfigurationDAO confDAO = new ConfigurationDAO(BosquetConnection.getInstance());
         try {
             String sql ="SELECT * FROM Spectacle";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Spectacle spectacle = new Spectacle();           	 
            	 plann = DAO.find(result.getInt("spec_plan_k"));
            	 conf = confDAO.find(result.getInt("spec_conf_k"));
            	 spectacle.setIdSpectacle(result.getInt("idSpectacle"));
            	 spectacle.setTitre(result.getString("titre"));
            	 spectacle.setDescription(result.getString("description"));
            	 spectacle.setNbrPlaceParClient(result.getInt("nbrePlaceParClient"));
            	 spectacle.setPlanning(plann);
            	 spectacle.setConf(conf);
                 list.add(spectacle);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
