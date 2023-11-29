package Fruttivendolo.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

class NegozioDAO extends GenericDAO {
    public static int create(Object o)throws SQLException {return -1;}
    public static Object read(int id) throws SQLException{return null;}
    public static ArrayList<Object> readAll() throws SQLException{return null;}
    public static boolean update(Object o) throws SQLException{return true; }
    public static boolean delete(Object o) throws SQLException{return true;}
}
