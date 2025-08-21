package modello;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovimentoContabile {

    private LocalDate data;
    private String descrizione;
    private double importo;
    private TipoMovimento tipo;
    private String categoria;


    public MovimentoContabile(LocalDate data, String descrizione , 
    double importo, TipoMovimento tipo, String categoria){

        this.data = data;
        this.descrizione = descrizione;
        this.importo = Math.abs(importo);
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public LocalDate getData(){return data;}
    public String getDescrizione(){return descrizione;}
    public double getImporto(){return importo; }
    public TipoMovimento getTipo(){ return tipo; }
    public String getCategoria(){ return categoria; }

    public String toCsvString(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dataFormattata = data.format(formatter);
    
    String entrata = (tipo == TipoMovimento.ENTRATA) ? String.format("%.2f", importo) : "";
    String uscita = (tipo == TipoMovimento.USCITA) ? String.format("%.2f", importo) : "";

    return dataFormattata + ";" + descrizione + ";" + categoria + ";" + entrata + ";" + uscita;
    }

    
}
