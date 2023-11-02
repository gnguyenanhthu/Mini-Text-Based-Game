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
    ArrayList<Puzzle> puzzleList = new ArrayList<>();
    ArrayList<Monster> monsterList = new ArrayList<>();
    int[] visualMap = new int[40];
    public Map() {
        loadRoom();
        System.out.println("Finish loading Room");
        //System.out.println(myMap);

        loadItem();
        loadEquipment();
        for (Item i : itemList) {
            myMap.get(i.getInitRoomID() - 1).addItem(i);
        }
        System.out.println("Finish adding Item");
        //System.out.println(itemList);

        loadPuzzle();
        for (Puzzle p : puzzleList) {
            myMap.get(p.getInitRoomID() - 1).setPuzzle(p);
        }
        System.out.println("Finish adding Puzzle");
        //System.out.println(puzzleList);

        loadMonster();
        for (Monster m : monsterList) {
            myMap.get(m.getInitRoomID() - 1).setMonster(m);
        }
        System.out.println("Finish adding Monster");
        //System.out.println(monsterList);

        loadVisualMap();
    }

    public void loadRoom(){
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
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    //Loading Item information from Item.txt
    public void loadItem(){
        try {
            Scanner scan = new Scanner(new File("Item.txt"));
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                int itemID = scan.nextInt();
                int initRoomID = scan.nextInt();
                String itemName = scan.next();
                String itemDescription = scan.nextLine();
                itemDescription = itemDescription.substring(1, itemDescription.length());
                itemList.add(new Item(initRoomID, itemID, itemName, itemDescription));
            }
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    //Loading Item information from Equipment.txt
    public void loadEquipment(){
        try {
            Scanner scan = new Scanner(new File("Equipment.txt"));
            scan.useDelimiter(",");
            while(scan.hasNext()){
                int itemID = scan.nextInt();
                int initRoomID = scan.nextInt();
                int atkValue = scan.nextInt();
                String itemName = scan.next();
                String itemDescription = scan.nextLine();
                itemDescription = itemDescription.substring(1,itemDescription.length());
                itemList.add(new Equipment(initRoomID,itemID,itemName,itemDescription,atkValue));
            }
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    //Loading Puzzle information from Puzzle.txt
    public void loadPuzzle(){
        try {
            Scanner scan = new Scanner(new File("Puzzle.txt"));
            while(scan.hasNext()){
                int puzzleID = scan.nextInt();
                String temp = scan.nextLine();
                int initRoomID = scan.nextInt();
                temp = scan.nextLine();
                int numberOfAttempts = scan.nextInt();
                temp = scan.nextLine();
                String question = scan.nextLine();
                String answer = scan.nextLine();
                String solvedMessage = scan.nextLine();
                String loseMessage = scan.nextLine();
                puzzleList.add(new Puzzle(puzzleID,initRoomID,numberOfAttempts,question,answer,solvedMessage,loseMessage));
            }
            scan.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    //Loading Puzzle information from Puzzle.txt
    public void loadMonster(){
        try {
            Scanner scan = new Scanner(new File("Monster.txt"));
            while(scan.hasNext()){
                int monsterID = scan.nextInt();
                String temp = scan.nextLine();
                int initRoomID = scan.nextInt();
                temp = scan.nextLine();
                String name = scan.nextLine();
                String description = scan.nextLine();
                int atkDmg = scan.nextInt();
                temp = scan.nextLine();
                int monsterHp = scan.nextInt();
                temp = scan.nextLine();
                int threshold = scan.nextInt();
                temp = scan.nextLine();
                int range = scan.nextInt();
                monsterList.add(new Monster(monsterID,initRoomID,name,description,atkDmg,monsterHp,threshold,range));
            }
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void loadVisualMap(){
        try {
            Scanner scan = new Scanner(new File("VisualMap.txt"));
            while(scan.hasNext()) {
                for (int i = 0; i < visualMap.length; i++) {
                    int number = scan.nextInt();
                    visualMap[i] = number;
                }
            }
//            for (int i = 0; i<visualMap.length;i++) {
//                System.out.print(visualMap[i] + " ");
//            }
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

//    Turn off choosing map function
//    public Map(int mapNumber){
//        String mapName = "";
//        switch (mapNumber){
//            case 1:
//                mapName = "Map1.txt";
//                break;
//            case 2:
//                mapName = "Map2.txt";
//                break;
//            case 3:
//                mapName = "TestMap.txt";
//                break;
//            default:
//                mapName = defaultFile;
//                break;
//        }
//        try {
//            Scanner scan = new Scanner(new File(mapName));
//            scan.useDelimiter(",");
//            while (scan.hasNext()) {
//                int roomID = scan.nextInt();
//                int northRoom = scan.nextInt();
//                int eastRoom = scan.nextInt();
//                int southRoom = scan.nextInt();
//                int westRoom = scan.nextInt();
//                String roomName = scan.next();
//                boolean isVisited = Boolean.parseBoolean(scan.nextLine());
//                myMap.add(new Room(roomID,roomName,northRoom,eastRoom,southRoom,westRoom,isVisited));
//            }
//            scan.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public Room getRoom(int roomID){return myMap.get(roomID-1);} //roomID starts at 1

    public int getNumberOfRooms(){return myMap.size();} //total rooms

    @Override
    public String toString() {
        return "Map{" +
                "MyMap=" + myMap +
                '}';
    }
}
