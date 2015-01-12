package goblindungeon;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * This action is fired when the user uses a health pack, notifying the Game 
 * instance, providing it with the ID of the item being used (which corresponds 
 * to the index of the item in the player's inventory).
 * @author Miles Bryant
 */
public class HealthUsedAction extends AbstractAction {

    private final int ID;
    
    /**
     * Creates a new HealthUsedAction with a specified item ID.
     * @param ID 
     */
    public HealthUsedAction(int ID) {
        this.ID = ID;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Game.getInstance().useHealth(ID);
    }
    
}
