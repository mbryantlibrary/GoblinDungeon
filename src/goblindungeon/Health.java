package goblindungeon;

/**
 * This class represents health pickups, that when used increase the Player's HP.
 * @author Miles Bryant
 * @version 11.11.2014
 */
public class Health extends Item {
    
    /**
     * Creates a new Health object with the specified attributes.
     * @param name short name to refer to the object (e.g. healing powder)
     * @param description longer description
     * @param usecommand the command second word that uses this object
     * @param rarity the likelihood of this item being in a room 
     * (0.0 never appears - 1.0 always appears)
     * @param healing the amount of HP added to the player's health when this 
     * item is used.
     */
    public Health(String name, String description, String usecommand, float rarity, int healing) {
        super(
                name,
                description + " Heals " + String.valueOf(healing) + "HP.",
                usecommand,
                rarity
        );
        this.healing = healing;
    }
    
    private int healing = 0;
    
    
    /**
     * @return the amount of HP added to the player's health when this 
     * item is used.
     */
    public int getHealing() {
        return healing;
    }

    
    
}
