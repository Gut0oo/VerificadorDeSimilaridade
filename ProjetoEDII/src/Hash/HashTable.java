package Hash;

import java.util.LinkedList;

public class HashTable{
    private int tam;
    private LinkedList<Node>[] tabela; //um array de lista ligada

    public HashTable(int tam){
        tabela = new LinkedList[tam];
        this.tam = tam;
    }

    public void inserir(String palavra, int freq){
        int index = metodoDivisao(palavra); //calcula o indice a ser inserido
        Node no = new Node(palavra, freq);

        tabela[index].addFirst(no); //adiciona no inicio da lista -> O(1)
    }

    public Node buscas(String palavra){
        Node  no = null;
        return no;
    }

    

    //Metodo de divisão
    public int metodoDivisao(String dado){
        int stringTam = dado.length(); //guarda o tamanho da string

        int hash = (int) dado.charAt(0);

        for(int i = 0; i < stringTam; i++){
            hash += (int) dado.charAt(i); //soma o codigo Ascii de cada codigo
        }

        hash *= hash; //Elevando a 2 para trazer uma melhor distribuição na tabela

        return (hash % tam);
    }

    public int metodoX(){
        return 0;
    }
}