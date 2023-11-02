public class Consumable extends Item{
    private int hpValue;

    public Consumable(int initRoomID, int itemID, String itemName, String itemDescription, int hpValue) {
        super(initRoomID, itemID, itemName, itemDescription);
        this.hpValue = hpValue;
    }

    public int getHpValue() {
        return hpValue;
    }
}
