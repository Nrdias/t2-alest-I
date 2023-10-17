import java.util.ArrayList;

/**
 * Classe que inicializa a execução da aplicacao.
 * @author Isabel H. Manssour
 */
public class Main {
    public static void main(String[] args) {

        DoubleLinkedListOfStopWords stopWords = new DoubleLinkedListOfStopWords();
        ArrayList<Paginas> paginas = new ArrayList<>();
        ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
        LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
        String l;

        arquivo.open("src/StopWords-EN.txt");
        do{
            //Salvando as stop words na lista para consulta posteriormente
            l = arquivo.getNextLine();
            if(l == null) break;
            stopWords.add(l);
        }while (true);
        arquivo.close();

        int nLinha = 0;
        int nPagina = 0;
        Paginas pagina = new Paginas(nPagina);

        arquivo.open("src/Livros/alice.txt");

        do{
            l = arquivo.getNextLine();
            if (l==null)
               break;
            nLinha++;
            if (nLinha == 40)
            {
                paginas.add(pagina);
                nLinha = 0;
                nPagina++;
                pagina = new Paginas(nPagina);
            }
            linha.setLine(l);
            do
            {
                String palavra = linha.getNextWord();
                if (palavra == null)
                {
                    break;
                }
                palavra = palavra.toLowerCase();
                if (!stopWords.contains(palavra))
                {
                    if(!pagina.contains(palavra)) {

                        palavra = palavra.replaceAll("[^a-zA-Z0-9]", "");
                        if(!palavra.equals("") || palavra.equals(" ")) {
                            pagina.addPalavra(palavra);
                        }
                    }
                }
             } while (true);
        } while (true);
        System.out.println("Numero de paginas: " + paginas.size());
        for(int i = 0; i < paginas.size(); i++){
            System.out.println("Conteúdo da pagina " + i + ": " + paginas.get(i).toString());
        }
        arquivo.close();
    }
}
