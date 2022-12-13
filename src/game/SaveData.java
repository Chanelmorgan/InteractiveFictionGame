/**********************************************************************************************************************
 File        : SaveData.java

 @author      : Chanel Morgan

 Description :  Class to save the Player objects data to using serialVersionUID
 ********************************************************************************************************************/

package game;

import gameobjects.Inventory;
import gameobjects.LOCATION;

import java.io.Serializable;
import java.util.ArrayList;


public class SaveData implements Serializable {


    private static final long serialVersionUID = 1L;

    // these really should be private and have accessors
    public String name;
    public int maxHp;
    public int hp;
    public int xp;
    public int numDefUpgrades;
    public int numAttackUpgrades;
    public int gold;
    public int pots;
    public ArrayList<String> chosenAttackTraits = new ArrayList<String>();
    public ArrayList<String> chosenDefensiveTraits = new ArrayList<String>();
    public Inventory playerInv = new Inventory();
    public LOCATION playerLocation;
    public boolean isAlive;
}
