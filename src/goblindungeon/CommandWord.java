package goblindungeon;

/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language and which game state for which
 * this command is available (for a command that can be shown at any time, 
 * e.g. QUII, the game state is PLAYING).
 * 
 * @author Miles Bryant
 * @version 11.11.2014
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string and available game state.

    GO("go",Game.GameState.NAVIGATING), //i.e. will only be available when navigating, i.e. not in battle
    QUIT("quit"), 
    HELP("help"), 
    UNKNOWN("?"), 
    ITEMS("items"), 
    USE("use"), 
    FIGHT("fight",Game.GameState.INBATTLE), 
    FLEE("flee", Game.GameState.INBATTLE);
    
    // The command string.
    private final String commandString;
    
    private final Game.GameState state;
    
    /**
     * Initialise with the corresponding command word, setting GameState to 
     * PLAYING (meaning it is valid at any time the game is running)
     * @param commandWord The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
        state = Game.GameState.PLAYING;
    }
    /**
     * Initialise with the corresponding command word and specified GameState.
     * @param commandWord The command string.
     * @param state The GameState for which this command is available.
     */
    CommandWord(String commandString, Game.GameState state)
    {
        this.commandString = commandString; this.state = state ;
    }
    
    public Game.GameState getState() {
        return state;
    }
    
    /**
     * @return The command word as a string.
     */
    @Override
    public String toString()
    {
        return commandString;
    }
}
