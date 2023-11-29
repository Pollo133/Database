package Fruttivendolo.DAO;

import Fruttivendolo.Negozio;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

class NegozioDAO extends GenericDAO {
    public static int create(Negozio negozio)throws SQLException {return -1;}
    public static Negozio read(int id) throws SQLException{return null;}
    public static ArrayList<Object> readAll() throws SQLException{return null;}
    public static boolean update(Negozio negozio) throws SQLException{return true; }
    public static boolean delete(Negozio negozio) throws SQLException{return true;}
}
