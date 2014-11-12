package goblindungeon;

/**
 * Enemies randomly appear in rooms to impede the player's progress. Their 
 * strength determines how difficult they are to defeat.
 * @author Miles Bryant
 * @version 11.11.2014
 */
public class Enemy extends GameObject {

    /**
     * Creates a new Enemy with the specified attributes.
     * @param name short name to refer to the enemy (e.g. troll)
     * @param description longer description
     * @param rarity the likelihood of this enemy being chosen for a room 
     * (0.0 never appears - 1.0 always appears)
     * @param strength how difficult this enemy is to defeat: higher strength 
     * will increase damage done to player HP in battle
     */
    public Enemy(String name, String description, float rarity, int strength) {
        super(name,description,rarity);
        this.strength = strength;
    }
    
    private final int strength;

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

}
