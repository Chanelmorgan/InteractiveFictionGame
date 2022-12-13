/***********************************************************************************************************************
 File        : Item.java

 @author      : Chanel Morgan

 Description : Class that represents an Item in the game. Item objects are intended to be stored in the Inventory
               for a Player to access
 **********************************************************************************************************************/

package gameobjects;

import java.io.Serializable;

public class Item implements Serializable {

    // Variable
    private String name;

    // Class Constructor
    public Item(String name) {
        this.name=name;
    }

    // Accessor
    public String getName() {
        return this.name;
    }

    // toString method
    public String toString() {
       return name ;
    }

    // local testing harness of this class
//    public static void main(String[] args){
//        Item broom = new Item("Broom"); // testing that the Item is created in the correct format I want
//
//    }
}
