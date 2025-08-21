// Il file si trova in: src/persistence/GestoreFileContabilita.java
package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modello.MovimentoContabile;
import modello.TipoMovimento;

public class GestoreFileContabilita {

    private static final Path FILE_PATH = Paths.get("dati", "movimenti.csv");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public List<MovimentoContabile> caricaMovimenti() {
        List<MovimentoContabile> movimenti = new ArrayList<>();
        if (!Files.exists(FILE_PATH)) {
            return movimenti;
        }

        try {
            List<String> righe = Files.readAllLines(FILE_PATH);
            for (int i = 1; i < righe.size(); i++) {
                String riga = righe.get(i);
                if (riga.trim().isEmpty()) continue;

                // --- QUESTA È LA RIGA CORRETTA ---
                // Il parametro -1 forza lo split a non ignorare le stringhe vuote alla fine.
                String[] dati = riga.split(";", -1); 

                if (dati.length == 5) {
                    try {
                        LocalDate data = LocalDate.parse(dati[0], DATE_FORMATTER);
                        String descrizione = dati[1];
                        String categoria = dati[2];
                        
                        double importo;
                        TipoMovimento tipo;
                        if (!dati[3].isEmpty()) {
                            importo = Double.parseDouble(dati[3].replace(',', '.'));
                            tipo = TipoMovimento.ENTRATA;
                        } else {
                            importo = Double.parseDouble(dati[4].replace(',', '.'));
                            tipo = TipoMovimento.USCITA;
                        }
                        
                        movimenti.add(new MovimentoContabile(data, descrizione, importo, tipo, categoria));
                    } catch (Exception e) {
                        System.err.println("Attenzione: riga malformattata nel file movimenti.csv (verrà ignorata): " + riga);
                    }
                } else {
                     System.err.println("Attenzione: riga con numero di campi errato (verrà ignorata): " + riga);
                }
            }
        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile leggere il file dei movimenti: " + e.getMessage());
        }
        return movimenti;
    }

    public void salvaMovimenti(List<MovimentoContabile> movimenti) {
        try {
            Files.createDirectories(FILE_PATH.getParent());
            List<String> righe = new ArrayList<>();
            righe.add("Data;Descrizione;Categoria;Entrate;Uscite"); 
            
            for (MovimentoContabile movimento : movimenti) {
                righe.add(movimento.toCsvString());
            }

            Files.write(FILE_PATH, righe);
        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile salvare il file dei movimenti: " + e.getMessage());
        }
    }
}