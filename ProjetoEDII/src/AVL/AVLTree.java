package AVL;
import java.util.ArrayList;

import Main.App;
import model.Resultado;

public class AVLTree{
    private BNode root;
    private int rotLL = 0;  //Rotação simples esquerda
    private int rotRR = 0;  //Rotação simples direita
    private int rotLR = 0;  //Rotação esquerda direita
    private int rotRL = 0;  //Rotação direita esquerda

    public AVLTree(){ //inicializa a arvore vazia
        this.root = null;
    }

    public void insert(double key, Resultado result) {
        this.root = (insert(root, key, result));
    }

    public BNode insert(BNode no, double key, Resultado result){
        if (no == null) {
            return new BNode(key, result);
        }

        if(no.getKey() == key){ //Caso já exista esse valor na arvore, ele só adiciona na lista resultado
            no.getArrResult().add(result);
            return no;
        }else if(no.getKey() > key){
            no.setLeft(insert(no.getLeft(), key, result));
            if (no.getLeft() != null) no.getLeft().setParent(no);
        }else{
            no.setRight(insert(no.getRight(), key, result));
            if (no.getRight() != null) no.getRight().setParent(no);
        }

        updateBalance(no);

        if(no.getFB() < -1 || no.getFB() > 1){
            no = balanceHelper(no);
        }

        return no;
    }

    public String montarStringDocs(String doc1, String doc2, BNode no){
        StringBuilder sb = new StringBuilder();
        searchDocs(doc1, doc2, no, sb);
        return sb.toString();
    }

    public BNode searchDocs(String doc1, String doc2, BNode no, StringBuilder sb){
        if (no != null) {
            ArrayList<Resultado> resultados = no.getArrResult();

            for (Resultado r : resultados) {
                String r1 = new java.io.File(r.getDoc1()).getName();
                String r2 = new java.io.File(r.getDoc2()).getName();

                if ((r1.equals(doc1) && r2.equals(doc2)) || (r1.equals(doc2) && r2.equals(doc1)) ) {//verifica as duas ordens possíveis
                    sb.append(r.toString()).append("\n");
                    return no;
                }
            }

            BNode encontrado = searchDocs(doc1, doc2, no.getLeft(), sb);//Aqui vai buscar na subarvore a esquerda
            if (encontrado != null) {
                return encontrado;
            }

            encontrado = searchDocs(doc1, doc2, no.getRight(), sb);//Aqui vai buscar na subarvore a direita
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;//Se não encontro, retorna null
    }






   //----------------------------------------TOPK----------------------------------------
    private void searchTopK(double limiar, BNode no, ArrayList<BNode> temp ){
        if(no != null){
            searchTopK(limiar, no.getLeft(), temp);
            if(no.getKey() >= limiar){
                temp.add(no);
            }
            searchTopK(limiar, no.getRight(), temp);
        }
    }

    
    public String montarStringTopK(double similaridade, int k){
        StringBuilder sb = new StringBuilder();
        exibirTopK(similaridade, k, sb);
        return sb.toString();
    }

    public void exibirTopK(double limiar, int k, StringBuilder sb){
        ArrayList<BNode> temp = new ArrayList<>();
        searchTopK(limiar, root, temp);

        ArrayList<Resultado> resultados = coletarResultados(temp);

        ordenarResultados(resultados); // ordem decrescente

        int max = Math.min(k, resultados.size());

        System.out.println("temp.size() = " + temp.size());
        System.out.println("k = " + k);
        System.out.println("max = " + max);
        
        sb.append("\n=== VERFICADOR DE SIMILARIDADE DE TEXTOS ===\n");
        for(int i = 0; i < max; i++){
            sb.append(resultados.get(i).toString()).append("\n");
        }
        sb.append("Métrica de Similaridade: Cosseno\n");
    }





    //----------------------------------------Lista----------------------------------------
    public void searchLista(double similaridade, BNode no, StringBuilder sb){ //Esse search vai ser para buscar os nós com maiores similaridade 
        if(no != null){
            searchLista(similaridade, no.getLeft(), sb);
            if(no.getKey() >= similaridade) {
                sb.append(no.getResultadosComoTexto()); //salva no arquivo
            }
            searchLista(similaridade, no.getRight(), sb);
        }
    }

    public String montarStringLista(double similaridade, BNode no){
        StringBuilder sb = new StringBuilder();
        searchLista(similaridade, no, sb);
        return sb.toString();
    }

    public BNode searchMenor(BNode no){ //Busca o menor
        if(no == null) return null;
        return findMin(no);
    }

    //========= Metodos para Rotação =========
    //O parametro "no" é a raiz da subarvore que precisa ser balanceada
    public BNode rotateRight(BNode no){
        rotRR++;
        System.out.println("→ Rotação Simples à Direita (RR)");
        boolean ehraiz = no.isRoot();

        BNode esq = no.getLeft();
        BNode temp = esq.getRight();

        no.setLeft(temp);
        if(temp != null) temp.setParent(no);

        esq.setRight(no);
        no.setParent(esq);

        updateBalance(no);
        updateBalance(esq);

        if(ehraiz){
            this.root = esq;
        }

        return esq;
    }

    public BNode rotateLeft(BNode no){
        rotLL++;
        System.out.println("→ Rotação Simples à Esquerda (LL)");
        boolean ehraiz = no.isRoot();

        BNode dir = no.getRight();
        BNode temp = dir.getLeft();

        no.setRight(temp);
        if(temp != null) temp.setParent(no);

        dir.setLeft(no);
        no.setParent(dir);

        updateBalance(no);
        updateBalance(dir);
        
        if(ehraiz){
            this.root = dir;
        }
        return dir;
    }

    public BNode rotateLeftRight(BNode no){
        rotLR++;
        System.out.println("→ Rotação Dupla Esquerda-Direita (LR)");
        no.setLeft(rotateLeft(no.getLeft()));
        return rotateRight(no);
    }

    public BNode rotateRightLeft(BNode no){
        rotRL++;
        System.out.println("→ Rotação Dupla Direita-Esquerda (RL)");
        no.setRight(rotateRight(no.getRight()));
        return rotateLeft(no);
    }

    public BNode balanceHelper(BNode no){
        if(no.getFB() > 1){ //Desbalanceamento para o lado esquerdo
            if(no.getLeft() != null && no.getLeft().getFB() >= 0){
                return rotateRight(no);
            }else if(no.getLeft() != null && no.getLeft().getFB() < 0){
                return rotateLeftRight(no);
            }
        }

        if(no.getFB() < -1){ //Desbalanceamento para o lado direito
            if(no.getRight() != null && no.getRight().getFB() <= 0){
                return rotateLeft(no);
            }else if(no.getRight() != null && no.getRight().getFB() > 0){
                return rotateRightLeft(no);
            }
        }

        return no;
    }

    //========= Metodos auxiliares =========
    public BNode findMin(BNode no){
        while(no.getLeft() != null){
            no = no.getLeft();
        }

        return no;
    }

    public int getHeight(BNode no){
        if(no == null){
            return 0;
        }

        return 1 + Math.max(getHeight(no.getRight()), getHeight(no.getLeft()));
    }

    public int getBalance(BNode no){ //Pega o fator de balanceamento
        return getHeight(no.getLeft()) - getHeight(no.getRight());
    }

    public void updateBalance(BNode no){ //Atualiza o fator de Balanceamento
        no.setFB(getBalance(no));
    }

    public void ordenarResultados(ArrayList<Resultado> lista) {
        lista.sort((a, b) -> Double.compare(b.getSimilaridade(), a.getSimilaridade()));
    }

    public ArrayList<Resultado> coletarResultados(ArrayList<BNode> nos) {
        ArrayList<Resultado> lista = new ArrayList<>();

        for (BNode no : nos) {
            lista.addAll(no.getArrResult()); // pega todos os resultados do nó
        }

        return lista;
    }
    //========= Getter & Setter =========
    public BNode getRoot(){
        return root;
    }    

    public void setRoot(BNode root){
        this.root = root;
    }

    public int getRotLL() { return rotLL; }
    public int getRotRR() { return rotRR; }
    public int getRotLR() { return rotLR; }
    public int getRotRL() { return rotRL; }

    

}