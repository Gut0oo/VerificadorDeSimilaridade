package Hash;

import java.util.LinkedList;

public class HashTable{
    private LinkedList<Node>[] tabelaHash; //um array de lista ligada
    private int contColisao, qtd;
    private int tam;

    public HashTable(int tam){
        tabelaHash = new LinkedList[tam];//cria o array
        for(int i = 0; i < tam; i++){ //inicializa cada indice com uma lista ligada - evita erro nullPointer
            tabelaHash[i] = new LinkedList<>();
        }
    
        this.tam = tam;
        this.contColisao = this.qtd = 0;
    }

    public void put(String palavra, int freq){
        double fatorCarga = (double)qtd / tam; //calcula o fator de carga

        if(fatorCarga > 0.8){ //antes de inserir o novo elemento, verifica se precisa realizar um resize
            resize();
        }

        int index = metodoDivisao(palavra); //calcula o indice a ser inserido

        for(Node no : tabelaHash[index]){
            if(no.getPalavra().equals(palavra)){ //Aqui verfica se já existe o nó 
                no.setFreq(no.getFreq() + freq);//Se sim, apenas atualiza a frequencia
                return;
            }
        }

        if(!tabelaHash[index].isEmpty()){ //verifica se a lista ligada não está vazia para contar as colisões
            contColisao++;
        }

        tabelaHash[index].addFirst(new Node(palavra, freq)); //adiciona no inicio da lista -> O(1)
        qtd++; //faz a contagem da quantidade de elementos
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

    public void resize(){
        int novoTam = this.tam * 2; //dobra o tamanho
        LinkedList<Node>[] novaTabelaHash = new LinkedList[novoTam];

        for(int i = 0; i < novoTam; i++){ //inicializa cada indice com uma lista ligada - evita erro nullPointer
            novaTabelaHash[i] = new LinkedList<>();
        }

        LinkedList<Node>[] antigaTabela = tabelaHash;//guarda a tabela anterior

        //atualiza os valores da tabela modifica
        this.tabelaHash = novaTabelaHash;
        this.tam = novoTam;
        this.contColisao = 0;


        for(LinkedList<Node> lista : antigaTabela){ //aqui vai add os nós da tabela antiga na tabela nova
            for(Node no : lista){
                put(no.getPalavra(), no.getFreq());//aqui chama o metodo de inserção, assim inserindo no local certo
            }
        }

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

    public int metodoMultiplicacao(String dado){
        double A = (Math.sqrt(5) - 1) / 2; //constante
        
        int stringTam = dado.length();
        int hash = (int) dado.charAt(0);

        for(int i = 1; i < stringTam; i++){
            hash = (31 * hash) + dado.charAt(i); //31 eh primo, assim dando uma melhor dispersão 
        }//o numero primo eh mlr pq não tem divisores alem de 1 e ele mesmo, assim evitando padrões de repetição

        hash = Math.abs(hash);
        double parteFrac = (hash * A) % 1; //pega a parte fracionaria

        if (parteFrac < 0) {
            parteFrac += 1; 
        }
        return (int) (tam * parteFrac);
    }

    //Getter e Setters

    public LinkedList<Node>[] getTabelaHash(){
        return tabelaHash;
    }

    public int getTam(){
        return tam;
    }

    public int getContColisao(){
        return contColisao;
    }
}