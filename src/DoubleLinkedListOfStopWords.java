public class DoubleLinkedListOfStopWords {
    private class Node{
        private String element;
        private Node next;
        private Node prev;

        Node(String e) {
            this.element = e;
            this.next = null;
            this.prev = null;
        }
    }
    private Node header;
    private Node trailer;
    private Node current;
    private int count;

    DoubleLinkedListOfStopWords(){
        this.header = new Node(null);
        this.trailer = new Node(null);
        this.header.next = trailer;
        this.trailer.prev = header;
        this. count = 0;
    }

    public void add(String e){
        Node n = new Node(e);
        n.next = trailer;
        n.prev = trailer.prev;
        trailer.prev.next = n;
        trailer.prev = n;
        count++;
    }

    public String get(String str) {
        Node aux = header.next;
        for(int i = 0; i < count; i++){
            if(str.equals(aux.element)) return aux.element;
            aux = aux.next;
        }
        return "Não Encontrado";
    }

    public boolean contains(String element) {
        Node aux = header.next;
        for (int i = 0; i < count; i++) {
            if(element == null) continue;
            if (aux.element.equals(element)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    @Override
    public String toString(){
        String aux = "Stop Words \n";
        Node n = header.next;
        for(int i = 0; i < count; i++){
            aux += n.element + "\n";
            n = n.next;
        }
        return aux;
    }
    public int getCount(){
        return this.count;
    }
}
