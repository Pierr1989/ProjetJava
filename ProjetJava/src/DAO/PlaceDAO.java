package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Commande;
import POJO.Place;

public class PlaceDAO extends DAO<Place> {
	public PlaceDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Place obj) {
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Place(prix, place_com_k) VALUES (?,?)");
            state.setDouble(1, obj.getPrix());
            state.setInt(2, obj.getCommande().getIdCommande());
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
    public boolean delete(Place obj){
        try
        {
            PreparedStatement state = connect.prepareStatement("DELETE FROM Place WHERE idPlace = ?");
            state.setInt(1, obj.getIdPlace());
            state.executeUpdate();
            return true;
        }

        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

	@Override
	public boolean update(Place obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Place find(int id) {
		Commande commande = null;
		Place place = null;
		CommandeDAO DAO = new CommandeDAO(BosquetConnection.getInstance());
        try{
        	String sql ="SELECT * FROM Place WHERE idPlace = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first()) {
            	commande = DAO.find(result.getInt("place_com_k"));
            	place = new Place(id, result.getDouble("prix"), commande);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return place;
	}

	@Override
    public List<Place> getAll() {
        List<Place> list = new LinkedList<Place>();
        Commande com = new Commande();
        CommandeDAO DAO = new CommandeDAO(BosquetConnection.getInstance());
         try {
             String sql ="SELECT * FROM Place";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Place place = new Place();
            	 com = DAO.find(result.getInt("place_com_k"));
            	 place.setIdPlace(result.getInt("idPlace"));
            	 place.setPrix(result.getDouble("prix"));
            	 place.setCommande(com);           	 
                 list.add(place);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
