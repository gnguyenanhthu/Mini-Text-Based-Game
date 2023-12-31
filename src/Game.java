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
        Map MyMap = new Map();
//        try {
//            System.out.println("Which map would you like to try? (Default, 1, 2, 3)");
//            System.out.println("1. The World\n2. Solar System\n3. Your Test Map\n4. Default Map");
//            int mapNumber = input.nextInt();
//            MyMap = new Map(mapNumber);
//        }catch (InputMismatchException e){
//            MyMap = new Map();
//        }
//        input.nextLine();
        player1 = new Player(playerName,MyMap);
        //System.out.println("Finish loading map and player.");
        //System.out.println(MyMap); //See the whole map (Room ID, Name and Connections)
    }
    public void play(){
        System.out.println("Hello " + player1.getPlayerName() + "! ");
        player1.enterRoom();
        System.out.println("Which direction do you want to go? (N/E/S/W) Or type (Exit) to quit the game");
        String playerInput = input.nextLine();
        while (!playerInput.equalsIgnoreCase("exit")){
            if (playerInput.equalsIgnoreCase("n") || playerInput.equalsIgnoreCase("north")){
                player1.moveNorth();
                player1.enterRoom();
            }
            else if (playerInput.equalsIgnoreCase("e") || playerInput.equalsIgnoreCase("east")){
                player1.moveEast();
                player1.enterRoom();
            }
            else if (playerInput.equalsIgnoreCase("s") || playerInput.equalsIgnoreCase("south")){
                player1.moveSouth();
                player1.enterRoom();
            }
            else if (playerInput.equalsIgnoreCase("w") || playerInput.equalsIgnoreCase("west")){
                player1.moveWest();
                player1.enterRoom();
            }
            else if (playerInput.equalsIgnoreCase("location")){
                player1.displayLocation();
            }
            else if (playerInput.equalsIgnoreCase("explore")){
                player1.displayItem();
            }
            else if (playerInput.equalsIgnoreCase("help")){
                player1.displayCommand();
            }
            else if (playerInput.equalsIgnoreCase("inventory")){
                player1.displayInventory();
            }
            else if (playerInput.toLowerCase().contains("pickup")){
                String itemName = playerInput.substring(7,playerInput.length()); //split item name
                //System.out.println(itemName);
                player1.pickUpItem(itemName);
            }
            else if (playerInput.toLowerCase().contains("drop")){
                String itemName = playerInput.substring(5,playerInput.length()); //split item name
                //System.out.println(itemName);
                player1.dropItem(itemName);
            }
            else if (playerInput.toLowerCase().contains("inspect")){
                String itemName = playerInput.substring(8,playerInput.length()); //split item name
                //System.out.println(itemName);
                player1.inspectItem(itemName);
            }
            else if (playerInput.toLowerCase().contains("equip") && !playerInput.toLowerCase().contains("unequip")){
                String itemName = playerInput.substring(6,playerInput.length()); //split item name
                //System.out.println(itemName);
                player1.equipItem(itemName);
            }
            else if (playerInput.toLowerCase().contains("unequip")){
                String itemName = playerInput.substring(8,playerInput.length()); //split item name
                //System.out.println(itemName);
                player1.unequipItem(itemName);
            }
            else if (playerInput.toLowerCase().contains("use")){
                String itemName = playerInput.substring(4,playerInput.length()); //split item name
                //System.out.println(itemName);
                player1.useItem(itemName);
            }
            else if (playerInput.equalsIgnoreCase("stats")){
                player1.displayStats();
            }
            else if (playerInput.equalsIgnoreCase("map")){
                player1.displayMap();
            }
            else if (playerInput.equalsIgnoreCase("examine monster")){
                player1.examineMonster();
                if (player1.getPlayerHP() <= 0)
                    break;
            }
            else{
                System.out.println("Please enter correct command.");
            }
            System.out.println("Please input your command: ");
            playerInput = input.nextLine();
        }
        if (player1.getPlayerHP() > 0) {
            player1.checkAllVisited();
            System.out.println("Bye " + player1.getPlayerName() + " ~ Thank you for traveling around the world with us!");
        }
        else {
            System.out.println("What do you want to do?");
            System.out.println("1. Start new game (restart)");
            System.out.println("2. Exit");
            playerInput = input.nextLine();
            if (playerInput.equalsIgnoreCase("restart"))
                playAgain();
            else
                System.exit(0);
        }
    }

    public void playAgain(){
        Game game = new Game();
        game.start();
        game.play();
    }
}