package Fruttivendolo.DAO;

import Fruttivendolo.Dipendente;
import Fruttivendolo.Frutto;
import Fruttivendolo.Negozio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NegozioDAO {
    public static int create(Negozio negozio) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO negozi VALUES(NULL,'"
                +negozio.getNome()+"' , '"
                +negozio.getSede()+"');";
        System.out.println(sql);

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        //executeUpdate per INSERT-DELETE-UPDATE sulle tuple della tabella
        stm.executeUpdate(sql);

        //seleziono ultimo id autogenerato da db
        ResultSet rs = stm.executeQuery("select last_insert_rowid() as id from negozi;");
        rs.next();
        int lastId = rs.getInt("id");

        //setto id del frutto
        negozio.setId(lastId);

        //chiudo connessione
        GenericDAO.conn.close();

        return lastId;
    }



    public static Negozio read(int id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT idNegozio, nome, sede FROM negozi WHERE idNegozio = "+id+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        ArrayList<Frutto> frutti = FruttoDAO.readAllNegozio(id);
        ArrayList<Dipendente> dipendenti = DipendenteDAO.readAllNegozio(id);

        if(rs!=null){
            rs.next();
            Negozio negozio = new Negozio(
                    rs.getInt("idNegozio"),
                    rs.getString("nome"),
                    rs.getString("sede"),
                    frutti,
                    dipendenti);
            GenericDAO.conn.close();
            return negozio;
        }
        GenericDAO.conn.close();
        return null;}

    public static ArrayList<Object> readAll() throws SQLException, ClassNotFoundException {

        ArrayList<Object> negozi = new ArrayList<Object>();

        String sql = "SELECT idNegozio, nome, sede FROM negozi;";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        while(rs.next()){
            negozi.add(new Negozio(
                    rs.getInt("idNegozio"),
                    rs.getString("nome"),
                    rs.getString("sede"),
                    FruttoDAO.readAllNegozio(rs.getInt("idNegozio")),
                    DipendenteDAO.readAllNegozio(rs.getInt("idNegozio"))
            ));
        }
        GenericDAO.conn.close();
        return negozi;
    }

    public static ArrayList<String> readAllIdNegozio() throws SQLException, ClassNotFoundException {

        ArrayList<String> idNegozi = new ArrayList<String>();

        String sql = "SELECT idNegozio FROM negozi;";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        while(rs.next()){
            idNegozi.add(rs.getInt("idNegozio")+"");
        }

        GenericDAO.conn.close();
        return idNegozi;
    }

    public static boolean update(Negozio negozio) throws SQLException, ClassNotFoundException {


        String sql = "UPDATE negozi SET "
                +"nome = '"+negozio.getNome()
                +"' , sede = '"+negozio.getSede()
                +"' WHERE idNegozio = "+negozio.getId()+";";

        System.out.println(sql);
        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        int rc = stm.executeUpdate(sql);

        GenericDAO.conn.close();
        return rc!=1;
    }

    public static boolean delete(Dipendente dipendente) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM dipendenti WHERE idDipendente = "+dipendente.getId()+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        int rc = stm.executeUpdate(sql);

        GenericDAO.conn.close();
        return rc!=1;
    }
}