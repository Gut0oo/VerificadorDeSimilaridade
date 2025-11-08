package Docs;
//Classe com a logica de ler documentos e coletar seus dados
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import Hash.HashTable;

public class Documento {
    private HashTable tabela;
    private String docName;
    private final String[] stopWords = {
        "a", "ao", "aos", "aquela", "aquelas", "aquele", "aqueles",
        "aquilo", "as", "ate", "com", "como", "da", "das", "de", "dela", "delas",
        "dele", "deles", "depois", "do", "dos", "e", "ela", "elas", "ele",
        "eles", "em", "entre", "era", "eram", "essa", "essas", "esse", "esses",
        "esta", "estamos", "estas", "estava", "estavam",
        "este", "estes", "eu", "foi", "foram", "ha", "isso", "isto", "ja",
        "lhe", "lhes", "mais", "mas", "me", "mesmo", "meu", "meus", "minha",
        "minhas", "muito", "na", "nas", "nem", "no", "nos", "nosso", "nossa",
        "nossos", "nossas", "num", "numa", "o", "os", "ou", "para",
        "pela", "pelas", "pelo", "pelos", "por", "qual", "quando", "que",
        "quem", "se", "sem", "seu", "seus", "so", "somos", "sua", "suas",
        "tambem", "te", "tem", "tenho", "teu", "teus", "tua", "tuas",
        "um", "uma", "voce", "voces", "vos"
    };

    public Documento(String docName){
        this.docName = docName;
    }

    public void leitorArquivo(){
        try{
            FileReader leitor = new FileReader(docName); 
            BufferedReader br = new BufferedReader(leitor);

            String line = br.readLine(); //Le a primeira linha

            while(line != null){
                System.out.println(line);
                line = br.readLine();
            }

            br.close();
        }catch (IOException e){
            System.out.println("Erro! " + e.getMessage());
        } 
    }

    public void filtro(String line){
        
    }
}
