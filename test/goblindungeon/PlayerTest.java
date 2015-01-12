package goblindungeon;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miles
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @Test
    public void testGetBestWeapon() {
        System.out.println("Testing getBestWeapon()...");
        Player instance = new Player();
        
        System.out.println("\tAdding 5 weapons...");
        
        instance.addItem(new Weapon("weak", "", "", 1.0f, 10));
        instance.addItem(new Weapon("weak", "", "", 1.0f, 40));
        instance.addItem(new Weapon("weak", "", "", 1.0f, 30));
        instance.addItem(new Weapon("strong", "", "", 1.0f, 50));
        instance.addItem(new Weapon("weak", "", "", 1.0f, 20));
        
        
        System.out.println("\tGetting best weapon...\n");
        Weapon result = instance.getBestWeapon();
        
        assertEquals("Strongest weapon not selected", result.getName(),"strong");
    }
    
    @Test
    public void testAddHP() {
        System.out.println("Testing addHP()...");
        int HP = 50;
        Player instance = new Player();
        
        System.out.println("\tAdding 50HP with HP already at maximum...");
        int result = instance.addHP(HP);
        assertTrue("HP added is negative",result >= 0);
        assertEquals("HP added to maximum health",0, result);
        
        instance.removeHP(Data.MAX_PLAYER_HEALTH - 70);
        System.out.println("\tAdding 50HP with HP at " + instance.getHP() + "...");
        result = instance.addHP(HP);
        assertEquals(30, result);
        
        instance.removeHP(Data.MAX_PLAYER_HEALTH - 50);
        System.out.println("\tAdding 50HP with HP at " + instance.getHP() + "...");
        result = instance.addHP(HP);
        assertEquals(50, result);
        
        
        instance.removeHP(Data.MAX_PLAYER_HEALTH - 30);
        System.out.println("\tAdding 50HP with HP at " + instance.getHP() + "...\n");
        result = instance.addHP(HP);
        assertEquals(50, result);
    }
    
    @Test
    public void testRemoveHP() {
        System.out.println("Testing removeHP()...");
        
        Player instance = new Player();
        
        System.out.println("\tRemoving 50HP from player HP of " + instance.getHP());
        boolean result = instance.removeHP(50);
        assertTrue(result);
        
        System.out.println("\tRemoving 70HP from player HP of " + instance.getHP());
        result = instance.removeHP(70);
        assertTrue(!result);
    }
}
