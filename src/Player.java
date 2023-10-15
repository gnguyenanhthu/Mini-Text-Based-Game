import java.util.ArrayList;

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

    private ArrayList<Item> inventory = new ArrayList<>();

    public Player(String playerName, Map map) {
        this.playerName = playerName;
        this.myMap = map;
        this.currentRoom = myMap.getRoom(1);
        currentRoom.setVisited(true);
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

    //Print player's location
    public String location(){
        return "You are at Region " + currentRoom.getRoomID() + ", " + currentRoom.getRoomName();
    }

    public void displayInventory(){
        if (inventory.isEmpty())
            System.out.println("You didn't pickup any items yet");
        else
            System.out.println("Inventory: " + inventory);
    }

    public void addToInv(Item item){
        inventory.add(item);
    }

    public void removeFromInv(Item item){
        inventory.remove(item);
    }

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
    }

    public void dropItem(String itemName){
        Item item = findItem(itemName);
        if (item!=null) {
            removeFromInv(item);
            currentRoom.addItem(item);
            System.out.println("You dropped: " + item.getItemName());
        }
        else System.out.println("You don't have this item in your inventory.");
    }

    public void inspectItem(String itemName){
        Item item = findItem(itemName);
        if (item!=null) {
            System.out.println(item.getItemDescription());
        }
        else System.out.println("You don't have this item in your inventory.");
    }

}
