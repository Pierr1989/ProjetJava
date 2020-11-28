package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import POJO.Commande;

public class CommandeDAO extends DAO<Commande> {
	public CommandeDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Commande obj) {
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Commande(idCommande, modeDePayement, modeDeLivraison, cout, com_cli_k) VALUES (?,?,?,?)");
            state.setInt(1, obj.getIdCommande());
            state.setString(2, obj.getModeDePayement());
            state.setString(3, obj.getModeDeLivraison());
            state.setDouble(4, obj.getCout());
            state.setInt(1, obj.getIdClient());
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
	public boolean delete(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Commande find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
