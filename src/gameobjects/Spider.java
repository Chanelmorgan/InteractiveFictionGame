package gameobjects;

// Class to represent a spider, the enemy object that will attack the player in the forest. Should be the weakest enemy
public class Spider extends Enemy {

    // Constructor
    public Spider(int maxHp, int xp) {
        super(maxHp, xp);
        this.name = "Spider";
        this.hp = 10;
        this.maxHp = 10;
    }

    // Enemy specific attack and defence calculations
    @Override
    public int attack() {
        return (int) (Math.random() * (xp / 4 + 1) + xp / 4 + 3);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (xp / 4 + 1) + xp / 4 + 3);
    }


}
