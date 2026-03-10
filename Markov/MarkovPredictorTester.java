public class MarkovPredictorTester {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(MarkovPrediction.predictNextState("Sunny", "weather.csv"));
            System.out.println(MarkovPrediction.predictNextState("Partly Cloudy", "weather.csv"));
            System.out.println(MarkovPrediction.predictNextState("Cloudy", "weather.csv"));
            System.out.println(MarkovPrediction.predictNextState("Sleeping", "activites.csv"));
            System.out.println(MarkovPrediction.predictNextState("Working", "activites.csv"));
            
        }
    }
}
