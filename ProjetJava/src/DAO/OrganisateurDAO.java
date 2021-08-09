package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Organisateur;

// Un organisateur va créer des artistes
public class OrganisateurDAO extends DAO<Organisateur>{
	private PersonneDAO DAO = new PersonneDAO(BosquetConnection.getInstance());
	
	public OrganisateurDAO(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Organisateur obj) {
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Organisateur(idOrganisateur, telephone) VALUES (?,?)");
            state.setLong(1, obj.getId());
            state.setString(2, obj.getTelephone());
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
	public boolean delete(Organisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Organisateur obj) {
		if(DAO.update(obj)) {
            try {
                PreparedStatement state = connect.prepareStatement("UPDATE Organisateur SET telephone =? WHERE idOrganisateur = " + obj.getId());
                state.setString(1, obj.getTelephone());
                state.executeUpdate();
                return true;
             }
             catch (SQLException e) {
                 e.printStackTrace();
            }
            return false;
        }
        else
            return false;
	}

	@Override
    public Organisateur find(int id){
		Organisateur organisateur = null;
        try{
        	String sql ="SELECT * FROM Personne INNER JOIN Organisateur ON Organisateur.idOrganisateur = Personne.id WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	organisateur = new Organisateur(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("password"), result.getString("email"), result.getString("role"), result.getString("telephone"));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return organisateur;
    }

	@Override
	public List<Organisateur> getAll() {
		List<Organisateur> list = new LinkedList<Organisateur>();
        try {
            String sql ="SELECT * FROM Personne INNER JOIN Organisateur ON Organisateur.idOrganisateur = Personne.id";
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            while(result.next()) {
            	Organisateur organisateur = new Organisateur();
            	organisateur.setId(result.getInt("idOrganisateur"));
            	organisateur.setNom(result.getString("nom"));
            	organisateur.setPrenom(result.getString("prenom"));
            	organisateur.setAdresse(result.getString("adresse"));
            	organisateur.setPassword(result.getString("password"));
            	organisateur.setEmail(result.getString("email"));
            	organisateur.setRole(result.getString("role"));
            	organisateur.setTelephone(result.getString("telephone"));
                list.add(organisateur);
           }
        } catch (SQLException e) {
           e.printStackTrace();
       }
        return list;
	}
}
