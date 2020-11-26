package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Reservation;

public class ReservationDAO extends DAO<Reservation> {
	
	public ReservationDAO(Connection conn){
        super(conn);
    }

	@Override
    public boolean create(Reservation obj) {
        int id = 0;
        try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Reservation(acompte, solde, statut, prix, res_orga_k) VALUES (?,?,?,?,?)");
            state.setDouble(1, obj.getAcompte());
            state.setDouble(2, obj.getSolde());
            state.setString(3, obj.getStatut());
            state.setDouble(4, obj.getPrix());
            state.setInt(5, obj.getIdOrganisateur());
            state.execute();
            ResultSet rs = state.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getInt(1);
                obj.setIdReservation(id);
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
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reservation find(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
