package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Organisateur;
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
            state.setInt(5, obj.getOrganisateur().getId());
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
		try
        {
            PreparedStatement state = connect.prepareStatement("DELETE FROM Reservation WHERE idReservation = ?");
            state.setInt(1, obj.getIdReservation());
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
	public boolean update(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reservation find(int id) {
		Reservation reservation = null;
		Organisateur orga = null;
		OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
        try{
        	String sql ="SELECT * FROM Reservation WHERE idReservation = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first()) {
            	orga = DAO.find(result.getInt("res_orga_k"));
            	reservation = new Reservation(id, orga, result.getDouble("acompte"), result.getDouble("solde"), result.getString("statut"), result.getDouble("prix"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return reservation;
	}

	@Override
    public List<Reservation> getAll() {
        List<Reservation> list = new LinkedList<Reservation>();
        Organisateur orga = new Organisateur();
        OrganisateurDAO DAO = new OrganisateurDAO(BosquetConnection.getInstance());
         try {
             String sql ="SELECT * FROM Reservation";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
                 Reservation res = new Reservation();
                 orga = DAO.find(result.getInt("res_orga_k"));
                 res.setIdReservation(result.getInt("idReservation"));
                 res.setAcompte(result.getDouble("acompte"));
                 res.setSolde(result.getDouble("solde"));
                 res.setStatut(result.getString("statut"));
                 res.setPrix(result.getDouble("prix"));
                 res.setOrganisateur(orga);
                 list.add(res);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }

         return list;
    }
}
