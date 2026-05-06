public class FrequencyNode implements Comparable {

    private char value;
    private int frequency;
    private FrequencyNode parent;
    private FrequencyNode left;
    private FrequencyNode right;
    private String binary;

    public FrequencyNode(char value, int frequency, FrequencyNode parent, FrequencyNode left, FrequencyNode right, String binary) {
        this.value = value;
        this.frequency = frequency;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.binary = binary;
    }

    public FrequencyNode(char value, int frequency) {
        this.value = value;
        this.frequency = frequency;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.binary = "";
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
    public String getBinary() {
        return binary;
    }

    /**
     * @param binary the binary to set
     */
    public void setBinary(String binary) {
        this.binary = binary;
    }

    public int compareTo(Object node) {
        if (this.frequency < ((FrequencyNode) node).getFrequency()) {
            return -1;
        } else if (this.frequency > ((FrequencyNode) node).getFrequency()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @return the value
     */
    public char getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(char value) {
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
