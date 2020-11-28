package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import POJO.Categorie;

public class CategorieDAO extends DAO<Categorie> {
	public int idConfiguration;
	public CategorieDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Categorie obj) {
		try
        {
			
			ResultSet result = this.connect.createStatement(
        			ResultSet.TYPE_SCROLL_INSENSITIVE,
        		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT MAX(idConfiguration) FROM Configuration");
			if(result.next())
            	idConfiguration = result.getInt(1);       	       	
   
			
            PreparedStatement state = connect.prepareStatement("INSERT INTO Categorie(idCategorie, type, prix, nbrPlaceDispo, nbrPlaceMax, cat_conf_k) VALUES (?,?,?,?,?,?)");
            state.setInt(1, obj.getIdCategorie());
            state.setString(2, obj.getType());
            state.setDouble(3, obj.getPrix());
            state.setInt(4, obj.getNbrPlaceDispo());
            state.setInt(5, obj.getNbrPlaceMax());
            state.setInt(6, idConfiguration);
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
	public boolean delete(Categorie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Categorie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Categorie find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
