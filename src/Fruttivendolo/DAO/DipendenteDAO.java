package Fruttivendolo.DAO;
import Fruttivendolo.Dipendente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DipendenteDAO extends GenericDAO {
    public static int create(Dipendente dipendente)throws SQLException {
        String sql = "INSERT INTO frutti VALUES(NULL,'"
                +dipendente.getIdDipendente()+"' , '"
                +dipendente.getNome()+"' , '"
                +dipendente.getCellulare()+"' , "
                +dipendente.getIdNegozio()+");";

        GenericDAO.connect();
        Statement stm = conn.createStatement();
        stm.executeUpdate(sql);

        //seleziono ultimo id autogenerato da db
        ResultSet rs = stm.executeQuery("select last_insert_rowid() as id from frutti;");
        rs.next();
        int lastId = rs.getInt("id");

        //setto id del frutto
        dipendente.setIdDipendente(lastId);

        //chiudo connessione
        GenericDAO.conn.close();

        return lastId;

    }
    public static Dipendente read(int id) throws SQLException{
        //id,nome,stagione,costoKg
        String sql = "SELECT idDipendente, nome, cognome, cellulare, idNegozio FROM frutti WHERE id = "+id+";";

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
                    rs.getInt("idNegozio"));

            return dipendente ;
        }

        return null;
    }
    public static ArrayList<Object> readAll() throws SQLException{

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
                        rs.getInt("idNegozio"))
                );
            }

            conn.close();
            return dipendenti;
        }

        conn.close();
        return null;

    }
    public static boolean update(Dipendente dipendente) throws SQLException{

        String sql = "UPDATE dipendenti SET nome = '" + dipendente.getNome()+
                "' ,cognome '" + dipendente.getCognome()+
                "' ,cellulare '" + dipendente.getCellulare()+
                "' ,idNegozio '" + dipendente.getIdNegozio()+
                "WHERE id " + dipendente.getIdDipendente()+";";


        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        int rc = stm.executeUpdate(sql);

        return rc!=1;
    }
    public static boolean delete(Dipendente dipendente) throws SQLException{

        String sql = "DELETE FROM dipendenti WHERE id = "+ dipendente.getIdDipendente()+";";

        GenericDAO.connect();
        Statement stm = GenericDAO.conn.createStatement();
        int rc = stm.executeUpdate(sql);

        return rc!=1;
    }
}
