public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E>{

	
	@Override
	public boolean contains(E obj) {
		int index = Utils.binarySearchRecursiveHelper(super.internalArray, obj, 0, super.internalArray.length - 1);
		if (index != -1) {
			return true;
		}
		return false;
	}
	
	//May not contain more than one of the same object
	@Override
	public boolean add(E obj) {
		if (contains(obj)) {
			return false;
		}
		int insert = Utils.findInsertion(internalArray, obj, 0, super.internalArray.length - 1);
		super.add(insert, obj);
		return true;
	}
	
	@Override
	public boolean remove(E obj) {
		return super.remove(obj);
	}
	
	public E min() {
		return super.internalArray[0];
	}
	
	public E max() {
		return super.internalArray[super.internalArray.length - 1];
	}
}
