package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Place;
import POJO.Representation;
import POJO.Spectacle;

public class RepresentationDAO extends DAO<Representation> {
	public RepresentationDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Representation obj) {
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Representation(dateRepresentation, heureDebut, heureFin, rep_place_k, rep_spec_k) VALUES (?,?,?,?,?)");
            state.setString(1, obj.getDate());
            state.setString(2, obj.getHeureDebut());
            state.setString(3, obj.getHeureFin());
            state.setNull(4, java.sql.Types.INTEGER);
            state.setInt(5, obj.getSpectacle().getIdSpectacle());
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
	public boolean delete(Representation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Representation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Representation find(int id) {
		Representation representation = null;
		Place place = null;
		Spectacle spec = null;
		PlaceDAO placeDAO = new PlaceDAO(BosquetConnection.getInstance());
		SpectacleDAO specDAO = new SpectacleDAO(BosquetConnection.getInstance());
        try{
        	String sql ="SELECT * FROM Representation WHERE idRepresentation = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first()) {
            	place = placeDAO.find(result.getInt("rep_place_k"));
            	spec = specDAO.find(result.getInt("rep_spec_k"));
            	representation = new Representation(id, result.getString("dateRepresentation"), result.getString("heureDebut"), result.getString("heureFin"), spec, place);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return representation;
	}

	@Override
    public List<Representation> getAll() {
        List<Representation> list = new LinkedList<Representation>();
        Place place = new Place();
        Spectacle spec = new Spectacle();
        PlaceDAO placeDAO = new PlaceDAO(BosquetConnection.getInstance());
        SpectacleDAO specDAO = new SpectacleDAO(BosquetConnection.getInstance());
         try {
             String sql ="SELECT * FROM Representation";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Representation representation = new Representation();
            	 place = placeDAO.find(result.getInt("rep_place_k"));
            	 spec = specDAO.find(result.getInt("rep_spec_k"));
            	 representation.setIdRepresentation(result.getInt("idRepresentation"));
            	 representation.setDate(result.getString("dateRepresentation"));
            	 representation.setHeureDebut(result.getString("heureDebut"));
            	 representation.setHeureFin(result.getString("heureFin"));
            	 representation.setPlace(place);
            	 representation.setSpectacle(spec);
                 list.add(representation);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
