/**********************************************************************************************************************
 File        : Character.java

 @author      : Chanel Morgan

 Description : This is an abstract class that outlines the basic information that all characters in the games
 will inherit.
 ********************************************************************************************************************/
package gameobjects;

public abstract class Character {

    // Variables - attributes all characters have
    public String name;
    public int maxHp, hp, xp;
    public boolean isAlive = true;

    // Constructor for character
    public Character(String name, int maxHp, int xp) {
        this.name = name;
        this.maxHp = maxHp;
        this.xp = xp;
    }

    // Methods every character will have
    public abstract int attack();

    public abstract int defend();

}
