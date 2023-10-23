import java.util.Scanner;

public class Programa {
    private DoubleLinkedListOfStopWords stopWords;
    private ListaOrdenadaDePalavras palavras;
    private ArquivoTexto arquivo;
    private LinhaTexto linha;
    private String s;
    private Scanner in;
    private int nLinha;
    private int nPagina;
    private int palavrasNoTexto;
    private int numeroDeStopWords;

    public Programa(){
        stopWords = new DoubleLinkedListOfStopWords();
        palavras = new ListaOrdenadaDePalavras();
        arquivo = new ArquivoTexto();
        linha = new LinhaTexto();
        s = null;
        in = new Scanner(System.in);
        nLinha = 0;
        nPagina = 0;
        palavrasNoTexto = 0;
        numeroDeStopWords = 0;
    }

    public void executar(){
        guardarStopWords();
        lerArquivo();
        menu();
    }

    public void menu(){
        do{
            System.out.println("""
                    ============================================
                    Menu
                    ============================================
                    [0] - Encerrar o programa;
                    [1] - Exibir todo o Índice;
                    [2] - Pesquisar ocorrências de uma palavra;
                    [3] - Palavra com maior número de ocorrências;
                    [4] - Percentual de stop words;
                    """);
            int op = in.nextInt();
            in.nextLine();
            switch (op){
                case 0:
                    System.out.println("""
                            ============================================
                            Programa encerrado!
                            ============================================
                            """);
                    return;
                case 1:
                    System.out.println(palavras);
                    break;
                case 2:
                    System.out.println("Digite a palavra: ");
                    String str = in.nextLine();
                    System.out.println(str + palavras.getOcorrencias(str));
                    break;
                case 3:
                    System.out.println(palavras.palavraComMaiorOcorrencia());
                    break;
                case 4:
                    System.out.println("Percentual de stop words no texto: " + (numeroDeStopWords*100)/(palavrasNoTexto) + "%");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }while (true);

    }

    public void guardarStopWords(){
        System.out.println("""
                ============================================
                Lendo o arquivo de Stop Words...
                ============================================
                """);
        arquivo.open("src/StopWords-EN.txt");
        do{
            s = arquivo.getNextLine();
            if(s == null) break;
            stopWords.add(s);
        }while (true);
        s = null;
        arquivo.close();
        System.out.println("""
                ============================================
                Arquivo de Stop Words lido com sucesso!
                ============================================
                """);
    }

    public void lerArquivo(){

        System.out.println("""
                Bem vindo!
                Para a execução correta do programa, é necessário que o arquivo esteja na pasta Livros.
                Além disso, informe somente o nome do livro, não é necessário informar o .txt.
                """);
        System.out.println("Informe o arquivo: ");
        String path = "src/Livros/" + in.nextLine() + ".txt";
        boolean opened = arquivo.open(path);
        while (!opened){
            path = "src/Livros/" + in.nextLine() + ".txt";
            opened = arquivo.open(path);
        }

        System.out.println("""
                ============================================
                Lendo o arquivo...
                ============================================
                """);
        do{
            s = arquivo.getNextLine();
            if (s==null)
                break;
            nLinha++;
            if (nLinha == 40)
            {
                nLinha = 0;
                nPagina++;
            }
            linha.setLine(s);
            do
            {
                String str = linha.getNextWord();
                if (str == null){
                    break;
                }
                palavrasNoTexto++;
                str = str.replaceAll("[^a-zZ-Z1-9 ]", "");

                if(str.isEmpty() || str.equals(" ")){
                    continue;
                }
                if(!stopWords.contains(str)){
                    if(!palavras.contains(str)){
                        palavras.add(str);
                    }
                    palavras.getOcorrencias(str).add(nPagina);
                }else{
                    numeroDeStopWords++;
                }
            } while (true);
        } while (true);
        arquivo.close();
        System.out.println("""
                ============================================
                Arquivo lido com sucesso!
                ============================================
                """);
    }
}
