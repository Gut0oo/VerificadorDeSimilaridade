import java.lang.reflect.Array;
import java.util.ArrayList;

import AVL.*;
import Docs.Documento;
import model.Resultado;

public class App {

    public static void listaExibir(double simi, AVLTree arvore){
        BNode menor = arvore.searchMenor(arvore.getRoot());
        System.out.println("=== VERFICADOR DE SIMILARIDADE DE TEXTOS ===");
        
        System.out.println("\nPares com similaridade >= " + simi + ":");
        System.out.println("--------------------------------------");
        arvore.searchMaiores(simi, arvore.getRoot());
        System.out.println("\nPares com menor similariade:");
        System.out.println("--------------------------------------");
        menor.exibir();
    }

    public static void main(String[] args) throws Exception {
        if(args.length < 3){ //quarto parametro eh opcional 
            System.out.println("Precisa passar pelo menos 3 parametros");
            return; 
        }

        double minimoSimi = Double.parseDouble(args[1]);//guarda o minimo de similaridade que quer

        
        if(args[2].equals("lista")){

        }

        if(args[2].equals("topK")){
            
        }
        
        if(args[2].equals("busca")){
            
        }

        AVLTree arvore = new AVLTree();

    }
}
