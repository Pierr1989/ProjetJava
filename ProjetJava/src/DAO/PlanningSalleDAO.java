package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Client;
import POJO.PlanningSalle;

public class PlanningSalleDAO extends DAO<PlanningSalle> {
	public int idReservation;
	public PlanningSalleDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(PlanningSalle obj) {
		try
        {
			    
			
            PreparedStatement state = connect.prepareStatement("INSERT INTO PlanningSalle(idPlanningSalle, dateDebutR, dateFinR, plan_gest_k, plan_reser_k, reserve) VALUES (?,?,?,?,?,?)");
            state.setInt(1, obj.getId());
            state.setDate(2, obj.getDateDebutR());
            state.setDate(3, obj.getDateFinR());
            state.setInt(4,  obj.getIdGestionnaire());
            state.setInt(5,  obj.getIdReservation());
            state.setBoolean(6, obj.getReserve());
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
    public boolean delete(PlanningSalle obj){
        try
        {
            PreparedStatement state = connect.prepareStatement("DELETE FROM PlanningSalle WHERE idPlanningSalle = ?");
            state.setInt(1, obj.getId());
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
	public boolean update(PlanningSalle obj) {
		try {			
			PreparedStatement state = connect.prepareStatement("UPDATE PlanningSalle SET dateDebutR =?, dateFinR =?, plan_gest_k =?, plan_reser_k =?, reserve =? WHERE idPlanningSalle = " + obj.getId());
            state.setDate(1, obj.getDateDebutR());
            state.setDate(2, obj.getDateFinR());
            state.setInt(3,  obj.getIdGestionnaire());
            state.setInt(4,  obj.getIdReservation());
            state.setBoolean(5, obj.getReserve());
		    state.executeUpdate();
		    return true;
		}	      
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
		return false;
	}

	@Override
	public PlanningSalle find(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
    public List<PlanningSalle> getAll() {
        List<PlanningSalle> list = new LinkedList<PlanningSalle>();
         try {
             String sql ="SELECT * FROM PlanningSalle";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 PlanningSalle planningSalle = new PlanningSalle();
            	 planningSalle.setId(result.getInt("idPlanningSalle"));
            	 planningSalle.setIdGestionnaire(result.getInt("plan_gest_k"));
            	 planningSalle.setIdReservation(result.getInt("plan_reser_k"));
            	 planningSalle.setDateDebutR(result.getDate("dateDebutR"));
            	 planningSalle.setDateFinR(result.getDate("dateFinR"));
            	 planningSalle.setReserve(result.getBoolean("reserve"));
                 list.add(planningSalle);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}
