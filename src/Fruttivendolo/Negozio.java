package Fruttivendolo;
import java.util.ArrayList;

public class Negozio {
    private int idNegozio = 0;
    private String nome = null;
    private String sede = null;
    private ArrayList<Dipendente> dipendenti = null;
    private ArrayList<Frutto> frutti = null;

    public Negozio(int idNegozio, String nome, String sede, ArrayList<Dipendente> dipendenti, ArrayList<Frutto> frutti){
        this.idNegozio = idNegozio;
        this.nome = nome;
        this.sede = sede;
        this.dipendenti = dipendenti;
        this.frutti = frutti;
    }

    public int getIdNegozio() {
        return idNegozio;
    }

    public String getNome() {
        return nome;
    }

    public String getSede() {
        return sede;
    }

    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        return "Negozio{" +
                "idNegozio=" + idNegozio +
                ", nome='" + nome + '\'' +
                ", sede='" + sede + '\'' +
                ", dipendenti='" + dipendenti + '\'' +
                '}';
    }

    public String toLine(){return idNegozio+";"+nome+";"+sede+"\n";}
}

