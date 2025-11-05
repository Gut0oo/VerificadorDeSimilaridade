package model;

public class Resultado {
    private double similaridade;
    private String doc1, doc2; //nome dos arquivos que foram comparados

    public Resultado(String doc1, String doc2){
        this.doc1 = doc1;
        this.doc2 = doc2;
        this.similaridade = 0.0;
    }


    //getters & setters
    public double getSimilaridade(){ return similaridade; }
    public void setSimilaridade(double similaridade){ this.similaridade = similaridade; }

    public String getDoc1(){ return doc1; }
    public void setDoc1(String doc1){ this.doc1 = doc1; }

    public String getDoc2(){ return doc2; }
    public void setDoc2(String doc2){ this.doc2 = doc2; }
}
