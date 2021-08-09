package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Client;
import POJO.Commande;

public class CommandeDAO extends DAO<Commande> {
	public CommandeDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Commande obj) {
		int id = 0;
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Commande(modeDePayement, modeDeLivraison, total, com_cli_k) VALUES (?,?,?,?)");
            state.setString(1, obj.getModeDePayement());
            state.setString(2, obj.getModeDeLivraison());
            state.setDouble(3, obj.getTotal());
            state.setInt(4, obj.getCli().getId());
            state.execute();
            ResultSet rs = state.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getInt(1);
                obj.setIdCommande(id);
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
	public boolean delete(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Commande obj) {
		try {			
			PreparedStatement state = connect.prepareStatement("UPDATE Commande SET modeDePayement =?, modeDeLivraison =?, total =?, com_cli_k =? WHERE iDCommande = " + obj.getIdCommande());
            state.setString(1, obj.getModeDePayement());
            state.setString(2, obj.getModeDeLivraison());
            state.setDouble(3,  obj.getTotal());
            state.setInt(4,  obj.getCli().getId());
		    state.executeUpdate();
		    return true;
		}	      
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
		return false;
	}

	@Override
	public Commande find(int id) {
		Commande commande = null;
		Client cli = null;
		ClientDAO DAO = new ClientDAO(BosquetConnection.getInstance());
        try{
        	String sql ="SELECT * FROM Commande WHERE iDCommande = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first()) {
            	cli = DAO.find(result.getInt("com_cli_k"));
            	commande = new Commande(id, result.getString("modeDePayement"), result.getString("modeDeLivraison"), result.getDouble("total"), cli);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return commande;
	}

	@Override
	public List<Commande> getAll() {
		List<Commande> list = new LinkedList<>();
        Client cli = new Client();
        ClientDAO DAO = new ClientDAO(BosquetConnection.getInstance());
         try {
             String sql ="SELECT * FROM Commande";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Commande commande = new Commande();           	 
            	 cli = DAO.find(result.getInt("com_cli_k"));
            	 commande.setIdCommande(result.getInt("iDCommande"));
            	 commande.setModeDePayement(result.getString("modeDePayement"));
            	 commande.setModeDeLivraison(result.getString("modeDeLivraison"));
            	 commande.setTotal(result.getDouble("total"));
            	 commande.setCli(cli);
                 list.add(commande);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
	}
}
