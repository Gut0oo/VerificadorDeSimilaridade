package Hash;

import java.util.LinkedList;

public class HashTable{
    private LinkedList<Node>[] tabelaHash; //um array de lista ligada
    private int contColisao;
    private int tam;

    public HashTable(int tam){
        tabelaHash = new LinkedList[tam];//cria o array
        for(int i = 0; i < tam; i++){ //inicializa cada indice com uma lista ligada - evita erro nullPointer
            tabelaHash[i] = new LinkedList<>();
        }
    
        this.tam = tam;
        this.contColisao = 0;
    }

    public void put(String palavra, int freq){
        int index = metodoDivisao(palavra); //calcula o indice a ser inserido
        Node no = new Node(palavra, freq);

        if(!tabelaHash[index].isEmpty()){ //verifica se a lista ligada não está vazia para contar as colisões
            contColisao++;
        }

        tabelaHash[index].addFirst(no); //adiciona no inicio da lista -> O(1)
    }

    public Node get(String palavra){
        int index = metodoDivisao(palavra); //Pega o index que está a palavra
        int tam = tabelaHash[index].size();

        Node no = null;

        for(int i = 0; i < tam; i++){
            if(tabelaHash[index].get(i).getPalavra().equals(palavra)){
                no = tabelaHash[index].get(i);
                break;
            }
        }
        
        return no;
    }

    //Metodo de divisão
    public int metodoDivisao(String dado){
        int stringTam = dado.length(); //guarda o tamanho da string

        int hash = (int) dado.charAt(0);

        for(int i = 1; i < stringTam; i++){
            hash = (31 * hash) + dado.charAt(i); //31 eh primo, assim dando uma melhor dispersão 
        }//o numero primo eh mlr pq não tem divisores alem de 1 e ele mesmo, assim evitando padrões de repetição

        return Math.abs(hash % tam); //evita numero negatvios caso hash estoure o limite
    }

    public int metodoX(){
        return 0;
    }

    //Getter e Setters

    public LinkedList<Node>[] getTabelaHash(){
        return tabelaHash;
    }

    public int getTam(){
        return tam;
    }
}