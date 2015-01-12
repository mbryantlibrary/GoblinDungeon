package goblindungeon;

import java.util.ArrayList;

/**
 * The Player class stores information about the player currently playing the 
 * game, including number of lives (to be implemented), current health, and 
 * items currently held by the player.
 * @author Miles Bryant
 * @version 11.11.2014
 */
public class Player {
    
    //amount of health this player has. The player loses a life when this reaches 0.
    private int HP = Data.MAX_PLAYER_HEALTH;
    //current items this player is holding.
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Gets the current item inventory of the player.
     * @return ArrayList of items.
     */
    ArrayList<Item> getItems() {
        return items;
    }
    
    /**
     * Adds an item to the players inventory, e.g. if the player finds one in a 
     * room.
     * @param item 
     */
    void addItem(Item item) {
        items.add(item);
    }
    
    Item getItem(int ID) {
        return items.get(ID);
    }
    
    void removeItem(Item item) {
        items.remove(item);
    }
    
    int getNumberOfItems() {
        return getItems().size();
    }
    
    /**
     * Selects the player's best weapon (in terms of damage). If the player does
     * not have any weapons, null is returned.
     * @return
     */
    public Weapon getBestWeapon() {
        Weapon bestWeap = null; //running count of best weapon
        //loop through items and check if they are weapons
        for(Item item : items) {
            if(item.getClass() == Weapon.class) {
                if(bestWeap == null) 
                    //no best weapon yet, so make this one the first
                    bestWeap = (Weapon)item;
                else if (((Weapon)item).getDamage() > bestWeap.getDamage())
                    //this weapon is better!
                    bestWeap = (Weapon)item;
            }
        }
        return bestWeap;
    }

    /**
     * @return the current HP of the player.
     */
    public int getHP() { return HP; }

    /**
     * @param HP the HP to add
     * @return whether HP was successfully added
     */
    public int addHP(int HP) {
        if(HP < 0)
            throw new IllegalArgumentException("HP " + HP + " must be positive.");
        if(this.HP == Data.MAX_PLAYER_HEALTH) {
            //player already at maximum health, let's warn them not to waste 
            //health packs!
            return 0;
        } 
        if(this.HP + HP > Data.MAX_PLAYER_HEALTH) {
            //make sure maximum health hasn't been exceeded
            int healedAmount = Data.MAX_PLAYER_HEALTH - this.HP;
            this.HP = Data.MAX_PLAYER_HEALTH;
            return healedAmount;            
        }
        this.HP += HP;
        return HP;
    }
    
    /**
     * Takes HP from the player, e.g. in battle.
     * @param HP to remove
     * @return whether player is dead
     */
    public boolean removeHP(int HP) {
        if(HP < 0)
            throw new IllegalArgumentException("HP " + HP + " must be positive.");
        if(this.HP == 0)
            throw new RuntimeException("Player is already dead, cannot remove HP!");
        this.HP -= HP;
        return this.HP > 0;
    }
    

    /**
     * Clears player data, e.g. if game is restarted.
     */
    public void clear() {
        items.clear(); HP = Data.MAX_PLAYER_HEALTH;
    }
    
    
}
