package Database;

import java.sql.*;

public class Connect
{
    private Connection conn = null;
    private String userName = "root";
    private String password = "sollar69";
    private String url = "jdbc:mysql://localhost/dunguinha";
    
    public Connect()
    {}
    
    public Connect(String user, String pass, String url)
    {
    	this.userName = user;
    	this.password = pass;
    	this.url = url;
    }
    
    public void open()
    {
        try
        {
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Conexão com o BD estabelecida!");
        }
        catch (Exception e)
        {
            System.err.println ("Não foi possível estabelecer conexão com o BD");
        }
    }

    public void close()
    {
        if (conn != null)
        {
            try
            {
                conn.close ();
                System.out.println ("Conexão finalizada");
            }
            catch (Exception e) { /* ignore close errors */ }
        }
    }
}