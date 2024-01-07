package Fruttivendolo.DAO;
import Fruttivendolo.Dipendente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DipendenteDAO {

    public static int create(Dipendente dipendente) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO dipendenti VALUES(NULL,'"
                +dipendente.getNome()+"' , '"
                +dipendente.getCognome()+"' , '"
                +dipendente.getCellulare()+"' ,"
                +dipendente.getIdNegozio()+");";
        System.out.println(sql);

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        //executeUpdate per INSERT-DELETE-UPDATE sulle tuple della tabella
        stm.executeUpdate(sql);

        //seleziono ultimo id autogenerato da db
        ResultSet rs = stm.executeQuery("select last_insert_rowid() as id from dipendenti;");
        rs.next();
        int lastId = rs.getInt("id");

        //setto id del frutto
        dipendente.setId(lastId);

        //chiudo connessione
        GenericDAO.conn.close();

        return lastId;
    }



    public static Dipendente read(int id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT idDipendente, nome, cognome, cellulare, idNegozio FROM dipendenti WHERE idDipendente = "+id+";";

        System.out.println(sql);
        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);


        if(rs!=null){
            rs.next();
            Dipendente dipendente = new Dipendente(
                    rs.getInt("idDipendente"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("cellulare"),
                    rs.getInt("IdNegozio"));
            GenericDAO.conn.close();
            return dipendente;
        }
        GenericDAO.conn.close();
        return null;}

    public static ArrayList<Object> readAll() throws SQLException, ClassNotFoundException {

        ArrayList<Object> dipendenti = new ArrayList<Object>();
        String sql = "SELECT idDipendente, nome, cognome, cellulare, idNegozio FROM dipendenti;";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);



        if(rs!=null){
            while(rs.next()){
                dipendenti.add(new Dipendente(
                        rs.getInt("idDipendente"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("cellulare"),
                        rs.getInt("idNegozio")));
            }

            GenericDAO.conn.close();
            return dipendenti;
        }

        GenericDAO.conn.close();
        return null;}

    public static boolean update(Dipendente dipendente) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE dipendenti SET "
                +"nome = '"+dipendente.getNome()
                +"' ,cognome = '"+dipendente.getCognome()
                +"' ,cellulare = '"+dipendente.getCellulare()
                +"' , idNegozio = "+dipendente.getIdNegozio()
                +" WHERE idDipendente = "+dipendente.getId()+";";

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

    public static ArrayList<Dipendente> readAllNegozio(int idNegozio) throws SQLException, ClassNotFoundException {

        ArrayList<Dipendente> dipendenti = new ArrayList<>();

        String sql = "SELECT idDipendente, nome, cognome, cellulare, idNegozio " +
                "FROM dipendenti WHERE idNegozio = "+idNegozio+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        while(rs.next()){
            dipendenti.add(new Dipendente(
                    rs.getInt("idDipendente"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("cellulare"),
                    rs.getInt("idNegozio")));
        }

        GenericDAO.conn.close();
        return dipendenti;

    }
}