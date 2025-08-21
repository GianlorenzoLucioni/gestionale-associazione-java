
package ui;

import controller.GestioneContabilita;
import controller.GestioneMembri;
import modello.Membro;
import modello.TipoMovimento;

import java.time.LocalDate;
import java.util.List;

public class MenuPrincipale {


    private GestioneMembri gestoreMembri;
    private GestioneContabilita gestoreContabilita;

    
    public MenuPrincipale() {
        this.gestoreMembri = new GestioneMembri();
        this.gestoreContabilita = new GestioneContabilita(); // Inizializziamo il nuovo controller
    }

    
    public void avvia() {
        boolean continua = true;
        while (continua) {
            mostraOpzioni();
            int scelta = InputUtente.leggiIntero("Inserisci la tua scelta: ");
            
            switch (scelta) {
                case 1:
                    visualizzaMembri();
                    break;
                case 2:
                    aggiungiNuovoMembro();
                    break;
                case 3:

                    registraNuovoMovimento(TipoMovimento.ENTRATA);
                    break;
                case 4:

                    registraNuovoMovimento(TipoMovimento.USCITA);
                    break;
                case 5:
                    esportaBilancio();
                    break;
                case 0:
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }
        
        terminaProgramma();
    }

    
    private void mostraOpzioni() {
        System.out.println("\n--- MENU GESTIONALE ---");
        System.out.println("--- Gestione Membri ---");
        System.out.println("1. Visualizza tutti i membri");
        System.out.println("2. Aggiungi un nuovo membro");
        System.out.println("--- Gestione Contabilità ---");
        System.out.println("3. Registra una nuova ENTRATA");
        System.out.println("4. Registra una nuova USCITA");
        System.out.println("5. Esporta bilancio su file CSV");
        System.out.println("-------------------------");
        System.out.println("0. Salva ed esci");
        System.out.println("-------------------------");
    }


    private void visualizzaMembri() {
        System.out.println("\n--- ELENCO MEMBRI ---");
        List<Membro> membri = gestoreMembri.getMembri();
        if (membri.isEmpty()) {
            System.out.println("Nessun membro registrato.");
        } else {
            for (Membro membro : membri) {
                System.out.println("- " + membro.getNome() + " " + membro.getCognome() + " (Ruolo: " + membro.getRuolo() + ")");
            }
        }
    }

    private void aggiungiNuovoMembro() {
        System.out.println("\n--- AGGIUNGI NUOVO MEMBRO ---");
        String nome = InputUtente.leggiTesto("Inserisci il nome: ");
        String cognome = InputUtente.leggiTesto("Inserisci il cognome: ");
        String ruolo = InputUtente.leggiTesto("Inserisci il ruolo: ");
        
        gestoreMembri.aggiungiMembro(nome, cognome, ruolo);
        System.out.println("Membro aggiunto con successo!");
    }

    // --- NUOVI Metodi per la Contabilità ---
    /**
     * Gestisce la registrazione sia di un'entrata che di un'uscita.
     * @param tipo Il tipo di movimento da registrare (ENTRATA o USCITA).
     */
    private void registraNuovoMovimento(TipoMovimento tipo) {
        String tipoString = (tipo == TipoMovimento.ENTRATA) ? "ENTRATA" : "USCITA";
        System.out.println("\n--- REGISTRA NUOVA " + tipoString + " ---");
        
        String descrizione = InputUtente.leggiTesto("Descrizione: ");
        String categoria = InputUtente.leggiTesto("Categoria (es. Donazioni, Costi Materiali): ");
        double importo = InputUtente.leggiDouble("Importo: ");

        // Usiamo la data odierna per il movimento
        gestoreContabilita.aggiungiMovimento(LocalDate.now(), descrizione, importo, tipo, categoria);
        
        System.out.println(tipoString + " registrata con successo!");
    }
    
    private void esportaBilancio() {
        System.out.println("\n--- ESPORTAZIONE BILANCIO ---");
        gestoreContabilita.esportaBilancioCsv();
    }
    
       private void terminaProgramma() {
        System.out.println("\nSalvataggio di tutti i dati in corso...");
        gestoreMembri.salvaDatiSuFile();
        gestoreContabilita.salvaDatiSuFile(); 
        InputUtente.chiudiScanner();
        System.out.println("Programma terminato. Arrivederci!");
    }
}