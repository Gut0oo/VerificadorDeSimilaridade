package Docs;

import java.util.LinkedList;
import Hash.*;

public class ComparadorDeDocumentos {
    public static double calculoSimilaridade(Documento doc1, Documento doc2){//Calculo de similaridade do cosseno
        HashTable h1 = doc1.getTabelaHash();//pegar as duas tabelas hash
        HashTable h2 = doc2.getTabelaHash();

        LinkedList<Node>[] tabH1 = h1.getTabelaHash();
        LinkedList<Node>[] tabH2 = h2.getTabelaHash();

        double countSimi = 0.0;
        double soma1 = 0.0, soma2 = 0.0;

        for(int i = 0; i < h1.getTam(); i++){ //percorre todo o array da tabela 1
            for(Node no : tabH1[i]){ //percorre todos os nós de cada indice da tabela1
                soma1 += no.getFreq()*no.getFreq();
                Node no2 = h2.get(no.getPalavra());
                if(no2 != null){
                    countSimi += no.getFreq()*no2.getFreq();
                }
            }
        }

        
        for(int i = 0; i < h1.getTam(); i++){ //percorre todo o array da tabela 2
            for(Node no : tabH2[i]){ //percorre todos os nós de cada indice da tabela2
                soma2 += no.getFreq()*no.getFreq();
            }
        }

        if(soma1 == 0 || soma2 == 0){ //evitar divisão com zero
            return 0.0;
        }

        double similaridade = countSimi / (Math.sqrt(soma1) * Math.sqrt(soma2));
        
        return similaridade;
    }
}
