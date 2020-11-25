package DAO;

import java.sql.*;
import java.util.List;

// DAO générique avec methodes CRUD
public abstract class DAO<T> {
    protected Connection connect = null;
    protected CallableStatement callStm = null;

    public DAO(Connection conn){
        this.connect = conn;
    }
    
    protected void close(Statement stm)
	{
		this.close(stm, null);
	}
	
	protected void close(Statement stm, ResultSet rs)
	{

		try { if (rs != null) rs.close(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		try { if (stm != null) stm.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}

    public abstract boolean create(T obj);

    public abstract boolean delete(T obj);

    public abstract boolean update(T obj);

    public abstract T find(int id);
    
    public abstract List<T> getAll();
    
    public boolean result() throws SQLException
	{
		boolean result = callStm.getInt(1) > 0 ? true : false;
		return result;
	}
}
