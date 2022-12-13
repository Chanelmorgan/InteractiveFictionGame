/***********************************************************************************************************************
 File        : StoneMonster.java

 @author      : Chanel Morgan

 Description : Class that represents the enemy that is found in the mountain's location.
 These should be harder to kill than the spiders.
 **********************************************************************************************************************/
package gameobjects;

public class StoneMonster extends Enemy {

    // Constructor
    public StoneMonster(int maxHp, int xp) {
        super(maxHp, xp);
        this.name = "Stone Monster";
        this.maxHp = 20;
    }

    // Methods for calculating defense and attack
    @Override
    public int attack() {
        return (int) (Math.random() * (xp / 4 + 1) + xp / 4 + 3);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (xp / 4 + 1) + xp / 4 + 3);
    }

}
