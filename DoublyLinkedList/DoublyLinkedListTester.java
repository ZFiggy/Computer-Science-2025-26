public class DoublyLinkedListTester {
    public static void main(String[] args) {
        DoublyLinkedList myList = new DoublyLinkedList();
        myList.add(Nucleotide.A);
        myList.add(Nucleotide.C);
        myList.add(Nucleotide.T);
        myList.add(Nucleotide.A);
        myList.add(Nucleotide.T);
        myList.add(Nucleotide.T);
        myList.add(Nucleotide.C);
        myList.add(Nucleotide.G);
        myList.add(Nucleotide.G);
        DoublyLinkedList newList = new DoublyLinkedList();
        newList.add(Nucleotide.T);
        newList.add(Nucleotide.G);
        newList.add(Nucleotide.A);
        newList.add(Nucleotide.A);
        System.out.println(myList.toString());
        myList.addSegmentToEnd(newList);
        System.out.println(myList);
    }
}
