package Docs;
//Classe com a logica de ler documentos e coletar seus dados
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;

import Hash.*;

public class Documento {
    private HashTable tabelaHash;
    private String docNome;

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

    public Documento(String docNome){
        this.tabelaHash = new HashTable(101);
        this.docNome = docNome;
    }

    public void leitorArquivo(){
        try{
            FileReader leitor = new FileReader(docNome); 
            BufferedReader br = new BufferedReader(leitor);

            String linha = br.readLine(); //Le a primeira linha

            while(linha != null){//le cada linha do doc
                filtro(linha);
                linha = br.readLine();
            }

            br.close();
        }catch (IOException e){
            System.out.println("Erro! " + e.getMessage());
        } 
    }

    private void filtro(String linha){
        linha = linha.toLowerCase(); //passa as palavra para o minusculo

        //Normalize separa as letras e acentos, exemplo: ação -> a c . a ~ o - O replaceAll vai remover os acentos
        linha = Normalizer.normalize(linha, Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        linha = linha.replaceAll("[^a-z0-9 ]", "");//remove tudo que não for letra, numero ou espaço, entao: . , ! ? () [] ""

        String[] palavra = linha.split(" "); //Tira os espaçamento da linha e guarda as palavra no array palavra
        
        for(String p : palavra){
            if(!p.isEmpty()){//evita possiveis strings vazias
                if(!ehStopWord(p)){ //Verfica se não eh uma stopword
                    tabelaHash.put(p, 1);//se não, insere um novo nó na lista
                }
            }
        }
    }

    private boolean ehStopWord(String palavra){ //metodo para verificar se a palavra eh uma stopword
        for(String p : stopWords){
            if(p.equals(palavra) || palavra.length() <= 2){
                return true; //se for retorna true
            }
        }

        return false;//se não, retorna false
    }

    //Getters e Setters

    public HashTable getTabelaHash(){
        return tabelaHash;
    }

    public String getDocNome(){
        return docNome;
    }
}
