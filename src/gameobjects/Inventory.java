/***********************************************************************************************************************
 File        : Inventory.java

 @author      : Chanel Morgan

 Description : Class should be holding the players inventory, holds items like broom and wand.
 **********************************************************************************************************************/

package gameobjects;

import java.io.Serializable;
import java.util.HashMap;


public class Inventory implements Serializable {

    // Variables
    private int size = 5; // the size of the inventory, if developed game further i would have more items to pick up and the player would be restricted on what they can carry
    //private ArrayList<Item> items;  // using a hashmap because no duplicates // int is the position in the inventory
    private HashMap<Item, Integer> items;

    // Class Constructor
    public Inventory() {
        this.items = new HashMap<Item, Integer>();
    }

    // Accessors
    public Item getItem(String name) {
        for (Item i : items.keySet()) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    // Method that creates an Item object before adding it to the inventory
    public void addItem(String name) {
        // create a new object of that item to add to the collection
        for (int i = 0; i <= this.size; i++) {
            Item newItem = new Item(name);
            this.items.put(newItem, i);
            i++;
        }
    }

    // Method that remove an Item from the Inventory
    public void removeItem(String name) {
        this.items.remove(getItem(name));
    }

    // method that prints out what is in the inventory, so the user can see what they are holding
    public void printInventory() {
        for (Item i : items.keySet()) {
            System.out.println(i);
        }

    }

    // Method to find the size the item's collection in the inventory
    public int size() {
        return this.items.size();
    }


    // Methods to get a specific item from the inventory
    public boolean getItems(String name) {
        for (Item i : items.keySet()) {
            if (i.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


//    // local testing harness
//    public static void main(String[] args){
//        Inventory testInv = new Inventory();
//        testInv.addItem("Broom");
//        testInv.printInventory();
//        //System.out.println(testInv.size());
//        testInv.removeItem("Broom");
//        System.out.println("Best test");
//        testInv.printInventory();
//
//
//
//
//    }
}