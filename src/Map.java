import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Map class has Array List of Room and a default map
Read map that users select (1 or 2), otherwise choose default map
There are 2 methods:
    getRoom: return a room based on its roomID
    getNumberOfRooms: return total rooms in that map
 */


public class Map {
    private static final String defaultFile = "Map1.txt";
    ArrayList<Room> myMap = new ArrayList<>();
    ArrayList<Item> itemList = new ArrayList<>();
    public Map(){
        try {
            Scanner scan = new Scanner(new File(defaultFile));
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                int roomID = scan.nextInt();
                int northRoom = scan.nextInt();
                int eastRoom = scan.nextInt();
                int southRoom = scan.nextInt();
                int westRoom = scan.nextInt();
                String roomName = scan.next();
                boolean isVisited = Boolean.parseBoolean(scan.nextLine());
                myMap.add(new Room(roomID,roomName,northRoom,eastRoom,southRoom,westRoom,isVisited));
            }
            scan.close();
            scan = new Scanner(new File("Item.txt"));
            scan.useDelimiter(",");
            while(scan.hasNext()){
                int itemID = scan.nextInt();
                int initRoomID = scan.nextInt();
                String itemName = scan.next();
                String itemDescription = scan.nextLine();
                itemList.add(new Item(initRoomID,itemID,itemName,itemDescription));
            }
            scan.close();
            for (Item i : itemList){
                myMap.get(i.getInitRoomID()-1).addItem(i);
            }
//            System.out.println(itemList);
//            System.out.println(myMap.get(1));
//            System.out.println(myMap.get(3));

        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
    }

    public Map(int mapNumber){
        String mapName = "";
        switch (mapNumber){
            case 1:
                mapName = "Map1.txt";
                break;
            case 2:
                mapName = "Map2.txt";
                break;
            case 3:
                mapName = "TestMap.txt";
                break;
            default:
                mapName = defaultFile;
                break;
        }
        try {
            Scanner scan = new Scanner(new File(mapName));
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                int roomID = scan.nextInt();
                int northRoom = scan.nextInt();
                int eastRoom = scan.nextInt();
                int southRoom = scan.nextInt();
                int westRoom = scan.nextInt();
                String roomName = scan.next();
                boolean isVisited = Boolean.parseBoolean(scan.nextLine());
                myMap.add(new Room(roomID,roomName,northRoom,eastRoom,southRoom,westRoom,isVisited));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Room getRoom(int roomID){return myMap.get(roomID-1);} //roomID starts at 1

    public int getNumberOfRooms(){return myMap.size();} //total rooms

    @Override
    public String toString() {
        return "Map{" +
                "MyMap=" + myMap +
                '}';
    }
}
