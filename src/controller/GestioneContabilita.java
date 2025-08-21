
package controller;

import java.time.LocalDate;
import java.util.List;
import modello.MovimentoContabile;
import modello.TipoMovimento;
import persistence.EsportatoreCsv;
import persistence.GestoreFileContabilita; 

public class GestioneContabilita {

    private List<MovimentoContabile> movimenti;
    private EsportatoreCsv esportatore;
    private GestoreFileContabilita gestoreFile; 

    
    public GestioneContabilita() {
        this.esportatore = new EsportatoreCsv();
        this.gestoreFile = new GestoreFileContabilita(); 
        this.movimenti = gestoreFile.caricaMovimenti(); 
        System.out.println("Modulo di contabilità inizializzato. Caricati " + movimenti.size() + " movimenti.");
    }

    public void aggiungiMovimento(LocalDate data, String descrizione, double importo, TipoMovimento tipo, String categoria) {
        MovimentoContabile nuovoMovimento = new MovimentoContabile(data, descrizione, importo, tipo, categoria);
        this.movimenti.add(nuovoMovimento);
    }

    public List<MovimentoContabile> getMovimenti() {
        return this.movimenti;
    }
    
    public void esportaBilancioCsv() {
        String nomeFile = "Rendiconto_Festa.csv";
        System.out.println("Avvio esportazione di " + movimenti.size() + " movimenti nel file " + nomeFile + "...");
        esportatore.esportaRendiconto(this.movimenti, nomeFile);
        System.out.println("Esportazione completata con successo!");
    }

   
    public void salvaDatiSuFile() {
        gestoreFile.salvaMovimenti(this.movimenti);
        System.out.println("Dati contabili salvati correttamente su file.");
    }
}