public class Hash {

    public Hash() {
        return;
    }

    public static int hashFunction(String name) {
        String newName = name.toLowerCase().strip();
        int[] finalInt = new int[name.length()];
        for (int i = 0; i < newName.length(); i++) {
            finalInt[i] = newName.charAt(i) - 31;
        }
        long add = 0;
        long sub = 0;
        long mult = 1;
        for (int i = 0; i < finalInt.length; i++) {
            if (i % 3 == 0) {
                mult *= finalInt[i];
            } else if (i % 2 == 0) {
                add += finalInt[i];
            } else {
                sub -= finalInt[i];
            }
        }
        return (int) (((((345 * add) - (190 * sub)) + 178 * mult) / 10 + 3 * add - 23 * sub) % 500);
    }
}