import com.paulbesret.gameoflife.GridManager;

public class Main {

    private static int numberOfGenerations = 0;

    public static void main(String[] args) {
        // Run the game
        while(true){
            GridManager.run();
            numberOfGenerations++;
            System.out.println(numberOfGenerations);
        }
    }
}
