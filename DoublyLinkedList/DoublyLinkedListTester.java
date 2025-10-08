public class DoublyLinkedListTester {
    public static void main(String[] args) {
        DoublyLinkedList myList = new DoublyLinkedList();
        myList.add(Nucleotide.A);
        myList.add(Nucleotide.A);
        myList.add(Nucleotide.T);
        myList.add(Nucleotide.A);
        myList.add(Nucleotide.T);
        myList.add(Nucleotide.T);
        myList.add(Nucleotide.C);
        myList.add(Nucleotide.G);
        DoublyLinkedList newList = new DoublyLinkedList();
        newList.add(Nucleotide.T);
        newList.add(Nucleotide.A);
        newList.add(Nucleotide.T);
        newList.add(Nucleotide.T);
        System.out.println(myList);
        myList.deleteSegment(newList);
        System.out.println(myList);
        myList.remove(Nucleotide.T);
        myList.remove(Nucleotide.C);
        myList.remove(Nucleotide.G);
        myList.remove(Nucleotide.G);
        System.out.println(myList);
    }
}
