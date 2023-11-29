package Fruttivendolo;

import Fruttivendolo.DAO.FruttoDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Frutto{

    private int id = 0;
    private String nome = null;
    private Stagionalita stagionalita = Stagionalita.ANNUALE;
    private int costoKg = 0;

    public Frutto(){}

    public Frutto(int id, String nome, Stagionalita stagionalita, int costoKg){
        this.id = id;
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

    public int getId(){
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
            FruttoDAO.update(
                    new Frutto(10,"mela di Rocca",Stagionalita.INVERNALE,0));


        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
