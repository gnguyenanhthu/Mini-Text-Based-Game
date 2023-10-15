import java.util.*;
/*
Main class controls all activities of the game
Methods:
    start: read player name and load their selected map
    play: receive direction from user and move to the right region
 */
public class Game {
    private Scanner input = new Scanner(System.in);
    Player player1;
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        game.play();
    }
    public void start(){
        System.out.println("Welcome to Text-based Around the World. Hi new traveler, please tell us your name:");
        String playerName = input.nextLine();
        Map MyMap;
        try {
            System.out.println("Which map would you like to try? (Default, 1, 2, 3)");
            System.out.println("1. The World\n2. Solar System\n3. Your Test Map\n4. Default Map");
            int mapNumber = input.nextInt();
            MyMap = new Map(mapNumber);
        }catch (InputMismatchException e){
            MyMap = new Map();
        }
        input.nextLine();
        player1 = new Player(playerName,MyMap);
        System.out.println("Finish loading map and player.");
        //System.out.println(MyMap); //See the whole map (Room ID, Name and Connections)
    }
    public void play(){
        System.out.println("Hello " + player1.getPlayerName() + "! " + player1.location());
        System.out.println("Which direction do you want to go? (N/E/S/W) Or type (Exit) to quit the game");
        String playerInput = input.nextLine();
        while (!playerInput.equalsIgnoreCase("exit")){
            if (playerInput.equalsIgnoreCase("n") || playerInput.equalsIgnoreCase("north")){
                player1.moveNorth();
            }
            else if (playerInput.equalsIgnoreCase("e") || playerInput.equalsIgnoreCase("east")){
                player1.moveEast();
            }
            else if (playerInput.equalsIgnoreCase("s") || playerInput.equalsIgnoreCase("south")){
                player1.moveSouth();
            }
            else if (playerInput.equalsIgnoreCase("w") || playerInput.equalsIgnoreCase("west")){
                player1.moveWest();
            }
            else{
                System.out.println("Please enter correct direction.");
            }

            System.out.println(player1.location());
            System.out.println("Which direction do you want to go? (N/E/S/W)");
            playerInput = input.nextLine();
        }
        player1.checkAllVisited();
        System.out.println("Bye " + player1.getPlayerName() + " ~ Thank you for traveling around the world with us!");
    }
}