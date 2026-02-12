public class HeapTester {
    public static void main(String[] args) {
        HeapPQ heap = new HeapPQ<>();
        heap.add(10);
		heap.add( 6);
		heap.add(11);
		heap.add( 8);
		heap.add( 9);
		heap.add( 5);
		heap.add( 7);
        System.out.println(heap.peek());
        System.out.println(heap.removeMin());
        System.out.println(heap.toString());
        System.out.println(heap.isEmpty());
    }
}
