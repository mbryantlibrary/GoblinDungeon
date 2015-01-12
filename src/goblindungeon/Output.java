package goblindungeon;

/**
 * The Output class provides the Strings required for many of the games dialogs
 * to keep the user informed of progress and choose options when required. Most
 * methods are split into Message() and Title() variants to provide appropriate
 * arguments for the dialogs - for option dialogs, a Buttons() method is also 
 * provided to supply text for the buttons.
 * @author Miles Bryant
 */
public class Output {
    
    /**
     * Print out the opening message for the player.
     * @param startingRoomDescription String description of the starting room.
     * Will be printed after "You are in: ", so e.g. "a dark, musty hallway" 
     * is appropriate.
     */
    
    /**
     * @return the welcome message.
     */
    public static String startMessage() {
        StringBuilder startmsg = new StringBuilder();
        startmsg.append("Welcome to the Goblin Dungeon!\n\n");
        startmsg.append("You have come here after hearing rumours of vast ");
        startmsg.append("treasures lurking deep in this terrible fortress.\n\n");
        startmsg.append("For help, click the ? in the bottom right.\n\n");
        startmsg.append("Click 'OK' to start!");
        return startmsg.toString();
    }
    /**
     * @return title for the welcome dialog.
     */
    public static String startTitle() { return "Welcome!"; }
    
    /**
     * @return a win message.
     */
    public static String winMessage() { 
        return ("Congratulations, you found the treasure!");
    }
    /**
     * @return title for the win dialog.
     */
    public static String winTitle() { return "You won!"; }
    /**
     * @return button texts for the win dialog.
     */
    public static Object[] winButtons() {
        final Object[] options = {"Play again","Quit"};
        return options;
    }
    /**
     * @return message for when the player flees a battle.
     */
    public static String fleeMessage() {
        return ("You scurry back to the previous room as"
                    + " fast as you can. Phew, that was a close one!");
    }
    /**
     * @return title for the dialog when player flees a battle.
     */
    public static String fleeTitle() { return "Fleeing"; }
    
    /**
     * @return a message informing player they cannot use a health item because
     * they already have full HP.
     */
    public static String maxHealthMessage() {
        return "You already have maximum health - might want to save that!";
    }
    /**
     * @return title for the dialog informing player they cannot use a health item
     * because they already have full HP.
     */
    public static String maxHealthTitle(){ return "Maximum health"; }
    /**
     * Returns a String message for the dialog informing player of how much HP they healed.
     * @param healing Int amount of HP the player healed when using a health item.
     * @return String for the dialog informing player of how much HP they healed.
     */
    public static String healedMessage(int healing) {
        return "You healed " + healing + "HP!";
    }
    /**
     * @return title for the dialog informing player of how much HP they healed.
     */
    public static String healedTitle() {
        return "Healed";
    }
    
    /**
     * Returns a String message for the dialog informing player of an item they found.
     * @param name String name of the item.
     * @param desc String description of the item.
     * @return String message.
     */
    public static String foundItemMessage(String name, String desc) {
        return ("You found " + name + ":\n\n" + desc);
    }
    /**
     * @return a String title for the dialog informing player of an item they found.
     */
    public static String foundItemTitle() { return "Found an item!"; }
    /**
     * Returns a String message for the dialog informing player when they encounter
     * an enemy.
     * @param enemyname Name of the enemy, e.g. troll.
     * @return String message.
     */
    public static String startFightMessage(String enemyname) {
        return ("A wild " + enemyname + " has"
                        + " appeared! You must either flee to the previous room or fight!");
    }
    /**
     * @return a String title for the dialog informing player when they encounter
     * an enemy.
     */
    public static String startFightTitle() {
        return ("Fight!");
    }
    /**
     * @return an Object array of Strings containing button texts for the start Fight dialog.
     */
    public static Object[] startFightButtons() {
        final Object[] options = {"Fight!","Flee!"};
        return options;
    }
    /**
     * @return String title for the dialog of when a player decides to fight.
     */
    public static String fightTitle() {
        return "Fighting...";
    }
    /**
     * Returns a String message for the dialog when a player decides to fight with
     * no weapons, i.e. bare hands.
     * @param enemyname Name of enemy
     * @return String message.
     */
    public static String fightMessage(String enemyname) {
        return ("You try to wrestle the " + enemyname + ""
                    + " with your bare hands.");
    }
    /**
     * Returns a String message for the dialog when a player decides to fight
     * with a weapon.
     * @param enemyname Name of enemy.
     * @param weaponname Name of weapon.
     * @return String message.
     */
    public static String fightMessage(String enemyname, String weaponname) {
        return ("You try to slay the " + enemyname + ""
                    + " with your " + weaponname + ".");
    }
    
    /**
     * Returns a String message for the dialog when a player defeats an enemy.
     * @param enemyname Name of enemy.
     * @param damage Damage done to the player's HP.
     * @return String message.
     */
    public static String killEnemyMessage(String enemyname, String damage) {
        return ("You manage to kill the " + enemyname + ", "
                    + "but it does " + damage + "damage!");
    }    
    /**
     * @return String title for the dialog when a player defeats an enemy.
     */
    public static String killEnemyTitle() { return "Killed enemy!"; }
    
    /**
     * returns a String message for the dialog when a player is killed by an enemy.
     * @param enemyname Name of enemy.
     * @param damage Damage done to player.
     * @return String message.
     */
    public static String playerKilledMessage(String enemyname, String damage) {
        return(
                    "You are brutally dismembered by the " + enemyname
                    + " (doing " + damage + " damage), and your body lies on the ground as a warning to"
                            + " future adventurers!"
            );
    }
    /**
     * @return String title for the dialog when a player is killed by an enemy.
     */
    public static String playerKilledTitle() {
        return "Killed in action!";
    }
    /**
     * @return Object array of Strings for button text of the dialog when a 
     * player is killed by an enemy.
     */
    public static Object[] playerKilledOptions() {
        Object[] options = {"Play again", "Quit"};
        return options;
    }
    
}
