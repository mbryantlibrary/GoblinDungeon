package goblindungeon;

import java.util.Random;

/**
 *  This is the main class for the GoblinGame, handling the game logic and the 
 *  interactions between different classes. To run, simply compile and run, or 
 *  double click the attached 'GoblinGame.jar' Java executable file (TODO: provide executable)
 *  There is only one instance of this class, so it is static, i.e. you cannot
 *  create a new class.
 * 
 * @author  Miles Bryant
 * @version 10.11.2014
 */

public class Game 
{
    private static Parser parser;
    private static Room currentRoom;
    private static Directions prevExit;

    public static final Player player = new Player();

    
    private static GameState state = GameState.PLAYING;
    
    /**
     * Stores the current state of the game.
     */
    public enum GameState {
        PLAYING, NAVIGATING, INBATTLE, FINISHED
    }
    
    
    public static void main(String [] args) {
        Data.load();
        currentRoom = Data.getFirstRoom();
        parser = new Parser();
        Output.printWelcome(currentRoom.getLongDescription());
        
        state = GameState.NAVIGATING;

        /*
         The main game loop. We repeatedly read and process commands until the 
         game is over, or the player wants to quit (boo!).
        */
        
        
        while (state != GameState.FINISHED) {
            Command command = parser.getCommand();
            state = processCommand(command);
        }
        
        Output.printQuit();
    }
    
    



    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private static GameState processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            Output.printUnknownCommand();
            return state;
        }
        if (commandWord == CommandWord.HELP) {
            Output.printHelp(parser.showCommands(state));
        }
        else if (commandWord == CommandWord.QUIT) {
            return quit(command) ? GameState.FINISHED : state;
        }
        else if (commandWord == CommandWord.ITEMS) {
            Output.printInventory(player.getItems());
        }
        else if (commandWord == CommandWord.USE) {
            useItem(command);
        } else if (commandWord == CommandWord.FIGHT) {
            if(state == GameState.INBATTLE)
                doFight();
            else
                Output.printNothingToFight();
        } else if (commandWord == CommandWord.FLEE) {
            if(state == GameState.INBATTLE) {
                Output.printFlee();
                state = goRoom(prevExit.getOpposite());
            } else {
                Output.printNothingToFlee();
            }
        } else if (commandWord == CommandWord.GO) {
            if(state == GameState.NAVIGATING)
                return goRoom(command);
            else if(state == GameState.INBATTLE)
                Output.printMustFightOrFlee(currentRoom.getEnemy().getName());
                
        } 
        
        // else command not recognised.
        return state;
    }

    // implementations of user commands:

    private static void useItem(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Use what?");
            return;
        }
        String itemCmd = command.getSecondWord();
        player.useItem(itemCmd);
    }
    
    private static GameState goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            Output.printDontKnowWhereToGo();
            return state;
        }
        Directions dir;
        try {
            dir = Directions.valueOf(command.getSecondWord());
        } catch(Exception e) {
            Output.printUnknownCommand();
            return state;
        }
        return goRoom(dir);
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private static GameState goRoom(Directions direction) 
    {
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            Output.printNoExit();
        } else if (nextRoom.getClass() == GoalRoom.class) {
            Output.printWinMessage();
            return GameState.FINISHED;
        }
        else {
            currentRoom = nextRoom;
            prevExit = direction;
            
            if(currentRoom.hasItem()) {
                Output.printFoundItem(currentRoom.getItem().toString());
                
                player.addItem(currentRoom.getItem());
                currentRoom.removeItem();
            }
            Output.printHP(player.getHP());
            if(currentRoom.hasEnemy()) {
                Output.printStartFight(currentRoom.getEnemy().getName());
                return GameState.INBATTLE;
            }
        }
        Output.printGameDesc(currentRoom.getLongDescription(), player.getHP());
        return GameState.NAVIGATING;
        
    }
    
    private static void doFight() {
        Weapon weap = player.getBestWeapon();
        Enemy enemy = currentRoom.getEnemy();
        int damage = 0;
        if(weap == null) {
            Output.printFight(enemy.getName());
            damage = battleDamage(enemy.getStrength(),0);
        } else {
            Output.printFight(enemy.getName(),weap.getName());
            damage = battleDamage(enemy.getStrength(),weap.getDamage());
        }
        if(player.removeHP(damage)) {
            Output.printKillEnemy(enemy.getName(), String.valueOf(damage));
            currentRoom.removeEnemy();
            state = GameState.NAVIGATING;
            Output.printGameDesc(currentRoom.getLongDescription(), player.getHP());
        } else {
            Output.printPlayerKilled(enemy.getName(), String.valueOf(damage));
            state = GameState.FINISHED;
        }
    }
    private static int battleDamage(int enemyStrength, int weaponStrength) {
        Random rand = new Random();
        return (int) Math.abs(Math.round((double)enemyStrength - (double)weaponStrength) + (rand.nextGaussian() * 10));
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private static boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            Output.printQuitWhat();
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
