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
    
    //number of lives the player has. The player dies when this reaches 0. TODO: implement lives
    private int lives = 1; 
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
    public int getHP() {
        return HP;
    }

    /**
     * @param HP the HP to add
     * @return whether HP was successfully added
     */
    public boolean addHP(int HP) {
        if(this.HP == Data.MAX_PLAYER_HEALTH) {
            //player already at maximum health, let's warn them not to waste 
            //health packs!
            Output.printMaxHealth();
            return false;
        } 
        this.HP += HP;
        if(this.HP > Data.MAX_PLAYER_HEALTH)
            //make sure maximum health hasn't been exceeded
            this.HP = Data.MAX_PLAYER_HEALTH;
        return true;
    }
    
    /**
     * Takes HP from the player, e.g. in battle.
     * @param HP to remove
     * @return whether player is dead
     */
    public boolean removeHP(int HP) {
        if(this.HP == 0)
            throw new RuntimeException("Player is already dead, cannot remove HP!");
        this.HP -= HP;
        return this.HP > 0;
    }
    
    /**
     * Uses an item in the player's inventory.
     * @param itemCmd the useCommand of the Item to use.
     */
    public void useItem(String itemCmd) {
        if(getItems().isEmpty()) {
            // cannot use any items since we don't have any!
            Output.printNoItems();
            return;
        }
        //loop through items and see if any of them match the use command
        for(Item item : getItems()) {
            if(itemCmd.equals(item.getUseCommand())) {
                if(item.doEffect()) //if item gets used up, remove it
                    getItems().remove(item);
                Output.printUseItem(item.getName());
                return; //found the item and used it, let's stop processing
            }
        }
        //went through all the items and didn't find it, so player doesn't have it
        Output.printDoesntHaveItem(itemCmd);
    }
    
    
}
