public class HashTester {
    public static void main(String[] args) {
        String[] newString = { "zack", "jake", "mateo", "jackson", "xander", "evan", "waller", "james", "camille",
                "siona", "morgan", "felicia", "david", "mattin", "taj", "dylan", "asher", "quinn", "carter", "yari",
                "abracazabra" };
        int[] newInts = new int[newString.length];
        for (int i = 0; i < newString.length; i++) {
            newInts[i] = Hash.hashFunction(newString[i]);
            System.out.println(newInts[i]);
        }
        String[] names = {
                "Mateo Atluri",
                "Asher Butan",
                "Xander Cheuk",
                "Taj Clement",
                "Camille Condren",
                "Evan Daneshrad",
                "Felicia Duan",
                "Jake Effress",
                "Zachary Figlin",
                "James Graczyk",
                "David Hadi",
                "Quinn Harris",
                "Jackson Hubbard",
                "Siona Kirschner",
                "Dylan Martin",
                "Morgan Maynard",
                "Yari Milakin",
                "Waller Morton",
                "Andrew Stout",
                "Mattin Tasbihgoo",
                "Andrew Theiss",
                "Carter Tsao",
                "Rose Ananda",
                "Autrin Anousheh",
                "Joshua Bie",
                "Elsa Cheng",
                "Isabel Erlic",
                "Jojo Gott",
                "Connor Jun",
                "Jordan Kay",
                "James Klarin",
                "Judy Law",
                "Grayden Lichtman",
                "Runshi Liu",
                "Juan Lopez",
                "Henry Margolis",
                "Garret Morberg-Nguyen",
                "Kai Nantamanasikarn",
                "Remi O'Dell",
                "Emil Palmer",
                "Jaden Park",
                "Ryder Rufi",
                "Alice Shao",
                "Marco Silvera",
                "Samuel Tabib",
                "Shriya Vishwas",
                "Nick Waller",
                "Vikram Wright",
                "Alex Yang",
                "Ethan You",
                "Lucas Yu",
                "Jack Yuan",
                "Jayden Zepeda",
                "Lawrence Zhao",
                "Michael Zhao",
                "Olivia Zhu"
        };
        int[] nextInts = new int[names.length];
        for (int i = 0; i < names.length - 1; i++) {
            nextInts[i] = Hash.hashFunction(names[i]);
            System.out.println(nextInts[i]);
        }
        String[] output = new String[500];
        for (int i = 0; i < names.length; i++) {

            int nextIndex = Hash.hashFunction(names[i]);
            if (output[nextIndex] != null) {
                System.out.println("duplicateFound for name: " + names[i] + " at index:" + nextIndex);
            }
            output[nextIndex] = names[i];
        }
    }
}
