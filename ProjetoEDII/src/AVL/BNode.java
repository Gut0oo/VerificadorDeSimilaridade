package AVL;

import java.util.ArrayList;

import model.Resultado;

public class BNode{
    private BNode left, right, parent;
    private int FB;

    private double key;
    private ArrayList<Resultado> result;

    public BNode(double key){ //construtor principal
        this.key = key;
        this.result = new ArrayList<>();
        this.FB = 0;
    }
    
    public BNode(double key, Resultado result){ //Construtor para passar primeiro resultado quando criar
        this(key); //chama o contrutor anterior
        this.result.add(result);
    }

    //Getters & Setters
    public double getKey() { return key; }
    public void setkey(double key) { this.key = key; }

    public ArrayList<Resultado> getArrResult() { return result; }
    public void setArrResult(ArrayList<Resultado> result) { this.result = result; }

    public int getFB() { return FB; }
    public void setFB(int FB) { this.FB = FB; }

    public BNode getLeft() { return left; }
    public void setLeft(BNode left) { this.left = left; }

    public BNode getRight() { return right; }
    public void setRight(BNode right) { this.right = right; }

    public BNode getParent() { return parent; }
    public void setParent(BNode parent) { this.parent = parent; }

    
    //Métodos
    public boolean isRoot(){
        return this.parent == null;
    }

    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    
    public int getDegree(){
        return ((left != null) ? 1 : 0) + ((right != null) ? 1 : 0);
    }
    

    public int getLevel(BNode atual){
        if(atual.parent == null){
            return 0;
        }

        return 1 + getLevel(atual.parent);
    }

    public int getHeight(BNode atual){
        if(atual == null){ //Caso base, chegou alem da folha
            return 0;
        }

        return 1 + Math.max(getHeight(atual.left), getHeight(atual.right));
    }

    public void exibir() {
        for (Resultado r : result) {
            System.out.println(r);
        }
    }
}