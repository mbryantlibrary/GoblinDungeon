package goblindungeon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

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
    public static final float P_ENEMYINROOM = 0.4f;
    
    /**
     * Resource directory for loading images and icons. This is relative to the 
     * user directory, i.e. where this application is run from.
     */
    private static final String imagesDirectory = "resources/img/";
    private static final String iconsDirectory = "resources/icons/";
    
    
    //specifies the first room of the game to load
    private static Room firstRoom;
    //stores all objects in the game; rooms can randomly select from them.
    private static final ArrayList<Item> itemPool = new ArrayList<>();
    //same, but for enemies
    private static final ArrayList<Enemy> enemyPool = new ArrayList<>();
    //stores our images and icons
    private static final HashMap<String,BufferedImage> images = new HashMap<>();
    private static final HashMap<String,ImageIcon> icons = new HashMap<>();
    
    private static final Random rand = new Random();
    
    /**
     * Run each loading method. Throws an IOException if loading images/icons
     * does not work, usually if the resource directory is incorrect/not present.
     */
    static void load() throws IOException {
        itemPool.clear();enemyPool.clear(); //clear pools to avoid duplication on restart
        loadImages();
        loadIcons();
        
        loadItems();
        loadEnemies();
        
        createRooms(); //must come last, as images, items and enemies must be loaded!
    }
    
    /**
     * Loads an image from a file.
     * @param filename Absolute filename of the image to be loaded.
     * @return BufferedImage from the image file.
     * @throws IOException Thrown if file does not exist/is not valid image file.
     */
    private static BufferedImage loadImageFromFile(String filename) throws IOException {
            BufferedImage image = ImageIO.read(new File(filename));
            if(image == null || (image.getWidth(null) < 0)) {
                // we could not load the image - probably invalid file format
                throw new IOException(String.format("No image data could be loaded - probably corrupt file %s",filename));
            }
            return image;
    }
    
    /**
     * Gets all files in a directory.
     * @param relativeDir Directory (relative to user directory, e.g. where 
     * application is run from)
     * @return Array of files in the directory, or an empty array if no files found
     * @throws FileNotFoundException if directory does not exist, or if directory
     * is a file.
     */
    private static File[] getAllFilesFromDirectory(String relativeDir) throws FileNotFoundException {
        File folder = new File(relativeDir);
        if(!folder.exists()) { //check folder doesn't exist
            throw new FileNotFoundException(
                    "Resources folder not found. Check that it is under the "
                            + "directory this application is run from.\n"
                            + "user.dir: " + System.getProperty("user.dir")
                            + "\nfull directory searched for: "
                            + folder.getAbsolutePath()
            );
        } else if(!folder.isDirectory()) { //check folder is actually a folder
            throw new FileNotFoundException(
                    "Directory '" + folder.getAbsolutePath() + "'given is a file! "
            );
        }
        return folder.listFiles();
    }
    
    /**
     * Loads all images from the image resource directory into a HashMap.
     * @throws IOException if image resource directory is not found, or another 
     * I/O error occurs.
     */
    private static void loadImages() throws IOException {
        for(File f : getAllFilesFromDirectory(imagesDirectory)) {
            String name = f.getName(); //file name - for 'image.jpg' it is 'image'
            String extension = ""; //extension
            int i = f.getName().lastIndexOf('.');
            if (i >= 0) { //if file has extension
                name = f.getName().substring(0, i);
                extension = f.getName().substring(i+1);
            }
            if("jpg".equals(extension)) //if file is a JPEG file
                images.put(name, loadImageFromFile(f.getAbsolutePath()));
        }
    }
    /**
     * Loads all icons from the icor resource directory from a HashMap.
     * @throws IOException if icon resource directory is not found, or another 
     * I/O error occurs.
     */
    private static void loadIcons() throws IOException {
        for(File f : getAllFilesFromDirectory(iconsDirectory)) {
            String name = "";
            String extension = "";
            int i = f.getName().lastIndexOf('.');
            if (i >= 0) {
                name = f.getName().substring(0, i);
                extension = f.getName().substring(i+1);
            }
            if("png".equals(extension))
                icons.put(name, new ImageIcon(f.getAbsolutePath()));
        }
    }
    /**
     * Public accessor for getting a loaded icon. Throws IndexOutOfBounds if name
     * supplied is not in icon file.
     * @param name Name of icon to access.
     * @return ImageIcon corresponding to the name.
     */
    public static ImageIcon getIcon(String name) {
        return icons.get(name);
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
                        "hound",
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
                
        Room a1,a2,a3,a4,a5;
        Room b1,b2,b3,b4,b5,b6,b7;
        Room c1,c2,c3,c4,c5,c6;
        
        a1 = new Room("in a damp, musty entrance porch.",images.get("entrance"));
        a2 = new Room("surrounded by filth and grot.",images.get("carcass"));
        a3 = new Room("in a dark room.",images.get("smallroom"));
        a4 = new Room("in an overgrown passageway.",images.get("arch"));
        a5 = new Room("at a worn down set of stairs leading up.",images.get("stairs"));
        
        a1.setExit(Directions.north, a2);
        a2.setExit(Directions.north, a3);
        a2.setExit(Directions.east, a4);
        a4.setExit(Directions.east, a5);
        
        
        b1 = new Room("at the top of a worn down staircase.",images.get("stairs"));
        b2 = new Room("in a grim corridor.",images.get("room"));
        b3 = new Room("in a dark room, surrounded by debris.",images.get("cage"));
        b4 = new Room("in a small room covered in stinking mould.",images.get("smallroom"));
        b5 = new Room("in an ominous passageway.",images.get("lights"));
        b6 = new Room("in a large room, with insects crawling everywhere.",images.get("devices"));
        b7 = new Room("at the top of a narrow staircase.",images.get("stairsup"));
        
        a5.setExit(Directions.up, b1);
        
        b1.setExit(Directions.east, b2);
        b2.setExit(Directions.north, b3);
        b3.setExit(Directions.east, b4);
        b2.setExit(Directions.south, b5);
        b5.setExit(Directions.south, b6);
        b6.setExit(Directions.east, b7);
        
        c1 = new Room("at the bottom of a narrow staircase.",images.get("stairsup"));
        c2 = new Room("in a musty sideroom.",images.get("devices2"));
        c3 = new Room("in a dank and manky corridor.",images.get("room"));
        c4 = new Room("in a festering passageway.",images.get("arch"));
        c5 = new Room("in a damp closet.",images.get("chain"));
        c6 = new GoalRoom("in a room with the treasure!!",images.get("treasure"));
        
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
        while(true) {
            Enemy enemy = enemyPool.get(rand.nextInt(enemyPool.size()));
            if(rand.nextFloat() < enemy.getRarity())
                return enemy;
        }
    }

    
}
