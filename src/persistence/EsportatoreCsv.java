
package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import modello.MovimentoContabile;

public class EsportatoreCsv {

    /**
     * Esporta una lista di movimenti contabili in un file CSV.
     * @param movimenti La lista dei movimenti da esportare.
     * @param percorsoFile Il nome del file da creare (es. "bilancio.csv").
     */
    public void esportaRendiconto(List<MovimentoContabile> movimenti, String percorsoFile) {
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(percorsoFile))) {

           
            writer.println("Data;Descrizione;Categoria;Entrate;Uscite");

           
            for (MovimentoContabile movimento : movimenti) {
                
                writer.println(movimento.toCsvString());
            }

            
            double totaleEntrate = 0;
            double totaleUscite = 0;
            for (MovimentoContabile m : movimenti) {
                if (m.getTipo() == modello.TipoMovimento.ENTRATA) {
                    totaleEntrate += m.getImporto();
                } else {
                    totaleUscite += m.getImporto();
                }
            }
            double avanzo = totaleEntrate - totaleUscite;

           
            writer.println();
            writer.println();
            
          
            writer.println("RIEPILOGO; ; ;Totale Entrate;" + String.format("%.2f", totaleEntrate));
            writer.println(" ; ; ;Totale Uscite;" + String.format("%.2f", totaleUscite));
            writer.println(" ; ; ;AVANZO DI GESTIONE;" + String.format("%.2f", avanzo));

        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile scrivere il file CSV: " + e.getMessage());
        }
    }
}
