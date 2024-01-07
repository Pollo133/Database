package Fruttivendolo.DAO;

import Fruttivendolo.DAO.GenericDAO;
import Fruttivendolo.Frutto;
import Fruttivendolo.Stagionalita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FruttoDAO extends GenericDAO {

    public static int create(Frutto frutto) throws SQLException, ClassNotFoundException {

        GenericDAO.connect();
        //id,nome,stagionalita,costoKg
        //INSERT INTO frutti VALUES(NULL,'mela','ESTIVA',100);
        String sql = "INSERT INTO frutti VALUES(NULL,'"
                +frutto.getNome()+"' , '"
                +frutto.getStagionalita().toString()+"' , "
                +frutto.getCostoKg()+");";

        Statement stm = GenericDAO.conn.createStatement();
        //executeUpdate per INSERT-DELETE-UPDATE sulle tuple della tabella
        stm.executeUpdate(sql);

        //seleziono ultimo id autogenerato da db
        ResultSet rs = stm.executeQuery("select last_insert_rowid() as id from frutti;");
        rs.next();
        int lastId = rs.getInt("id");

        //setto id del frutto
        frutto.setId(lastId);

        //chiudo connessione
        GenericDAO.conn.close();

        return lastId;
    }



    public static Frutto read(int id) throws SQLException, ClassNotFoundException {
        //id,nome,stagionalita,costoKg
        String sql = "SELECT id, nome, stagionalita, costoKg FROM frutti WHERE id = "+id+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        if(rs!=null){
            rs.next();
            Frutto frutto = new Frutto(rs.getInt("id"),rs.getString("nome"),
                    Stagionalita.valueOf(rs.getString("stagionalita")),
                    rs.getInt("costoKg"));
            return frutto;
        }

        return null;}

    public static ArrayList<Object> readAll() throws SQLException, ClassNotFoundException {

        ArrayList<Object> frutti = new ArrayList<Object>();
        String sql = "SELECT id, nome, stagionalita, costoKg FROM frutti;";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);



        if(rs!=null){
            while(rs.next()){
                Frutto frutto = new Frutto();
                frutto.setId(rs.getInt("id"));
                frutto.setNome(rs.getString("nome"));
                frutto.setCostoKg(rs.getInt("costoKg"));
                frutto.setStagionalita(Stagionalita.valueOf(rs.getString("stagionalita")));

                frutti.add(frutto);
            }

            conn.close();
            return frutti;
        }

        conn.close();
        return null;}

    public static boolean update(Frutto frutto) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE frutti SET nome = '"+frutto.getNome()
                +"' ,stagionalita = '"+frutto.getStagionalita().toString()
                +"' ,costoKg = "+frutto.getCostoKg()+" WHERE id = "+ Frutto.getId()+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        int rc = stm.executeUpdate(sql);

        return rc!=1;
    }

    public static boolean delete(Frutto frutto) throws SQLException, ClassNotFoundException {
        int id = Frutto.getId();
        String sql = "DELETE FROM frutti WHERE id = "+id+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        int rc = stm.executeUpdate(sql);

        return rc!=1;
    }

    public static ArrayList<Frutto> readAllNegozio(int idNegozio) throws SQLException, ClassNotFoundException {

        ArrayList<Frutto> frutti = new ArrayList<>();

        String sql = "SELECT frutti.id, frutti.nome, frutti.stagionalita, frutti.costoKg " +
                "FROM frutti, fruttiNegozi " +
                "WHERE fruttiNegozi.idNegozio = "+idNegozio +
                " AND frutti.id = fruttiNegozi.idFrutto;";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);



        while(rs.next()){
            frutti.add(new Frutto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    Stagionalita.valueOf(rs.getString("stagionalita")),
                    rs.getInt("costoKg")
            ));

        }

        GenericDAO.conn.close();
        return frutti;

    }

    public static void deleteAll() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM frutti;";
        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        stm.executeUpdate(sql);

        GenericDAO.conn.close();
    }
}
