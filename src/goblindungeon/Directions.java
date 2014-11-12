
package goblindungeon;

/**
 * Stores a list of directions the game uses.
 * @author Miles Bryant
 * @version 11.11.2014
 */
public enum Directions {
    north,
    south,
    west,
    east,
    up,
    down;
    
    /*
     * Return the opposite direction to this one (for fleeing).
     */
    public Directions getOpposite() {
        if(this == north)
            return south;
        else if (this == south)
            return north;
        else if (this == west)
            return east;
        else if (this == east)
            return west;
        else if (this == up)
            return down;
        else if (this == down)
            return up;
        return null;
        
    }
}
