public class ListaDeOcorrencias {
    private class Node {
        public int numeroDaPagina;
        public Node next;    
        public Node(int n) {
            numeroDaPagina = n;
            next = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int count;

    // Metodos 
    public ListaDeOcorrencias() {
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
    public boolean add(int numPagina)  {
        if(isEmpty()){
            head = new Node(numPagina);
            tail = head;
            count++;
            return true;
        }
        else{
            Node aux = head;
            while(aux != null){
                if(aux.numeroDaPagina == numPagina){
                    return false;
                }
                aux = aux.next;
            }
            tail.next = new Node(numPagina);
            tail = tail.next;
            count++;
            return true;
        }
    }
    public boolean contains(int numPagina) {
        Node aux = head;
        while(aux != null){
            if(aux.numeroDaPagina == numPagina){
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
    
    @Override
    public String toString() {
        String s = "";
        Node aux = head;
        while(aux != null){
            s += " -> " + aux.numeroDaPagina;
            aux = aux.next;
        }
        return s;
    }
}
