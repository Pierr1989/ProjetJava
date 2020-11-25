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
    public boolean create(Personne obj) { // En cas d'ajout ou de modification de donn�e ==> executeUpdate
        try
        {
            //Statement state = connect.createStatement(); // Solution via google ==> Qui ne marche pas car probl�me avec les valeurs ==> re�oit une erreur car il confond les champs
            //state.executeUpdate("INSERT INTO Personne(Num_pers, Nom, Prenom, Sexe, Age, Pays) VALUES(id, nom, prenom, sexe, age, pays)"); // Dans values les donn�es en param�tre/
            PreparedStatement state = connect.prepareStatement("INSERT INTO Personne(nom, prenom, adresse, telephone, pseudo, password) VALUES (?,?,?,?,?,?)");
            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setString(3, obj.getAdresse());
            state.setString(4, obj.getTelephone());
            state.setString(5, obj.getPseudo());
            state.setString(6, obj.getPassword());
            state.execute();
            return true;
        }

        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
 // V�rification de la pr�sence d'un pseudo d�j� existant dans la table personne
    public boolean VerifTel(String telephone) {
        try {
            String sql ="SELECT * FROM Personne WHERE telephone ='"+telephone+"'";
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.next())
                return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    @Override
    public boolean delete(Personne obj){
        return false;
    }

    @Override
    public boolean update(Personne obj){
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
