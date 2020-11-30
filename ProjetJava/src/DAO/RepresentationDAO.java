package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
            PreparedStatement state = connect.prepareStatement("INSERT INTO Representation(dateRepresentation, heureDebut, heureFin, rep_place_k, rep_spec_k, numeroRepresentation) VALUES (?,?,?,?,?,?)");
            state.setDate(1, obj.getDate());
            state.setDate(2, obj.getHeureDebut());
            state.setDate(3, obj.getHeureFin());
            state.setInt(4, obj.getIdPlace());
            state.setInt(5, obj.getIdSpectacle());
            state.setInt(6,  obj.getNumeroRepresentation());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<Representation> getAll() {
        List<Representation> list = new LinkedList<Representation>();
         try {
             String sql ="SELECT * FROM Representation";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Representation representation = new Representation();
            	 representation.setIdRepresentation(result.getInt("idRepresentation"));
            	 representation.setDate(result.getDate("dateRepresentation"));
            	 representation.setHeureDebut(result.getDate("heureDebut"));
            	 representation.setHeureFin(result.getDate("heureFin"));
            	 representation.setIdPlace(result.getInt("rep_place_k"));
            	 representation.setIdSpectacle(result.getInt("rep_spec_k"));
            	 representation.setNumeroRepresentation(result.getInt("numeroRepresentation"));
                 list.add(representation);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
