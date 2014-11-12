package goblindungeon;

import java.util.ArrayList;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

/**
 * The Output class produces the screen output of the game, being called by the
 * main Game class. Although these methods could be located in Game, it reduces
 * the size of that class and provides easy editing capabilities to add new 
 * content, e.g. if we wanted to randomly vary descriptions or add new languages.
 * This class does not care or have access to the current game state, or any 
 * rooms/enemies/items; String descriptions of these are provided to it.
 * 
 * This abstraction also ensures easy extensibility if, e.g. we wanted a GUI.
 * 
 * Like Game, it is also static as only one output is needed.
 * @author Miles
 */
public class Output {
    
    /**
     * Print out the opening message for the player.
     * @param startingRoomDescription String description of the starting room.
     * Will be printed after "You are in: ", so e.g. "a dark, musty hallway" 
     * is appropriate.
     */
    
    /**
     * Prints the first message of the game.
     * @param startingRoomDescription Long description of the first room.
     */
    public static void printWelcome(String startingRoomDescription) {
        if(startingRoomDescription.isEmpty()) //we need a starting room!
            throw new ValueException("Starting room description not provided.");
        
        System.out.println();
        System.out.println("Welcome to the Goblin Dungeon!");
        System.out.println("You have come here after hearing rumours of vast");
        System.out.println("treasures lurking deep in this terrible fortress");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        printGameDesc(startingRoomDescription,Data.MAX_PLAYER_HEALTH);
    }
    
    /**
     * Prints a description for the current room.
     * @param roomDesc Description of current room.
     * @param health HP of player.
     */
    public static void printGameDesc(String roomDesc, int health) {
        System.out.println(roomDesc);
        printHP(health);
    }
    
    
    public static void printUnknownCommand() {
        System.out.println("I don't know what you mean...");
    }
    
    /**
     * Prints a message when the player reaches the goal room.
     */
    public static void printWinMessage() {
        System.out.println("Congratulations, you found the treasure!");
    }
    
    /**
     * Print out a message if the player quits the game before it is finished.
     */
    public static void printQuit() {
        System.out.println();
        System.out.println("Thanks for playing.");
    }
    
    /**
     * Prints a message if the player types anything after 'quit'.
     */
    public static void printQuitWhat() {
        System.out.println("Quit what?");
    }
    
    /**
     * Print out some help information.
     * @param commandWords String containing list of commands, from the parser, 
     * e.g. "MOVE QUIT HELP".
     */
    public static void printHelp(String commandWords) 
    {
        System.out.println("You are a goblin in a dungeon. Your task is to ");
        System.out.println("find the room with the treasure.");
        System.out.println();
        System.out.println("You will encounter fearsome foes along the way.");
        System.out.println("Pick up weapons to counter these enemies and minimise damage.");
        System.out.println();
        System.out.println("But be careful, if you lose all your HP you will die!");
        System.out.println();
        System.out.println("Type items to show all the items you are holding.");
        System.out.println("To use a health pack, type use health.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(commandWords);
    }
    
    /**
     *
     */
    public static void printMaxHealth() {
        System.out.println("You might want to save that health!");
    }
    
    /**
     *
     * @param healing
     */
    public static void printUsedHealth(String healing) {
        System.out.println("You healed " + healing + "HP.");
    }
    
    /**
     *
     * @param itemname
     */
    public static void printUseItem(String itemname) {
        System.out.println("You used " + itemname);
    }
    
    /**
     *
     */
    public static void printNoItems() {
        System.out.println("You have no items...");
    }
    
    /**
     *
     * @param itemcmd
     */
    public static void printDoesntHaveItem(String itemcmd) {
        System.out.println("You do not have " + itemcmd + "!");
    }
    
    /**
     *
     * @param weaponname
     */
    public static void printCantUseWeapon(String weaponname) {
        System.out.println("You swing the " + weaponname + " around wildly; "
                + "best save your energy for real foes!");
    }

    static void printHP(int HP) {
        System.out.println("Health: " + String.valueOf(HP));
    }
    
    static void printInventory(ArrayList<Item> items) {
        if(items.isEmpty()) {
            System.out.println("You have no items. Explore some more rooms "
                    + "to find some!");
        } else {
            System.out.println("Inventory: ");
            for(Item item : items) {
                System.out.println(item.toString());
            }
        }
        
    }

    static void printNothingToFight() {
        System.out.println("There is nothing to fight!");
    }

    static void printFlee() {
        System.out.println("You scurry back to the previous room as"
                    + " fast as you can. Phew, that was a close one!");
    }

    static void printNothingToFlee() {
        System.out.println("There is nothing to flee from, have courage!");
    }

    static void printMustFightOrFlee(String enemyname) {
        System.out.println("You must fight or flee the " + enemyname + "!");
    }

    static void printDontKnowWhereToGo() {
        System.out.println("Go where?");
    }

    static void printNoExit() {
        System.out.println("There is no door!");
    }

    static void printFoundItem(String item) {
        System.out.println("You found " + item);
    }

    static void printStartFight(String enemyname) {
        System.out.println("A wild " + enemyname + " has"
                        + " appeared! You must either flee to the previous room or fight!");
    }
    
    static void printFight(String enemyname) {
        System.out.println("You try to wrestle the " + enemyname + ""
                    + " with your bare hands.");
    }
    
    static void printFight(String enemyname, String weaponname) {
        System.out.println("You try to slay the " + enemyname + ""
                    + " with your " + weaponname + ".");
    }
    
    static void printKillEnemy(String enemyname, String damage) {
        System.out.println("You manage to kill the " + enemyname + ", "
                    + "but it does " + damage + "damage!");
    }
    
    static void printPlayerKilled(String enemyname, String damage) {
        System.out.println(
                    "You are brutally dismembered by the " + enemyname
                    + " (doing " + damage + " damage), and your body lies on the ground as a warning to"
                            + " future adventurers!"
            );
    }
}
