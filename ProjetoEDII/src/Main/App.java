package Main;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import AVL.*;
import Docs.ComparadorDeDocumentos;
import Docs.Documento;
import model.Resultado;

public class App {

    public static void listaExibir(double simi, AVLTree arvore, int count, List<Documento> docs){
        BNode menor = arvore.searchMenor(arvore.getRoot());
        StringBuilder sb = new StringBuilder();


        sb.append("=== VERFICADOR DE SIMILARIDADE DE TEXTOS ===\n");
        sb.append("Total de documentos processados: " + docs.size() + "\n");
        sb.append("Total de pares comparados: " + count + "\n");
        sb.append("Função hash utilizada: \n");
        sb.append("Métrica de Similaridade: Cosseno\n");

        sb.append("\nPares com similaridade >= " + simi + ":\n");
        sb.append("--------------------------------------\n");

        String listaMaiores = arvore.montarStringLista(simi, arvore.getRoot());
        sb.append(listaMaiores);

        sb.append("\nPares com menor similariade:\n");
        sb.append("--------------------------------------\n");
        
        if (menor != null) {
            sb.append(menor.getResultadosComoTexto());
        }

        String resultadoFinal = sb.toString();
        System.out.println(resultadoFinal);
        salvarLinha(resultadoFinal);
    }

    public static void salvarLinha(String conteudo) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("resultado.txt"); 
            writer.write(conteudo);
            writer.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        if(args.length < 3){ //quarto parametro eh opcional 
            System.out.println("Precisa passar pelo menos 3 parametros");
            return; 
        }

        String dir = args[0];
        double limiar = Double.parseDouble(args[1]);//guarda o minimo de similaridade que quer
        String modo = args[2];

        if(limiar < 0.0 || limiar > 1.0){
            System.out.println("Limiar inválido. Deve ser número entre 0.0 e 1.0.");
            return;
        }

        int countParCompa = 0;

        AVLTree arvore = new AVLTree();
        List<Documento> documentos = new ArrayList<>();

        File pasta = new File(dir);
        File[] arquivos = pasta.listFiles();
        
        if(arquivos.length == 0 || arquivos == null){
            System.out.println();
            return;
        }

        for(File arquivo : arquivos){
            Documento doc = new Documento(arquivo.getPath());
            doc.leitorArquivo();
            documentos.add(doc);
        }
        for(int i = 0; i < documentos.size(); i++){
            for(int j = i + 1; j < documentos.size(); j++){
                Documento doc1 = documentos.get(i);
                Documento doc2 = documentos.get(j);

                double sim = ComparadorDeDocumentos.calculoSimilaridade(doc1, doc2);
                countParCompa++;
                Resultado result = new Resultado(doc1.getDocNome(), doc2.getDocNome(), sim);

                arvore.insert(sim, result);
            }
        }
        

        if(modo.equals("lista")){
            listaExibir(limiar, arvore, countParCompa, documentos);

        }else if(modo.equals("topK")){
            int k = Integer.parseInt(args[3]);
            String resultadoTopK = arvore.montarStringTopK(limiar, k);
            System.out.print(resultadoTopK);
            salvarLinha(resultadoTopK);
            
        }else if(modo.equals("busca")){
            String doc1 = args[3];
            String doc2 = args[4];
            BNode result = arvore.searchDocs(doc1, doc2, arvore.getRoot(), new StringBuilder());

            if(result == null){
                System.out.println("O par de documentos não foi encontrado na AVL");
            }else{
                StringBuilder sb = new StringBuilder();
                sb.append("=== VERFICADOR DE SIMILARIDADE DE TEXTOS ===\n");
                sb.append(result.getResultadosComoTexto()); 
                sb.append("Similariade Calculada: " + String.format("%.2f", result.getKey()) + "\n");
                sb.append("Métrica de Similaridade: Cosseno\n");

                String resultadoBusca = sb.toString();

                System.out.print(resultadoBusca);
                salvarLinha(resultadoBusca);
            }
        }else{
            System.out.println("Modo inválido. Use: lista | topK | busca");
            return;
        }
    }
}
