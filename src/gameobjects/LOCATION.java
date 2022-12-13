/***********************************************************************************************************************
 File        : Location.java

 @author      : Chanel Morgan

 Description : Enums to represent the player's location throughout the game,
 allows the player to be tracked through their progression of the game
 **********************************************************************************************************************/


package gameobjects;

public enum LOCATION {
    ROOM(1),
    FOREST(2),
    HALLWAY(3),
    VILLAGE(4),
    MOUNTAINS(5),
    FINALBATTLE(6);

    private int number;

    LOCATION(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
