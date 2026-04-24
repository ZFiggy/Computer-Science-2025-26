public class FrequencyNode implements Comparable {

    private Character value;
    private int frequency;
    private FrequencyNode parent;
    private FrequencyNode left;
    private FrequencyNode right;
    private int binary;

    public FrequencyNode(Character value, int frequency, FrequencyNode parent, FrequencyNode left, FrequencyNode right, int binary) {
        this.value = value;
        this.frequency = frequency;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.binary = binary;
    }

    public FrequencyNode(Character value, int frequency) {
        this.value = value;
        this.frequency = frequency;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.binary = -1;
    }

    /**
     * @return the parent
     */
    public FrequencyNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(FrequencyNode parent) {
        this.parent = parent;
    }

    /**
     * @return the childOne
     */
    public FrequencyNode getLeft() {
        return left;
    }

    /**
     * @param childOne the childOne to set
     */
    public void setLeft(FrequencyNode left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public FrequencyNode getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(FrequencyNode right) {
        this.right = right;
    }

    /**
     * @return the binary
     */
    public int getBinary() {
        return binary;
    }

    /**
     * @param binary the binary to set
     */
    public void setBinary(int binary) {
        this.binary = binary;
    }

    public int compareTo(Object node) {
        if (this.frequency < ((FrequencyNode) node).getFrequency()) {
            return 1;
        } else if (this.frequency > ((FrequencyNode) node).getFrequency()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * @return the value
     */
    public Character getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Character value) {
        this.value = value;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
