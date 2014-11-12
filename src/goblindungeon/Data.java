package goblindungeon;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is static, and loads the game data (rooms, items etc) into memory 
 * and provides access to them. At the moment all data is hardcoded, but this 
 * could be easily extended to reading from data files.
 * 
 * Also contains constants and game parameters.
 * 
 * @author Miles
 * @version 11.11.2014
 */
public class Data {
    
    //Constants

    /**
     * The maximum HP the player can have.
     */
    public static final int MAX_PLAYER_HEALTH = 100;

    /**
     * The probability a room contains an item (0.0: never contains items).
     */
    public static final float P_ITEMINROOM = 0.2f;

    /**
     * The probability a room contains an enemy (0.0: never contains enemies).
     */
    public static final float P_ENEMYINROOM = 0.2f;
    
    
    //specifies the first room of the game to load
    private static Room firstRoom;
    //stores all objects in the game; rooms can randomly select from them.
    private static final ArrayList<Item> itemPool = new ArrayList<>();
    //same, but for enemies
    private static final ArrayList<Enemy> enemyPool = new ArrayList<>();
    
    /*
     * Run each loading method.
     */
    static void load() {
        loadItems();
        loadEnemies();
        createRooms(); //must come last, as items and enemies must be loaded!
    }
    
    /*
     * Load all items into the game.
     */
    static void loadItems() {
        itemPool.add(
                new Health(
                        "Philter of the Nomad",
                        "an opaque goo which smells like burned carrots;",
                        "health",
                        0.5f,
                        10
                )
        );
        itemPool.add(
                new Health(
                        "Balm of Earth Aura",
                        "a foul smelling blend of bitter herbs;",
                        "balm",
                        0.3f,
                        20
                )
        );
        itemPool.add(
                new Health(
                        "Celestial Animate Tonic",
                        "a chilling yet sweet viscous liquid;",
                        "health",
                        0.2f,
                        30
                )
        );
        itemPool.add(
                new Health(
                        "Blasphemous Salve",
                        "a tasteless and odorless light gray powder;",
                        "health",
                        0.1f,
                        40
                )
        );
        itemPool.add(
                new Health(
                        "Ointment of Envenoment",
                        "a vile and repulsive smelling blend of wicket spirits;",
                        "health",
                        0.1f,
                        50
                )
        );
        itemPool.add(
                new Weapon(
                        "spiked mace",
                        "a vicious looking metal weapon with spiked protrusions;",
                        "mace",
                        0.1f,
                        40
                )
        );
        itemPool.add(
                new Weapon(
                        "cracked club",
                        "a well used and weighty beam of willow;",
                        "club",
                        0.2f,
                        10
                )
        );
        itemPool.add(
                new Weapon(
                        "longsword",
                        "an elven-crafted heavy metal sword;",
                        "longsword",
                        0.05f,
                        50
                )
        );
        itemPool.add(
                new Weapon(
                        "crossbow",
                        "a well engineered steel crossbow, with a few scattered bolts;",
                        "crossbow",
                        0.1f,
                        25
                )
        );
        itemPool.add(
                new Weapon(
                        "silver dagger",
                        "a gleaming, short but pointedly sharp dagger;",
                        "dagger",
                        0.1f,
                        15
                )
        );
    }
    /*
     * Load all enemies into the game.
     */
    private static void loadEnemies() {
        enemyPool.add(
                new Enemy(
                        "troll",
                        "",
                        0.1f,
                        50
                )
        );
        enemyPool.add(
                new Enemy(
                        "wild dog",
                        "",
                        0.4f,
                        20
                )
        );
        enemyPool.add(
                new Enemy(
                        "centaur",
                        "",
                        0.2f,
                        30
                )
        );
        enemyPool.add(
                new Enemy(
                        "undead",
                        "",
                        0.15f,
                        40
                )
        );
        enemyPool.add(
                new Enemy(
                        "giant beast",
                        "",
                        0.05f,
                        60
                )
        );
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private static void createRooms() {
        // check if items and enemies have been loaded and if not load them
        if(itemPool.isEmpty())
            loadItems();
        if(enemyPool.isEmpty())
            loadEnemies();        
        
        Room outside;
        Room theatre;
        Room pub;
        Room lab;
        Room office;
        
        Room a1,a2,a3,a4,a5;
        Room b1,b2,b3,b4,b5,b6,b7;
        Room c1,c2,c3,c4,c5,c6;
        
        a1 = new Room("in a damp, musty entrance porch.");
        a2 = new Room("surrounded by filth and grot.");
        a3 = new Room("in a dark room.");
        a4 = new Room("in an overgrown passageway.");
        a5 = new Room("at a worn down set of stairs leading up.");
        
        a1.setExit(Directions.north, a2);
        a2.setExit(Directions.north, a3);
        a2.setExit(Directions.east, a4);
        a4.setExit(Directions.east, a5);
        
        
        b1 = new Room("at the top of a worn down staircase.");
        b2 = new Room("in a grim corridor.");
        b3 = new Room("in a dark room, surrounded by debris.");
        b4 = new Room("in a small room covered in stinking mould.");
        b5 = new Room("in an ominous passageway.");
        b6 = new Room("in a large room, with insects crawling everywhere.");
        b7 = new Room("at the top of a narrow staircase.");
        
        a5.setExit(Directions.up, b1);
        
        b1.setExit(Directions.east, b2);
        b2.setExit(Directions.north, b3);
        b3.setExit(Directions.east, b4);
        b2.setExit(Directions.south, b5);
        b5.setExit(Directions.south, b6);
        b6.setExit(Directions.east, b7);
        
        c1 = new Room("at the bottom of a narrow staircase.");
        c2 = new Room("in a musty sideroom.");
        c3 = new Room("in a dank and manky corridor.");
        c4 = new Room("in a festering passageway.");
        c5 = new Room("in a damp closet.");
        c6 = new GoalRoom("in a room with the treasure!!");
        
        b7.setExit(Directions.down, c1);
        c1.setExit(Directions.west, c2);
        c2.setExit(Directions.south, c3);
        c3.setExit(Directions.south, c4);
        c4.setExit(Directions.east, c5);
        c4.setExit(Directions.south, c6);
        
        // create the rooms
        firstRoom = a1; // start game outside
    }
    
    /*
     * Get the first room of the game. If not set, we call createRooms() to get it.
     */
    static Room getFirstRoom() {
        if(firstRoom == null)
            createRooms();
        return firstRoom;
    }
    
    
    
    /*
     * Returns a random item from the itemPool depending on rarity.
     */
    static Item getRandomItem() {
        Random rand = new Random();
        while(true) { //loop until we find an item
            //select a random item
            Item item = itemPool.get(rand.nextInt(itemPool.size()));
            //randomly decide to pick the item
            if(rand.nextFloat() < item.getRarity()) 
                return item;
        }
    }
    
    /*
     *Returns a random enemy from the enemyPool depending on rarity.
     */
    static Enemy getRandomEnemy() {
        Random rand = new Random();
        while(true) {
            Enemy enemy = enemyPool.get(rand.nextInt(enemyPool.size()));
            if(rand.nextFloat() < enemy.getRarity())
                return enemy;
        }
    }

    
}
