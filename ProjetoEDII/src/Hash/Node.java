package Hash;

public class Node {
    private String palavra;
    private int freq; //frenquencia que essa palavra aparece no texto

    public Node(String palavra, int freq){
        this.palavra = palavra;
        this.freq = freq;
    }

    public String getPalavra(){
        return palavra;
    }

    public void setPalavra(String palavra){
        this.palavra = palavra;
    }

    public int getFreq(){
        return freq;
    }

    public void setFreq(int freq){
        this.freq = freq;
    }
}
