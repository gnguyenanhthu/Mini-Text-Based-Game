public class Monster {
    private int monsterID;
    private int initRoomID;
    private String name;
    private String description;
    private int atkDmg;
    private int monsterHp;
    private int threshold;
    private int range;

    public Monster(int monsterID, int initRoomID, String name, String description, int atkDmg, int monsterHp, int threshold, int range) {
        this.monsterID = monsterID;
        this.initRoomID = initRoomID;
        this.name = name;
        this.description = description;
        this.atkDmg = atkDmg;
        this.monsterHp = monsterHp;
        this.threshold = threshold;
        this.range = range;
    }

    public int getMonsterID() { return monsterID; }

    public int getInitRoomID() { return initRoomID; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public int getAtkDmg() { return atkDmg; }

    public int getMonsterHp() { return monsterHp; }

    public int getThreshold() { return threshold; }

    public int getRange() { return range; }

    public void setMonsterHp(int monsterHp) {
        if (monsterHp < 0)
            this.monsterHp = 0;
        else
            this.monsterHp = monsterHp;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monsterID=" + monsterID +
                ", initRoomID=" + initRoomID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", atkDmg=" + atkDmg +
                ", threshold=" + threshold +
                ", range=" + range +
                '}';
    }
}
