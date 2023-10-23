public class ListaOrdenadaDePalavras {

    // Classe interna 
    private class Palavra {
        public String s;
        public ListaDeOcorrencias listaOcorrencias;
        public Palavra next;    
        public Palavra(String str) {
            s = str;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();
        }

    }
    private Palavra head;
    private Palavra tail;
    private int count;
    public ListaOrdenadaDePalavras() {
        head = null;
        tail = null;
        count = 0;
    }
    public boolean isEmpty() {
        return (head == null);
    }
    public int size() {
        return count;
    }
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }
    public boolean add(String str) {
        Palavra novaPalavra = new Palavra(str);
        if (isEmpty()) {
            head = novaPalavra;
            tail = novaPalavra;
        } else if (str.compareTo(head.s) < 0) {
            novaPalavra.next = head;
            head = novaPalavra;
        } else {
            Palavra anterior = null;
            Palavra atual = head;
            while (atual != null && str.compareTo(atual.s) > 0) {
                anterior = atual;
                atual = atual.next;
            }
            if (anterior != null) {
                anterior.next = novaPalavra;
            }
            novaPalavra.next = atual;
            if (atual == null) {
                tail = novaPalavra;
            }
        }
        count++;
        return true;
    }

    public boolean contains(String str) {
        Palavra aux = head;
        while (aux != null) {
            if (aux.s.equals(str)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
    public String get(int i) {
        if (i < 0 || i >= count) {
            throw new IndexOutOfBoundsException();
        }
        Palavra aux = head;
        for (int j = 0; j < i; j++) {
            aux = aux.next;
        }
        return aux.s;
    }
    public ListaDeOcorrencias getOcorrencias(String str) {
        Palavra aux = head;
        while (aux != null) {
            if (aux.s.equals(str)) {
                return aux.listaOcorrencias;
            }
            aux = aux.next;
        }
        return null;
    }

    public String palavraComMaiorOcorrencia(){
        Palavra aux = head;
        Palavra maior = head;
        while(aux != null){
            if(aux.listaOcorrencias.size() > maior.listaOcorrencias.size()){
                maior = aux;
            }
            aux = aux.next;
        }
        return maior.s;
    }

    @Override
    public String toString() {
        String s = "";
        Palavra aux = head;
        while (aux != null) {
            s += aux.s + aux.listaOcorrencias + "\n";
            aux = aux.next;
        }
        return s;
    }

}
