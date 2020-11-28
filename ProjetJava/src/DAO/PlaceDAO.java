package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import POJO.Place;

public class PlaceDAO extends DAO<Place> {
	public PlaceDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Place obj) {
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Place(numPlace, prix) VALUES (?,?)");
            state.setInt(1, obj.getNumPlace());
            state.setDouble(2, obj.getPrix());
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
	public boolean delete(Place obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Place obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Place find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Place> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
