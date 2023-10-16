public class Equipment extends Item{
   private int atkValue;

    public Equipment(int initRoomID, int itemID, String itemName, String itemDescription, int atkValue) {
        super(initRoomID, itemID, itemName, itemDescription);
        this.atkValue = atkValue;
    }

    public int getAtkValue() {
        return atkValue;
    }
}
