package AVL_pacote;
import model.Resultado;

public class AVLTree{
    private BNode root;

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

    public void delete(double key){
        this.root = (delete(root, key));
    }

    public BNode delete(BNode no, double key){
        if(no == null){
            return null;
        }

        if(no.getKey() < key){
            no.setRight(delete(no.getRight(), key));
        }else if(no.getKey() > key){
            no.setLeft(delete(no.getLeft(), key));
        }else{
            if(no.isLeaf()){
                return null;
            }else if(no.getDegree() == 1){
                if(no.getLeft() != null){
                    return no.getLeft();
                }else{
                    return no.getRight();
                }
            }else {
                BNode temp = findMin(no.getRight());
                no.setkey(temp.getKey());
                no.setRight(delete(no.getRight(), temp.getKey()));
            }
        }

        updateBalance(no);

        if(no.getFB() < -1 || no.getFB() > 1){
            no = balanceHelper(no);
        }

        return no;
    }
    //========= Metodos para Rotação =========
    //O parametro "no" é a raiz da subarvore que precisa ser balanceada
    public BNode rotateRight(BNode no){
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
        System.out.println("→ Rotação Dupla Esquerda-Direita (LR)");
        no.setLeft(rotateLeft(no.getLeft()));
        return rotateRight(no);
    }

    public BNode rotateRightLeft(BNode no){
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

    //========= Getter & Setter =========
    public BNode getRoot(){
        return root;
    }    

    public void setRoot(BNode root){
        this.root = root;
    }
}