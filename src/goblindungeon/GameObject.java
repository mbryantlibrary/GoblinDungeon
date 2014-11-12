package goblindungeon;

/**
 * This class stores attributes about a single game object, i.e. things  randomly
 * found in rooms.
 * @author Miles
 * @version 11.11.2014
 */
public abstract class GameObject {
    // a short object name (e.g. healing) and longer description for when found.
    protected final String name,description;
    // probability of this object being selected in a room, from
    // 0.0 (object will never occur) to 1.0 (object will always occur).
    protected final float rarity;
    
    /*
     * Creates a new GameObject with specified name, description and rarity.
     */
    GameObject(String name, String description, float rarity) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @return the rarity
     */
    public float getRarity() {
        return rarity;
    }
    
    /**
     * Provides a string representation of the GameObject, with name: description.
     * @return 
     */
    @Override
    public String toString() {
        return getName() + ": " + getDescription();
    }
}
