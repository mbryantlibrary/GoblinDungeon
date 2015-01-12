package goblindungeon;

import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.HashMap;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * Modified to use the 'Directions' enum instead of string literals; setExit now
 * assigns this room to it's neighbours exit too; also rooms
 * now randomly contain items or enemies to fight.
 * 
 * @author  Miles Bryant
 * @version 11.11.2014
 */

public class Room 
{
    
    private final String description;
    private final HashMap<Directions, Room> exits; // stores exits of this room.
    private final BufferedImage image; //image used in UI to show this room
    
    //the item this room contains. Set to null if this room has no item.
    protected Item item; 
    //likewise, with enemy.
    protected Enemy enemy;

    public BufferedImage getImage() {
        return image;
    }

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard". An item is randomly added (according to 
     * Data.P_ITEMINROOM), and if not an enemy may be randomly added
     * (according to Data.P_ENEMYINROOM).
     * @param description The room's description.
     * @param image BufferedImage to be displayed in UI. Will be resized to fit.
     */
    public Room(String description, BufferedImage image) {
        this.description = description;
        exits = new HashMap<>();
        Random rand = new Random();
        
        if(rand.nextBoolean()) {
            if(rand.nextFloat()< Data.P_ITEMINROOM) {
                item = Data.getRandomItem(); //if this room has an item, add a random one
            } 
        } else if(rand.nextFloat() < Data.P_ENEMYINROOM) {
            enemy = Data.getRandomEnemy();
        }
        
        this.image = image;
    }
    
    /**
     * Check whether a room contains an item.
     * @return true if a room has an item
     */
    public boolean hasItem() {
        return getItem() != null;
    }
    
    /**
     * Check whether a room contains an enemy.
     * @return true if a room has an enemy
     */
    public boolean hasEnemy() {
        return getEnemy() != null;
    }
    
    /**
     * Remove the enemy from the room (e.g. if it is killed)
     */
    public void removeEnemy() {
        enemy = null;
    }

    /**
     * @return the item
     */
    Item getItem() {
        return item;
    }

    /**
     * @return the enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }

    void removeItem() {
        item = null;
    }
    
    

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(Directions direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
        //also add this room to neighbour's exits
        if(neighbor.getExit(direction.getOpposite()) == null)
            neighbor.setExit(direction.getOpposite(), this);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + "\n";
    }

    /**
     * Return the room that is reached if we go from this room according to the
     * Directions enum.
     * @param direction Direction to get exit of.
     * @return The room in the given direction.
     */
    public Room getExit(Directions direction) 
    {
        return exits.get(direction);
    }
    
}

