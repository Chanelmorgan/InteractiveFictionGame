/***********************************************************************************************************************
 File        : Enemy.java

 @author      : Chanel Morgan

 Description : This is an abstract class that represents an enemy in the game.
 **********************************************************************************************************************/

package gameobjects;


public abstract class Enemy {

    protected int maxHp, xp, hp;
    protected String name;
    protected boolean isAlive = true;
    protected int playerXp;

    // enemy specific constructor
    public Enemy(int maxHp, int xp) {
        //this.maxHp = (int)(Math.random()*playerXp + playerXp/3 + 5); // this is the health of the enemy we are creating
        this.xp = xp; // this is the xp we are giving to the enemy
        this.hp = maxHp; // start of as maxHp

    }

    // Enemy specific attack and defence calculations
    public abstract int attack();

    public abstract int defend();

    // Accessors and Mutators
    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getXp() {
        return xp;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
