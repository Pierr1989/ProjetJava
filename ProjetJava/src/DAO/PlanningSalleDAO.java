package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Gestionnaire;
import POJO.PlanningSalle;
import POJO.Reservation;

public class PlanningSalleDAO extends DAO<PlanningSalle> {
	public int idReservation;
	public PlanningSalleDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(PlanningSalle obj) {
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO PlanningSalle(dateDebutR, dateFinR, plan_gest_k, plan_reser_k, reserve) VALUES (?,?,?,?,?)");
            state.setString(1, obj.getDateDebutR());
            state.setString(2, obj.getDateFinR());
            state.setInt(3,  obj.getGestionnaire().getId());
            state.setNull(4, java.sql.Types.INTEGER);
            state.setBoolean(5, obj.getReserve());
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
            state.setInt(1, obj.getIdPlanning());
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
			PreparedStatement state = connect.prepareStatement("UPDATE PlanningSalle SET dateDebutR =?, dateFinR =?, plan_gest_k =?, plan_reser_k =?, reserve =? WHERE idPlanningSalle = " + obj.getIdPlanning());
            state.setString(1, obj.getDateDebutR());
            state.setString(2, obj.getDateFinR());
            state.setInt(3,  obj.getGestionnaire().getId());
            state.setInt(4,  obj.getReservation().getIdReservation());
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
		PlanningSalle plan = null;
		Gestionnaire gest = null;
		Reservation res = null;
		GestionnaireDAO gestDAO = new GestionnaireDAO(BosquetConnection.getInstance());
		ReservationDAO resDAO = new ReservationDAO(BosquetConnection.getInstance());
        try{
        	String sql ="SELECT * FROM PlanningSalle WHERE idPlanningSalle = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first()) {
            	gest = gestDAO.find(result.getInt("plan_gest_k"));
            	res = resDAO.find(result.getInt("plan_reser_k"));
            	plan = new PlanningSalle(id, result.getString("dateDebutR"), result.getString("dateFinR"), gest, res, result.getBoolean("reserve"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return plan;
	}

	@Override
    public List<PlanningSalle> getAll() {
        List<PlanningSalle> list = new LinkedList<PlanningSalle>();
        Reservation res = new Reservation();
        Gestionnaire gest = new Gestionnaire();
        ReservationDAO resDAO = new ReservationDAO(BosquetConnection.getInstance());
        GestionnaireDAO gestDAO = new GestionnaireDAO(BosquetConnection.getInstance());
         try {
             String sql ="SELECT * FROM PlanningSalle";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 PlanningSalle planningSalle = new PlanningSalle();
            	 res = resDAO.find(result.getInt("plan_reser_k"));
            	 gest = gestDAO.find(result.getInt("plan_gest_k")) ;
            	 planningSalle.setIdPlanning(result.getInt("idPlanningSalle"));
            	 planningSalle.setGestionnaire(gest);
            	 planningSalle.setReservation(res);
            	 planningSalle.setDateDebutR(result.getString("dateDebutR"));
            	 planningSalle.setDateFinR(result.getString("dateFinR"));
            	 planningSalle.setReserve(result.getBoolean("reserve"));
                 list.add(planningSalle);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
	
}
