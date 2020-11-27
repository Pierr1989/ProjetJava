package DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import POJO.Configuration;

public class ConfigurationDAO extends DAO<Configuration> {
	public int idSpectacle;
	public ConfigurationDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Configuration obj) {
		try
        {
			ResultSet result = this.connect.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT MAX(idSpectacle) FROM Spectacle");
			if(result.next())
				idSpectacle = result.getInt(1); 
			
            PreparedStatement state = connect.prepareStatement("INSERT INTO Configuration(idConfiguration, type, description, conf_spec_k) VALUES (?,?,?,?)");
            state.setInt(1, obj.getIdConfiguration());
            state.setString(2, obj.getType());
            state.setString(3, obj.getDescription());
            state.setInt(4, idSpectacle);
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
	public boolean delete(Configuration obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Configuration obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Configuration find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Configuration> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
