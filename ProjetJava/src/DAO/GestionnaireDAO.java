package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Gestionnaire;

// Agit comme un admin ==> Présent en dur dans le code
public class GestionnaireDAO extends DAO<Gestionnaire> {

	public GestionnaireDAO(Connection conn){
        super(conn);
    }

	@Override
    public boolean create(Gestionnaire obj) {
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Gestionnaire(idGestionnaire, telephone) VALUES (?,?)");
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
    public boolean delete(Gestionnaire obj){
        return false;
    }

	@Override
    public boolean update(Gestionnaire obj){
        return false;
    }

	@Override
    public Gestionnaire find(int id){
    	Gestionnaire ges = null;
        try{
        	String sql ="SELECT * FROM Personne INNER JOIN Gestionnaire ON Gestionnaire.idGestionnaire = Personne.id WHERE idGestionnaire = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	ges = new Gestionnaire(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("password"), result.getString("email"), result.getString("role"), result.getString("telephone"));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return ges;
    }

	@Override
	public List<Gestionnaire> getAll() {
		List<Gestionnaire> list = new LinkedList<Gestionnaire>();
        try {
            String sql ="SELECT * FROM Personne INNER JOIN Gestionnaire ON Gestionnaire.idGestionnaire = Personne.id";
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            while(result.next()) {
            	Gestionnaire gestionnaire = new Gestionnaire();
            	gestionnaire.setId(result.getInt("idGestionnaire"));
            	gestionnaire.setNom(result.getString("nom"));
            	gestionnaire.setPrenom(result.getString("prenom"));
            	gestionnaire.setAdresse(result.getString("adresse"));
            	gestionnaire.setPassword(result.getString("password"));
            	gestionnaire.setEmail(result.getString("email"));
            	gestionnaire.setRole(result.getString("role"));
            	gestionnaire.setTelephone(result.getString("telephone"));
                list.add(gestionnaire);
           }
        } catch (SQLException e) {
           e.printStackTrace();
       }
        return list;
	}
}
