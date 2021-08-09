package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Categorie;
import POJO.Configuration;

public class CategorieDAO extends DAO<Categorie> {
	public int idConfiguration;
	public CategorieDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Categorie obj) {
		try
        {
			PreparedStatement state = connect.prepareStatement("INSERT INTO Categorie(type, prix, nbrPlaceDispo, nbrPlaceMax, cat_conf_k) VALUES (?,?,?,?,?)");
            state.setString(1, obj.getType());
            state.setDouble(2, obj.getPrix());
            state.setInt(3, obj.getNbrPlaceDispo());
            state.setInt(4, obj.getNbrPlaceMax());
            state.setInt(5, obj.getConfiguration().getIdConfiguration());
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
		try {			
			PreparedStatement state = connect.prepareStatement("UPDATE Categorie SET type =?, prix =?, nbrPlaceDispo =?, nbrPlaceMax =?, cat_conf_k =? WHERE idCategorie = " + obj.getIdCategorie());
            state.setString(1, obj.getType());
            state.setDouble(2, obj.getPrix());
            state.setInt(3, obj.getNbrPlaceDispo());
            state.setInt(4, obj.getNbrPlaceMax());
            state.setInt(5, obj.getConfiguration().getIdConfiguration());
            state.execute();
		    return true;
		}	      
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
		return false;
	}
	@Override
	public Categorie find(int id) {
		Categorie categorie = null;
		Configuration conf = null;
		ConfigurationDAO DAO = new ConfigurationDAO(BosquetConnection.getInstance());
        try{
        	String sql ="SELECT * FROM Categorie WHERE idCategorie = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first()) {
            	conf = DAO.find(result.getInt("cat_conf_k"));
            	categorie = new Categorie(id, result.getString("type"), result.getDouble("prix"), result.getInt("nbrPlaceDispo"), result.getInt("nbrPlaceMax"), conf);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return categorie;
	}

	@Override
    public List<Categorie> getAll() {
        List<Categorie> list = new LinkedList<Categorie>();
        Configuration config = new Configuration();
        ConfigurationDAO DAO = new ConfigurationDAO(BosquetConnection.getInstance());
         try {
             String sql ="SELECT * FROM Categorie";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Categorie categorie = new Categorie();
            	 config = DAO.find(result.getInt("cat_conf_k"));
            	 categorie.setIdCategorie(result.getInt("idCategorie"));
            	 categorie.setType(result.getString("type"));
            	 categorie.setPrix(result.getInt("prix"));
            	 categorie.setNbrPlaceDispo(result.getInt("nbrPlaceDispo"));
            	 categorie.setNbrPlaceMax(result.getInt("nbrPlaceMax"));
            	 categorie.setConfiguration(config);
                 list.add(categorie);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
