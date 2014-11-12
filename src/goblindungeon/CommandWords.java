package goblindungeon;

import java.util.HashMap;

/**
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in, and to provide a list
 * of all available commands.
 *
 * @author  Miles Bryant
 * @version 11.11.2014
 */

public class CommandWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private final HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @param aString command to check
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands for the specified GameState to System.out. E.g.
     * if GameState == BATTLE then the normal + battle commands will be shown.
     * @param state Specifies the command words to show based on GameState.
     * @return A String list of all the available commands separated by whitespace.
     */
    public String showAll(Game.GameState state) 
    {
        String r = "";
        for(CommandWord command : validCommands.values()) {
            if(state == command.getState() || command.getState() == Game.GameState.PLAYING)
            r += command.name() + "  ";
        }
        return r;
    }
}
