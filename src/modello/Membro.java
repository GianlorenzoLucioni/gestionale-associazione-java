
package modello;

public class Membro {
    private String nome;
    private String cognome;
    private String ruolo;

    public Membro(String nome, String cognome, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getRuolo() { return ruolo; }

    public String toFileString() {
        return nome + ";" + cognome + ";" + ruolo;
    }
}