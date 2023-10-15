public class Item implements Comparable <Item>{
    private int initRoomID;
    private int itemID;
    private String itemName;
    private String itemDescription;

    public Item(int initRoomID, int itemID, String itemName, String itemDescription) {
        this.initRoomID = initRoomID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public int getInitRoomID() {
        return initRoomID;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    @Override
    public String toString() {
        return "(" + itemID +
                ") " + itemName;
    }

    @Override
    public int compareTo(Item o) {
        if (itemID < o.itemID)
            return -1;
        else if (itemID > o.itemID)
            return 1;
        else return 0;
    }

//    @Override
//    public String toString() {
//        return "Item{" +
//                "itemID=" + itemID +
//                ", itemName='" + itemName + '\'' +
//                ", itemDescription='" + itemDescription + '\'' +
//                '}';
//    }
}
