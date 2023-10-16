import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
Player class has player's name, a map and current room
Methods:
    moveNorth/East/South/West: move to appropriate direction of the room
    checkVisited: if already visited -> say familiar
                  if not visited -> set to visited
    checkAllVisited: check if the player has visited all rooms/explored whole map
                     print leftover rooms if exist
 */
public class Player {
    private String playerName;
    private Map myMap;
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private static int maxHP = 100;
    private int playerHP;
    private int playerATK;
    private ArrayList<Equipment> equipList = new ArrayList<>();

    public Player(String playerName, Map map) {
        this.playerName = playerName;
        this.myMap = map;
        this.currentRoom = myMap.getRoom(1);
        currentRoom.setVisited(true);
        playerHP = maxHP;
        playerATK = 20;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /*Check if current region is visited
    If already visited -> say familiar
    If not visited -> set to visited
    */
    public void checkVisited(){
        if (currentRoom.isVisited())
            System.out.println("This Region looks familiar.");
        else currentRoom.setVisited(true);
    }

    public void moveNorth(){
        if (currentRoom.getNorthRoom() == 0)
            System.out.println("You cannot go this way. Please choose another direction!");
        else {
            setCurrentRoom(myMap.getRoom(currentRoom.getNorthRoom()));
            checkVisited();
        }
    }

    public void moveEast(){
        if (currentRoom.getEastRoom() == 0)
            System.out.println("You cannot go this way. Please choose another direction!");
        else {
            setCurrentRoom(myMap.getRoom(currentRoom.getEastRoom()));
            checkVisited();
        }
    }

    public void moveSouth(){
        if (currentRoom.getSouthRoom() == 0)
            System.out.println("You cannot go this way. Please choose another direction!");
        else {
            setCurrentRoom(myMap.getRoom(currentRoom.getSouthRoom()));
            checkVisited();
        }
    }

    public void moveWest(){
        if (currentRoom.getWestRoom() == 0)
            System.out.println("You cannot go this way. Please choose another direction!");
        else {
            setCurrentRoom(myMap.getRoom(currentRoom.getWestRoom()));
            checkVisited();
        }
    }

    public void checkAllVisited(){
        ArrayList<Room> notVisited = new ArrayList<>();
        for (int i = 1; i <= myMap.getNumberOfRooms(); i++){
            if(!myMap.getRoom(i).isVisited())
                notVisited.add(myMap.getRoom(i));
        }
        if (notVisited.isEmpty())
            System.out.println("Congratulations! You have visted all regions.");
        else{
            System.out.println("These are regions you have not visited: ");
            notVisited.forEach((n) -> System.out.println(n.getRoomID() + " " + n.getRoomName() ));
        }
    }

    //When player enter a room, they will see room information, items and puzzles
    //Player has to solve the puzzle before picking up item
    public void enterRoom(){
        displayLocation();
        displayPuzzle();
    }

    //Print the current room information and items in that room
    public void displayLocation(){
        System.out.println("\n---------------");
        System.out.println("You are at Region " + currentRoom.getRoomID() + ", " + currentRoom.getRoomName());
        if (!currentRoom.getItemList().isEmpty())
            currentRoom.displayItemList();
        System.out.println("---------------\n");
    }

    public void displayInventory(){
        if (inventory.isEmpty()) {
            System.out.println("\n***************");
            System.out.println("You didn't pickup any items yet");
            System.out.println("***************\n");
        }
        else {
            System.out.println("\n***************");
            System.out.println("Inventory: " + inventory);
            System.out.println("***************\n");
        }
    }

    public void addToInv(Item item){
        inventory.add(item);
        Collections.sort(inventory);
    }

    public void removeFromInv(Item item){
        inventory.remove(item);
    }

    //Find an item in inventory
    public Item findItem(String itemName){
        Item item = null;
        for (Item i : inventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                item = i;
                break;
            }
        }
        return item;
    }

    public void pickUpItem(String itemName){
        Item item = null;
        for (Item i : currentRoom.getItemList()){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                item = i;
                break;
            }
        }
        if (item!=null) {
            addToInv(item);
            currentRoom.removeItem(item);
            System.out.println("You have picked up: " + item.getItemName());
        }
        else System.out.println("There is no such item in this room.");
        displayLocation();
    }

    public void dropItem(String itemName){
        Item item = findItem(itemName);
        if (item!=null) {
            if (item instanceof Equipment && (equipList.contains((Equipment)item))){
                System.out.println("You're equipping this item, take off before dropping it.");
            }
            else {
                removeFromInv(item);
                currentRoom.addItem(item);
                Collections.sort(currentRoom.getItemList());
                System.out.println("You dropped: " + item.getItemName());
            }
        }
        else System.out.println("You don't have this item in your inventory.");
        displayLocation();
    }

    public void inspectItem(String itemName){
        Item item = findItem(itemName);
        if (item!=null) {
            System.out.println(item.getItemDescription());
        }
        else System.out.println("You don't have this item in your inventory.");
    }

    public void equipItem(String itemName){
        Item item = findItem(itemName);
        if (item!=null) {
            if ((item instanceof Equipment)){
                Equipment equipment = (Equipment) item;
                if (equipList.contains(equipment)) {
                    System.out.println("You have already equip this weapon!");
                }
                else {
                    playerATK += equipment.getAtkValue();
                    equipList.add(equipment);
                    System.out.println("You successfully equip " + equipment.getItemName() + ". Your ATK increased " + equipment.getAtkValue());
                }
            }
            else
                System.out.println("This item is not an equipment.");
        }
        else System.out.println("You don't have this item in your inventory.");
    }

    public void unequipItem(String equipmentName) {
        Equipment equipment = null;
        for (Equipment e : equipList) {
            if (e.getItemName().equalsIgnoreCase(equipmentName)) {
                equipment = e;
                break;
            }
        }
        if (equipment != null) {
            playerATK -= equipment.getAtkValue();
            equipList.remove(equipment);
            System.out.println("You successfully take off " + equipment.getItemName() + ". Your ATK decreased " + equipment.getAtkValue());
        } else
            System.out.println("You didn't equip this item or this item is not an equipment.");
    }

    public void displayCommand(){
        System.out.println("\n--------------HELP MENU--------------");
        System.out.printf("| %2s %5s %-10s %10s \n", "n/north", "", "Move North","|");
        System.out.printf("| %2s %6s %-10s %10s \n", "e/east","","Move East","|");
        System.out.printf("| %2s %5s %-13s %7s \n", "s/south","", "Move South","|");
        System.out.printf("| %2s %6s %-13s %7s \n", "w/west","","Move West","|");
        System.out.printf("| %2s %3s %-10s %5s \n", "inventory","", "Check inventory","|");
        System.out.printf("| %2s %6s %-10s %8s \n", "pickup","","Pick up item","|");
        System.out.printf("| %2s %8s %-10s %10s \n", "drop","","Drop item","|");
        System.out.printf("| %2s %5s %-13s %7s \n", "inspect","","Inspect item","|");
        System.out.printf("| %2s %7s %-13s %7s \n", "equip","","Equip item","|");
        System.out.printf("| %2s %5s %-13s %7s \n", "takeoff","","Unequip item","|");
        System.out.printf("| %2s %4s %-10s %6s \n", "location","","Check location","|");
        System.out.printf("| %2s %7s %-10s %6s \n", "stats","","Check HP & ATK","|");
        System.out.printf("| %2s %9s %-10s %7s \n", "map","","Check the map","|");
        System.out.printf("| %2s %8s %-10s %10s \n", "help","","Help menu","|");
        System.out.println("-------------------------------------\n");
    }

    public void displayPuzzle(){
        if(currentRoom.getPuzzle()!=null){
            int attempts = currentRoom.getPuzzle().getNumbOfAttempts();
            Scanner input = new Scanner(System.in);
            for (int i = attempts; i > 0; i--){
                System.out.println(currentRoom.getPuzzle().getQuestion());
                String answer = input.nextLine();
                if (answer.equalsIgnoreCase(currentRoom.getPuzzle().getAnswer())){
                    System.out.println("You solved the puzzle correctly! " + currentRoom.getPuzzle().getSolvedMessage());
                    currentRoom.setPuzzle(null);
                    break;
                }
                else{
                    if ((i-1) > 0)
                        System.out.println("The answer you provided is wrong, you still have " + (i-1) +" attempts. Try one more time");
                    else
                        System.out.println("No more attempts! Please comeback later.");
                }
            }
        }
    }

    public void displayStats(){
        System.out.println("\n-------Player's Stats-------");
        System.out.println("Player name: " + playerName);
        System.out.println("Health point (HP): " + playerHP + "/" + maxHP);
        System.out.println("Attack (ATK): " + playerATK);
        System.out.println("----------------------------\n");
    }

    public void displayMap(){
        String notVisited = "";
        for (int i = 1; i <= myMap.getNumberOfRooms(); i++){
            if(!myMap.getRoom(i).isVisited())
                notVisited = notVisited + " " + String.valueOf(myMap.getRoom(i).getRoomID());
        }

        int[] currentMap = myMap.visualMap.clone();
        for (int i = 0; i < currentMap.length;i++){
            if (notVisited.contains("2") && (currentMap[i]==2 || currentMap[i]==20))
                currentMap[i] = 0;
            if (notVisited.contains("3") && (currentMap[i]==3 || currentMap[i]==30))
                currentMap[i] = 0;
            if (notVisited.contains("4") && (currentMap[i]==4 || currentMap[i]==40))
                currentMap[i] = 0;
            if (notVisited.contains("5") && (currentMap[i]==5 || currentMap[i]==50))
                currentMap[i] = 0;
            if (notVisited.contains("6") && (currentMap[i]==6 || currentMap[i]==60))
                currentMap[i] = 0;
        }

        int[][] array = new int [4][10];
        int index=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = currentMap[index];
                ++index;
            }
        }
        System.out.println("This is the map: ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (array[i][j]==0)
                    System.out.print(" ");
                else if (array[i][j] > 6)
                    System.out.print("*");
                else System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

}
