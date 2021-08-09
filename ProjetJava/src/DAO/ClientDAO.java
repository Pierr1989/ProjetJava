package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Client;

public class ClientDAO extends DAO<Client>{
	private PersonneDAO DAO = new PersonneDAO(BosquetConnection.getInstance());
    public ClientDAO(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(Client obj) {
    	try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO Client(idClient, telephone, genre, dateDeNaissance) VALUES (?,?,?,?)");
            state.setLong(1, obj.getId());
            state.setString(2, obj.getTelephone());
            state.setString(3, obj.getGenre());
            state.setString(4, obj.getDateDeNaissance());
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
    public boolean delete(Client obj){
        return false;
    }

    @Override
    public boolean update(Client obj){
        if(DAO.update(obj)) {
            try {
                PreparedStatement state = connect.prepareStatement("UPDATE Client SET telephone =?, genre =?, dateDeNaissance =? WHERE idClient = " + obj.getId());
                state.setString(1, obj.getTelephone());
                state.setString(2, obj.getGenre());
                state.setString(3, obj.getDateDeNaissance());
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
    public Client find(int id){
    	Client client = null;
        try{
        	String sql ="SELECT * FROM Personne INNER JOIN Client ON Client.idClient = Personne.id WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	client = new Client(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("password"), result.getString("email"), result.getString("role"), result.getString("telephone"), result.getString("genre"), result.getString("dateDeNaissance"));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return client;
    }
    

    @Override
    public List<Client> getAll() {
        List<Client> list = new LinkedList<Client>();
         try {
             String sql ="SELECT * FROM Personne INNER JOIN Client ON Client.idClient = Personne.id";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
                 Client client = new Client();
                 client.setId(result.getInt("idClient"));
                 client.setNom(result.getString("nom"));
                 client.setPrenom(result.getString("prenom"));
                 client.setAdresse(result.getString("adresse"));
                 client.setPassword(result.getString("password"));
                 client.setEmail(result.getString("email"));
                 client.setRole(result.getString("role"));
                 client.setTelephone(result.getString("telephone"));
                 client.setGenre(result.getString("genre"));
                 client.setDateDeNaissance(result.getString("dateDeNaissance"));
                 
                 list.add(client);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
}

