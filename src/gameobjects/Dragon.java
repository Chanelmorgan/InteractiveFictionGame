/***********************************************************************************************************************
 File        : Dragon.java

 @author      : Chanel Morgan

 Description : Class to represent the final battle enemy
 **********************************************************************************************************************/
package gameobjects;

public class Dragon extends Enemy {

    public Dragon(int maxHp, int xp) {
        super(maxHp, xp);
        this.name = "Puff";
        this.hp = maxHp;
        this.maxHp = 50;
    }

    @Override
    public int attack() {
        return (int) (Math.random() * (xp / 4 + 1) + xp / 4 + 3);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (xp / 4 + 1) + xp / 4 + 3);
    }


}
