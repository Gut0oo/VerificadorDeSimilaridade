package model;

public class Resultado {
    private double similaridade;
    private String doc1, doc2; //nome dos arquivos que serão comparados

    public Resultado(String doc1, String doc2){
        this.doc1 = doc1;
        this.doc2 = doc2;
        this.similaridade = 0.0;
    }

    //Método pro Calculo de Similaridade: 
    public double similaridadeCalculo(){
        return 0.0;
    }
}
