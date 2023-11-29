package Fruttivendolo.DAO;
import Fruttivendolo.Frutto;
import Fruttivendolo.Stagionalita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FruttoDAO extends GenericDAO{

    public static int create(Frutto frutto) throws SQLException{

        //id,nome,stagione,costoKg
        //INSERT INTO frutti VALUES(NULL,'mela','ESTIVA',100);
        String sql = "INSERT INTO frutti VALUES(NULL,'"
                +frutto.getNome()+"' , '"
                +frutto.getStagionalita().toString()+"' , "
                +frutto.getCostoKg()+");";

        GenericDAO.connect();
        Statement stm = conn.createStatement();
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

    public static Frutto read(int id) throws SQLException{
        //id,nome,stagione,costoKg
        String sql = "SELECT id, nome, stagione, costoKg FROM frutti WHERE id = "+id+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);

        if(rs!=null){
            rs.next();
            Frutto frutto = new Frutto(rs.getInt("id"),rs.getString("nome"),
                    Stagionalita.valueOf(rs.getString("stagione")),
                    rs.getInt("costoKg"));
            return frutto;
        }

        return null;
    }

    public static ArrayList<Object> readAll() throws SQLException{

        ArrayList<Object> frutti = new ArrayList<Object>();
        String sql = "SELECT id, nome, stagione, costoKg FROM frutti;";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();

        ResultSet rs = stm.executeQuery(sql);



        if(rs!=null){
            while(rs.next()){
                frutti.add(new Frutto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        Stagionalita.valueOf(rs.getString("stagione")),
                        rs.getInt("costoKg")
                ));
            }

            conn.close();
            return frutti;
        }

        conn.close();
        return null;}

    public static boolean update(Frutto frutto)throws SQLException{

        String sql = "UPDATE frutti SET nome = '"+frutto.getNome()
                +"' ,stagionalita = '"+frutto.getStagionalita().toString()
                +"' ,costoKg = "+frutto.getCostoKg()+" WHERE id = "+frutto.getId()+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        int rc = stm.executeUpdate(sql);

        return rc!=1;
    }

    public static boolean delete(Frutto frutto)throws SQLException{

        String sql = "DELETE FROM frutti WHERE id = "+frutto.getId()+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        int rc = stm.executeUpdate(sql);

        return rc!=1;
    }
}
