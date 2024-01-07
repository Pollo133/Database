package Fruttivendolo;

import java.sql.SQLException;

public class Frutto{

    private static int id = 0;
    private String nome = null;
    private Stagionalita stagionalita = Stagionalita.ANNUALE;
    private int costoKg = 0;

    public Frutto(){}

    public Frutto(int id, String nome, Stagionalita stagionalita, int costoKg){
        this.nome = nome;
        this.stagionalita = stagionalita;
        this.costoKg = costoKg;
    }

    public Frutto(String line){

        //dichiaro un vettore di stringhe
        String [] fields = line.split("[,;]");

        //assegnazione dopo parsificazione
        this.id = Integer.parseInt(fields[0]);
        this.nome = fields[1];
        this.stagionalita = Stagionalita.valueOf(fields[2]);
        this.costoKg = Integer.parseInt(fields[3]);

    }

    public Frutto(Integer id, String nome, Stagionalita stagionalita, int costo) {
    }

    //setty
    //decoratori per scrivere e leggere da xml


    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setStagionalita(Stagionalita stagionalita){
        this.stagionalita = stagionalita;
    }

    public void setCostoKg(int costoKg){
        this.costoKg = costoKg;
    }
    //getty

    public static int getId(){
        return id;
    }

    public String getNome(){
        return this.nome;
    }
    public Stagionalita getStagionalita(){
        return this.stagionalita;
    }
    public int getCostoKg(){
        return this.costoKg;
    }

    //toString
    public String toString(){
        return "[Frutto]"
                +"\nid: "+id
                +"\nNome: "+nome
                +"\nStagionalita: "+stagionalita
                +"\nCosto/kg: "+costoKg;
    }

    //toLine
    public String toLine(){
        return id+";"+nome+";"+stagionalita+";"+costoKg;
    }

    //toRow () per formati ods
    public String [] toRow(){

        //vettore di stringhe
        String [] ret = new String[4];

        ret[0] = this.id+"";
        ret[1] = this.nome;
        ret[2] = this.stagionalita.toString();
        ret[3] = this.costoKg+"";

        return ret;
    }

    public static void main(String [] args){

        try{
            Fruttivendolo.DAO.FruttoDAO.update(
                    new Frutto(10,"mela di Rocca",Stagionalita.INVERNALE,0));


        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
