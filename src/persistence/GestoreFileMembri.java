
package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modello.Membro;

public class GestoreFileMembri {
    
    private static final Path FILE_PATH = Paths.get("dati", "membri.txt");

    /**
     * Legge tutte le righe dal file membri.txt e le converte in una lista di oggetti Membro.
     * @return Una lista di Membro. Se il file non esiste o è vuoto, ritorna una lista vuota.
     */
    public List<Membro> caricaMembri() {
        List<Membro> membri = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            System.out.println("Nota: Il file membri.txt non esiste ancora. Verrà creato al primo salvaggio.");
            return membri;
        }

        try {
            List<String> righe = Files.readAllLines(FILE_PATH);
            for (String riga : righe) {
                if (riga.trim().isEmpty()) {
                    continue;
                }
                
                String[] dati = riga.split(";");
                
                if (dati.length == 3) {
                    
                    Membro m = new Membro(dati[0].trim(), dati[1].trim(), dati[2].trim()); 
                    membri.add(m);
                } else {
                    System.err.println("Attenzione: riga malformattata nel file membri.txt (verrà ignorata): " + riga);
                }
            }
        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile leggere il file dei membri: " + e.getMessage());
            return new ArrayList<>(); 
        }

        return membri;
    }

    /**
     * Salva una lista di oggetti Membro nel file membri.txt, sovrascrivendo il contenuto precedente.
     * @param membri La lista di membri da salvare.
     */
    public void salvaMembri(List<Membro> membri) {
        List<String> righe = membri.stream()
                                   .map(Membro::toFileString)
                                   .collect(Collectors.toList());
        try {
            Files.createDirectories(FILE_PATH.getParent());
            Files.write(FILE_PATH, righe);
        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile salvare il file dei membri: " + e.getMessage());
        }
    }
}     

    
    

