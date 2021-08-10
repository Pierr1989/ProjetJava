package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import POJO.Personne;

public class PersonneDAO extends DAO<Personne>{

    public PersonneDAO(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(Personne obj) { // En cas d'ajout ou de modification de donnée ==> executeUpdate
        try
        {
            //Statement state = connect.createStatement(); // Solution via google ==> Qui ne marche pas car problème avec les valeurs ==> reçoit une erreur car il confond les champs
            //state.executeUpdate("INSERT INTO Personne(Num_pers, Nom, Prenom, Sexe, Age, Pays) VALUES(id, nom, prenom, sexe, age, pays)"); // Dans values les données en paramètre/
            PreparedStatement state = connect.prepareStatement("INSERT INTO Personne(nom, prenom, adresse, password, email, role) VALUES (?,?,?,?,?,?)");
            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setString(3, obj.getAdresse());
            state.setString(4, obj.getPassword());
            state.setString(5, obj.getEmail());
            state.setString(6, obj.getRole());
            state.execute();
            ResultSet rs = state.getGeneratedKeys();
            if(rs.next()) {
                int id = rs.getInt(1);
                obj.setId(id);
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
    public boolean delete(Personne obj){
    	try
        {
            PreparedStatement state = connect.prepareStatement("DELETE FROM Personne WHERE id = ?");
            state.setInt(1, obj.getId());
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
    public boolean update(Personne obj){
    	try {
            PreparedStatement state = connect.prepareStatement("UPDATE Personne SET nom =?, prenom =?, adresse =?, password =?, email =?, role =? WHERE id = " + obj.getId());
            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setString(3, obj.getAdresse());
            state.setString(4, obj.getPassword());
            state.setString(5, obj.getEmail());
            state.setString(6, obj.getRole());
            state.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Personne find(int id){
    	return null;
    }

	@Override
	public List<Personne> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
