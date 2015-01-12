package goblindungeon;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * A MoveAction is fired when the user moves between rooms. The Game instance
 * is notified.
 * @author Miles Bryant
 */
public class MoveAction extends AbstractAction {

    private final Directions dir; //direction user elects to move in
    
    /**
     * Creates a new MoveAction, specifying the direction which will be passed
     * to the Game instance when this action fires.
     * @param dir Direction user will move in. If this is an inappropriate direction,
     * i.e. the current room does not have an exit in this direction, an 
     * IllegalArgument exception will be thrown, so care must be taken to ensure
     * that the user can only move in the correct directions.
     */
    public MoveAction(Directions dir) {
        this.dir = dir;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Game.getInstance().goRoom(dir);
    }
}
