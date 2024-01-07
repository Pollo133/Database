package Fruttivendolo;
import Fruttivendolo.Dipendente;
import Fruttivendolo.Frutto;

import java.util.ArrayList;

public class Negozio {
    private int id = 0;
    private String nome = null;
    private String sede = null;
    private ArrayList<Dipendente> dipendenti = null;
    private ArrayList<Frutto> frutti = null;


    public Negozio(int idNegozio, String nome, String sede, ArrayList<Frutto> frutti, ArrayList<Dipendente> dipendenti) {
        this.id = idNegozio;
        this.nome = nome;
        this.sede = sede;
        this.frutti = frutti;
        this.dipendenti = dipendenti;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSede() {
        return sede;
    }

    public ArrayList<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public ArrayList<Frutto> getFrutti() {
        return frutti;
    }

    public void setId(int idNegozio) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public void setDipendenti(ArrayList<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    public void setFrutti(ArrayList<Frutto> frutti) {
        this.frutti = frutti;
    }

    @Override
    public String toString() {
        return "Negozio{" +
                "idNegozio=" + id +
                ", nome='" + nome + '\'' +
                ", sede='" + sede + '\'' +
                ", dipendenti='" + dipendenti + '\'' +
                '}';
    }

    public String toLine(){return id+";"+nome+";"+sede+"\n";}

}

