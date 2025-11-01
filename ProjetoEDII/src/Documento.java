//Classe com a logica de ler documentos e coletar seus dados
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Documento {
    private String docName;

    public Documento(String docName){
        this.docName = docName;
    }

    public void leitorArquivo(){
        try{
            FileReader leitor = new FileReader(docName); 
            BufferedReader br = new BufferedReader(leitor);

            String line = br.readLine(); //Lê a primeira linha

            while(line != null){
                System.out.println(line);
                line = br.readLine();
            }

        }catch (IOException e){
            System.out.println("Erro! " + e.getMessage());
        } 

    }
}
