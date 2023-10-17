import java.util.ArrayList;

public class Paginas {
    private ArrayList<String> palavras;
    private int pagina;

    public Paginas(int pag) {
        this.palavras = new ArrayList<>();
        this.pagina = pag;
    }

    public void addPalavra(String palavra){
        this.palavras.add(palavra);
    }

    public boolean contains(String palavra){
        return this.palavras.contains(palavra);
    }
    public int getPagina(){
        return this.pagina;
    }
    public String getPalavra(String palavra){
        return this.palavras.get(this.palavras.indexOf(palavra));
    }

    @Override
    public String toString(){
        String aux = "";
        for(String palavra : this.palavras){
            aux += palavra + " - ";
        }
        return aux;
    }
}
