package model;

import Docs.ComparadorDeDocumentos;
import Docs.Documento;

public class Resultado {
    private double similaridade;
    private String doc1, doc2; //nome dos arquivos que foram comparados

    public Resultado(String doc1, String doc2){
        this.doc1 = doc1;
        this.doc2 = doc2;

        Documento documento1 = new Documento(doc1);
        documento1.leitorArquivo();
        Documento documento2 = new Documento(doc2);
        documento2.leitorArquivo();
        
        this.similaridade = ComparadorDeDocumentos.calculoSimilaridade(documento1, documento2);
    }


    //getters & setters
    public double getSimilaridade(){ return similaridade; }
    public void setSimilaridade(double similaridade){ this.similaridade = similaridade; }

    public String getDoc1(){ return doc1; }
    public void setDoc1(String doc1){ this.doc1 = doc1; }

    public String getDoc2(){ return doc2; }
    public void setDoc2(String doc2){ this.doc2 = doc2; }
}
