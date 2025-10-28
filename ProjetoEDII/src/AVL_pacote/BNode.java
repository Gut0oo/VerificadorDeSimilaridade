class BNode{
    private BNode esq, dir, pai;
    private int valor, FB;

    public BNode(int valor){
        this.valor = valor;
        this.FB = 0;
    }

    //Métodos
    public boolean isRoot(){
        return this.parent == null;
    }

    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    public int getDegree(){
        int count = 0;

        if(this.left != null){
            count++;
        }

        if(this.right != null){
            count++;
        }

        return count;
    }

    //Uma outra forma de escrever o getDegree():
    /*
    public int getDegree(){
        return ((left != null) ? 1 : 0) + ((right != null) ? 1 : 0)
    }
    */

    public int getLevelInte(){
        BNode atual = this;
        int count = 0;

        while(atual.parent != null){
            atual = atual.parent;
            count++;
        }

        return count;
    }

    public int getLevelRecu(BNode atual){
        if(atual.parent == null){
            return 0;
        }

        return 1 + getLevelRecu(atual.parent);
    }

    public int getHeightInte(){
        BNode atual = this; //Onde eu estou
        BNode anterior = null; //De onde eu vim
        BNode prox = null; //Para onde eu vou

        int prof_atual = 0;
        int altura_max = 0;

        while(atual != null){
            //Usando a Ideia de PosOrdem
            if(anterior == atual.parent){ //Veio do pai
                if(atual.left != null){
                    prox = atual.left;
                }else if(atual.right != null){
                    prox = atual.right;
                }else{
                    prox = atual.parent;
                }
            } else if(anterior == atual.left){ //Veio da Esquerda
                if(atual.right != null){
                    prox = atual.right;
                }else{
                    prox = atual.parent;
                }
            } else if(anterior == atual.right) { //Veio da Direita
                prox = atual.parent;
            }

            if(prox == atual.left || prox == atual.right){
                prof_atual ++;
            }else if(prox == atual.parent){
                prof_atual--;
            }

            altura_max = Math.max(altura_max, prof_atual);

            anterior = atual;
            atual = prox;
        }

        return altura_max;
    }

    public int getHeightRec(BNode atual){
        if(atual == null){ //Caso base, chegou alem da folha
            return 0;
        }

        return 1 + Math.max(getHeightRec(atual.left), getHeightRec(atual.right));
    }



    //Getters & Setters
    public int getValor() {
        return valor;
    }

    public void setFB(int FB) { this.FB = FB; }

    public int getFB() { return FB; }

    public BNode getLeft() {
        return left;
    }

    public BNode getRight() {
        return right;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setLeft(BNode left) {
        this.left = left;
    }

    public void setRight(BNode right) {
        this.right = right;
    }

    public BNode getParent() {
        return parent;
    }

    public void setParent(BNode parent) {
        this.parent = parent;
    }
}