package goblindungeon;

import goblindungeon.ui.MainUI;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *  This is the main class for the GoblinGame, handling the game logic, the 
 *  interactions between different classes and the UI. To run, simply compile
 *  and run, or double click the attached 'GoblinGame.jar' Java executable file
 *  (TODO: provide executable).
 * 
 * @author  Miles Bryant
 * @version 10.11.2014
 */

public class Game {
    
    //STATIC FIELDS AND METHODS
    
    private static MainUI ui;
    private static final Game instance = new Game();
    
    /**
     * Entry point for executable.
     * @param args Command line arguments. Currently has no effect.
     */
    public static void main(String args[]) {
        try {
        Game.getInstance().initialise();
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
            System.exit(1);
        }
    }
    
    /**
     * Returns the instance of Game.
     * @return the single instance of Game.
     */
    public static Game getInstance() {
        return instance;
    }
    
    /**
     * Prevents Game from being constructed outside the class; maintains
     * singleton status
     */
    private Game() {}
    
    private Room currentRoom;   //Stores the room the player is currently in
    private Directions prevExit; //Stores the exit player previously came through for fleeing
    private final Player player = new Player();
    private boolean initialised = false;
    
    /**
     * Initialises the game and sets everything to default states, loading data
     * and showing the UI. Can only be run once otherwise an IllegalStateException
     * is thrown.
     * @throws java.io.IOException
     */
    public void initialise() throws IOException {
        if(initialised)
            // cannot initialise more than once!
            throw new IllegalStateException("Game already initialised");
        
        Data.load();
        
        ui = new MainUI();
        
        currentRoom = Data.getFirstRoom();
        
        initialised = true;
        
        //Set up UI control buttons
        getInstance().setUIexits();
        
        //Start the UI and show the welcome dialog
        ui.setVisible(true);        
        ui.showInfoDialog(
                Output.startMessage(), 
                Output.startTitle(), 
                JOptionPane.INFORMATION_MESSAGE
        );
        
        //Show first room description
        ui.setGameCommentary(currentRoom.getLongDescription());
        ui.setGameTitle("Health: " + player.getHP());
        ui.loadImage(currentRoom.getImage());
        
    }
    
    
    /** 
     * Try to go to the room in a specified direction.
     * @param direction Direction of room to go in; if room is not a valid exit
     * an IllegalStateException is thrown.
     */
    public void goRoom(Directions direction) 
    {        
        checkInit();
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        
        if (nextRoom == null) {
            //exit is not valid; the programmer should not allow user to select
            //an incorrect exit, so throw an IllegalStateException
            throw new IllegalStateException("Room in direction " + direction.name() + " is inaccessible.");
        } else if (nextRoom.getClass() == GoalRoom.class) {
            //Player has reached the goal room, show the win dialog
            ui.loadImage(nextRoom.getImage());
            if(ui.showChoiceDialog(Output.winMessage(), Output.winTitle(), Output.winButtons(), JOptionPane.INFORMATION_MESSAGE)) {
                //User decides to restart
                restartGame();
            } else {
                //User wants to exit; shut the game down
                System.exit(0);
            }
        } else {
            //Just a normal room
            currentRoom = nextRoom;
            prevExit = direction; //in case player has to flee from a fight
            
            if(currentRoom.hasItem()) {
                //player found an item
                findItem(currentRoom.getItem());
                currentRoom.removeItem();
            }
            
            if(currentRoom.hasEnemy()) {
                //Player has run into an enemy; give choice of fight or flee
                if(ui.showChoiceDialog(
                        Output.startFightMessage(currentRoom.getEnemy().getName()),
                        Output.startFightTitle(),
                        Output.startFightButtons(),
                        JOptionPane.WARNING_MESSAGE))
                {
                    doFight();
                } else {
                    doFlee();
                }
            }
        }
        
        setUIexits(); //set up new exits
        ui.setGameCommentary(currentRoom.getLongDescription());
        ui.setTitle("Health: " + player.getHP());
        
        ui.loadImage(currentRoom.getImage());
    }
    
    /**
     * Uses a health item; checks if it can be used (ie if player's health is 
     * below maximum) and notifies player accordingly, removing the health item 
     * from player inventory if appropriate. Throws an IllegalStateException if
     * Game is not initialised.
     * @param ID ID of the item to use - corresponds to matching item in player's 
     * inventory. Throws an IndexOutOfBounds exception if ID does not match up
     * to an item in player's inventory.
     */
    public void useHealth(int ID) {
        checkInit();
        
        Item item = player.getItem(ID);
        //Check if item is a health item; inappropriate to use it otherwise
        if(item.getClass() == Health.class) {
            Health h = (Health)item;
            if(player.getHP() == Data.MAX_PLAYER_HEALTH) {
                //Player already has maximum health, notify player
                ui.showInfoDialog(
                    Output.maxHealthMessage(),
                    Output.maxHealthTitle(),
                    JOptionPane.WARNING_MESSAGE
                );
            } else {
                // Add HP and get amount of healing done, notifying player.
                int heal = player.addHP(h.getHealing());
                ui.showInfoDialog(
                    Output.healedMessage(heal),
                    Output.healedTitle(),
                    JOptionPane.INFORMATION_MESSAGE
                );
                //Remove item from both player and UI, updating health.
                player.removeItem(h);
                ui.removeItem(ID);
                ui.setGameTitle("Health: " + player.getHP());
            }
        } else
            //Cannot use this item; programmer should not allow the user to use 
            //other items so throw an unchecked exception.
            throw new IllegalArgumentException("Item type " + item.getClass() + " is not Health; cannot use");   
    }
     
    
    
    /**
     * Restarts the game e.g. if player killed or reaches goal and wants to play
     * again
     */
    private void restartGame() {
        //reset everything
        currentRoom = null; prevExit = null;
        initialised = false;
        player.clear();
        ui.clear();
        try {
            initialise();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This is run at the beginning of every relevant public method to check that
     * initialise() has been run and data has been loaded. As it is up to the 
     * programmer to ensure this, an unchecked IllegalStateException is thrown.
     */
    private void checkInit() {
        if(!initialised)
            throw new IllegalStateException("Game is not initialised");
    }
    
    /**
     * Ensures only buttons of the directions player can go in are enabled. If
     * currentRoom is not set 
     */
    private void setUIexits() {
        checkInit();
        for(Directions dir : Directions.values())
            ui.setExit(dir, currentRoom.getExit(dir) != null);
    }
    
    /**
     * This is run when an item is found; it adds it to the player's inventory,
     * to the UI and notifies the player through a dialog.
     * @param item Item that has been found (normally currentRoom.getItem())
     */
    private void findItem(Item item) {
        player.addItem(item); //add to inventory
        //add to appropriate UI box, depending on whether item is a weapon or not
        if(item.getClass() == Weapon.class)
            ui.addWeapon(player.getNumberOfItems() - 1, item.getName());
        else
            ui.addItem(player.getNumberOfItems() - 1, item.getName());
        //advise player that an item has been found
        ui.showInfoDialog(
                Output.foundItemMessage(item.getName(), item.getDescription()), 
                Output.foundItemTitle(), 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static int battleDamage(int enemyStrength, int weaponStrength) {
        Random rand = new Random();
        return (int) Math.abs(Math.round((double)enemyStrength - (double)weaponStrength) + (rand.nextGaussian() * 10));
    }
    
    /**
     * Performs a fight, informing user of progress.
     */
    private void doFight() {
        //Get weapon and enemy.
        Weapon weap = player.getBestWeapon();
        Enemy enemy = currentRoom.getEnemy();
        final int damage; //store damage done to player
        
        if(weap == null) {
            //player has no weapon, inform player of progress and proceed as if
            //weapon damage = 0 (bare hands)
            ui.showInfoDialog(
                    Output.fightMessage(enemy.getName()), 
                    Output.fightTitle(), 
                    JOptionPane.WARNING_MESSAGE
            );
            damage = battleDamage(enemy.getStrength(),0);
        } else {
            //Player has weapon, inform player of progress and do battle
            ui.showInfoDialog(
                    Output.fightMessage(enemy.getName(),weap.getName()), 
                    Output.fightTitle(), 
                    JOptionPane.WARNING_MESSAGE
            );
            damage = battleDamage(enemy.getStrength(),weap.getDamage());
        }
        if(player.removeHP(damage)) {
            //Player successfully killed enemy and still has HP, notify player of
            //victory, remove enemy and move to this room
            ui.showInfoDialog(
                    Output.killEnemyMessage(enemy.getName(), String.valueOf(damage)),
                    Output.killEnemyTitle(),
                    JOptionPane.INFORMATION_MESSAGE);
            currentRoom.removeEnemy();
            ui.setGameCommentary(currentRoom.getLongDescription());
            ui.setGameTitle("Health: " + player.getHP());
        } else {
            //Player ran out of HP, notify player
            if(ui.showChoiceDialog(
                    Output.playerKilledMessage(enemy.getName(), String.valueOf(damage)),
                    Output.playerKilledTitle(),
                    Output.playerKilledOptions(),
                    JOptionPane.ERROR_MESSAGE)) {
                restartGame();
            } else {
                //User does not want to play again; exit gracefully
                System.exit(0);
            }
        }
    }

    /**
     * Performs a fleeing action, informing player and moving to previous room.
     */
    private void doFlee() {
        goRoom(prevExit.getOpposite());
        ui.showInfoDialog(
            Output.fleeMessage(),
            Output.fleeTitle(),
            JOptionPane.WARNING_MESSAGE
        );
    }
            
}
