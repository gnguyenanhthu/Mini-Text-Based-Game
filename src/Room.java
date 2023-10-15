import java.util.ArrayList;

/*
Room class keeps track of room's attributes:
    roomID: unique room ID
    roomName: name of the room
    northRoom: connect to north room
    eastRoom: connect to east room
    southRoom: connect to south room
    westRoom: connect to west room
    isVisted: boolean to track if it is visited
 */
public class Room {
    private int roomID;
    private String roomName;
    private int northRoom;
    private int eastRoom;
    private int southRoom;
    private int westRoom;
    private boolean isVisited;

    private ArrayList<Item> itemList = new ArrayList<>();

    public Room(int roomID, String roomName, int northRoom, int eastRoom, int southRoom, int westRoom, boolean isVisited) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.northRoom = northRoom;
        this.eastRoom = eastRoom;
        this.southRoom = southRoom;
        this.westRoom = westRoom;
        this.isVisited = isVisited;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getNorthRoom() {
        return northRoom;
    }

    public void setNorthRoom(int northRoom) {
        this.northRoom = northRoom;
    }

    public int getEastRoom() {
        return eastRoom;
    }

    public void setEastRoom(int eastRoom) {
        this.eastRoom = eastRoom;
    }

    public int getSouthRoom() {
        return southRoom;
    }

    public void setSouthRoom(int southRoom) {
        this.southRoom = southRoom;
    }

    public int getWestRoom() {
        return westRoom;
    }

    public void setWestRoom(int westRoom) {
        this.westRoom = westRoom;
    }

    public ArrayList<Item> getItemList() { return itemList; }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public void addItem(Item item){
        itemList.add(item);
    }

    public void removeItem(Item item){
        itemList.remove(item);
    }

    public void displayItemList(){
        System.out.println("There are " + itemList + " in this room.");
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", roomName='" + roomName + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
