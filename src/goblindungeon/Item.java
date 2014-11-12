package goblindungeon;

/**
 * An Item in the game generally has an effect (e.g. extra HP or extra life), or
 * may be a weapon. Rooms randomly contain these, and once found they are added 
 * to the player's inventory.
 * @author Miles
 * @version 11.11.2014
 */
abstract class Item extends GameObject {
    private String usecommand = "";
    
    public abstract boolean doEffect();
    /**
     * Creates a new Item with the specified attributes. Can only be accessed
     * from subclasses.
     * @param name short name to refer to the object (e.g. healing powder)
     * @param description longer description
     * @param usecommand the command second word that uses this object
     * @param rarity the likelihood of this item being in a room 
     * (0.0 never appears - 1.0 always appears)
     */
    protected Item(String name, String description, String usecommand, float rarity) {
        super(name,description,rarity);
        this.usecommand = usecommand;
    }


    public String getUseCommand() {
        return usecommand;
    }

}
