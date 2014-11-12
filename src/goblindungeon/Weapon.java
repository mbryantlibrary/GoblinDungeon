package goblindungeon;

/**
 * Weapons are game objects used in battle, that increase the player's chance
 * of victory against an enemy, and reduce the HP damage taken according to the
 * weapon's damage.
 * @author Miles Bryant
 * @version 11.11.2014
 */
public class Weapon extends Item {
    
    private int damage = 0;

    /**
     * Creates a new Health object with the specified attributes.
     * @param name short name to refer to the object (e.g. spiked mace)
     * @param description longer description
     * @param usecommand the command second word that uses this object
     * @param rarity the likelihood of this item being in a room 
     * (0.0 never appears - 1.0 always appears)
     * @param damage the effect this weapon has on a battle. Higher damage will 
     * lead to greater success and less HP lost per battle.
     */
    public Weapon(String name, String description, String usecommand, float rarity, int damage) {
        super(name,description + " does " + String.valueOf(damage) + " damage.",usecommand,rarity);
        this.damage = damage;
    }

    /**
     * This happens when a player tries to use a weapon.
     * @return Always returns false as the weapon should be kept.
     */
    @Override
    public boolean doEffect() {
        Output.printCantUseWeapon(name);
        return false;
    }

    /**
     * @return the damage this weapon does.
     */
    public int getDamage() {
        return damage;
    }

    
}
