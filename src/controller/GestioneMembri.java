package controller;


import modello.Membro;
import persistence.GestoreFileMembri;

import java.util.List;

public class GestioneMembri {

    private List<Membro> membri; 
    private GestoreFileMembri gestoreFile;

    public GestioneMembri() {
        this.gestoreFile = new GestoreFileMembri();
        this.membri = gestoreFile.caricaMembri(); 
        System.out.println("Caricati " + membri.size() + " membri dal file.");
    }

    public void aggiungiMembro(String nome, String cognome, String ruolo) {
        Membro nuovoMembro = new Membro(nome, cognome, ruolo);
        this.membri.add(nuovoMembro);
        System.out.println("Nuovo membro aggiunto: " + nome + " " + cognome);
    }

    public List<Membro> getMembri() {
        return this.membri;
    }

    public void salvaDatiSuFile() {
        gestoreFile.salvaMembri(this.membri);
        System.out.println("Dati dei membri salvati correttamente su file.");
    }
}
