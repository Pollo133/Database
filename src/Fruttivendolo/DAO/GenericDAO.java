package Fruttivendolo.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;

public class GenericDAO{
    protected static Connection conn = null;
    private static final String URL_DB = "jdbc:sqlite:fruttivendiki.db";

    public static void connect() throws SQLException {
        GenericDAO.conn = DriverManager.getConnection(GenericDAO.URL_DB);
    }

    public static int create(Object o)throws SQLException {return -1;}
    public static Object read(int id) throws SQLException{return null;}
    public static ArrayList<Object> readAll() throws SQLException{return null;}
    public static boolean update(Object o) throws SQLException{return true; }
    public static boolean delete(Object o) throws SQLException{return true;}
}
