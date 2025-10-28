public class AVLTreeRec{

    public AVLTreeRec(BNode root){
        super(root);
    }

    public void insert(int valor) {
        setRoot(insertRec(getRoot(), valor));
    }

    public BNode insert(BNode no, int valor){
        if (no == null) {
            return new BNode(valor);
        }

        if(no.getValor() > valor){
            no.setLeft(insertRec(no.getLeft(), valor));
            if (no.getLeft() != null) no.getLeft().setParent(no);
        }else{
            no.setRight(insertRec(no.getRight(), valor));
            if (no.getRight() != null) no.getRight().setParent(no);
        }

        updateBalance(no);

        if(no.getFB() < -1 || no.getFB() > 1){
            no = balanceHelper(no);
        }

        return no;
    }

    public void delete(int valor){
        setRoot(delete(getRoot(), valor));
    }

    public BNode delete(BNode no, int valor){
        if(no == null){
            return null;
        }

        if(no.getValor() < valor){
            no.setRight(delete(no.getRight(), valor));
        }else if(no.getValor() > valor){
            no.setLeft(delete(no.getLeft(), valor));
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
                no.setValor(temp.getValor());
                no.setRight(delete(no.getRight(), temp.getValor()));
            }
        }

        updateBalance(no);


        if(no.getFB() < -1 || no.getFB() > 1){
            no = balanceHelper(no);
        }

        return no;
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
            setRoot(esq);
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
            setRoot(dir);
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
}