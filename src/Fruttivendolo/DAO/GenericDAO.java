package Fruttivendolo.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;

public class GenericDAO{

    public static Connection conn = null;
    private static String URL_DB_HEADER = "jdbc:sqlite:";
    private static String dbName = null;


    public static void setDbName(String dbName){
        GenericDAO.dbName = dbName;
    }


    public static void connect() throws SQLException, ClassNotFoundException{
    Class.forName("org.sqlite.JDBC");
        GenericDAO.conn = DriverManager.getConnection(GenericDAO.URL_DB_HEADER+GenericDAO.dbName);
    }
    public static int create(Object o) throws SQLException, ClassNotFoundException{return -1;}
    public static Object read(int id) throws SQLException, ClassNotFoundException {return null;}
    public static ArrayList<Object> readAll() throws SQLException, ClassNotFoundException {return null;}
    public static boolean update(Object o)throws SQLException, ClassNotFoundException{return true;}
    public static boolean delete(Object o)throws SQLException, ClassNotFoundException{return true;}
}
