package ui;

import java.util.Scanner;

public class InputUtente {

    


    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Mostra un messaggio all'utente e legge la riga di testo che viene inserita.
     * @param messaggio Il messaggio da visualizzare (es. "Inserisci il nome: ").
     * @return La stringa inserita dall'utente.
     */
    public static String leggiTesto(String messaggio) {
        System.out.print(messaggio);
        return scanner.nextLine();
    }

    /**
     * Mostra un messaggio e continua a chiedere un input finché l'utente non inserisce un numero intero valido.
     * @param messaggio Il messaggio da visualizzare.
     * @return Il numero intero inserito.
     */
    public static int leggiIntero(String messaggio) {
        while (true) { // Ciclo infinito finché non otteniamo un input valido
            System.out.print(messaggio);
            String input = scanner.nextLine();
            try {
                // Prova a convertire la stringa in un intero
                int numero = Integer.parseInt(input);
                return numero; // Se ci riesce, ritorna il numero e esce dal ciclo
            } catch (NumberFormatException e) {
                // Se la conversione fallisce, avvisa l'utente e il ciclo ricomincia
                System.out.println("Errore: Inserisci un numero valido.");
            }
        }
    }

     public static double leggiDouble(String messaggio) {
        while (true) {
            System.out.print(messaggio);
            String input = scanner.nextLine();
            try {
                // Sostituisce la virgola con il punto per renderlo compatibile con Double.parseDouble
                input = input.replace(',', '.');
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserisci un importo numerico valido (es. 150.50).");
            }
        }
    }
    
    /**
     * Metodo di cortesia per chiudere lo scanner quando il programma termina.
     * È buona norma rilasciare le risorse.
     */
    public static void chiudiScanner() {
        scanner.close();
    }
}