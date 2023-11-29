package Fruttivendolo;

public class Dipendente {
    private int idDipendente = 0;
    private String nome = null;
    private String cognome = null;
    private String cellulare = null;
    private int idNegozio = 0;

    public Dipendente(int idDipendente,String nome, String cognome, String cellulare, int idNegozio) {
        this.idDipendente = idDipendente;
        this.nome = nome;
        this.cognome = cognome;
        this.cellulare = cellulare;
        this.idNegozio = idNegozio;
    }

    public int getIdDipendente() {
        return idDipendente;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCellulare() {
        return cellulare;
    }

    public int getIdNegozio() {
        return idNegozio;

    }

    public void setIdDipendente(int idDipendente) {
        this.idDipendente = idDipendente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "idDipendente=" + idDipendente +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cellulare='" + cellulare + '\'' +
                ", idNegozio=" + idNegozio +
                '}';
    }
}
