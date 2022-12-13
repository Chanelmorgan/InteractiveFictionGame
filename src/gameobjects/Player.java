/***********************************************************************************************************************
 File        : Player.java

 @author      : Chanel Morgan

 Description : This Class represents the users players.
 **********************************************************************************************************************/
package gameobjects;

import game.GameLogic;
import game.ResourceManger;
import game.SaveData;
import game.Story;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static game.GameLogic.*;

public class Player extends Character implements Serializable {

    // Variables
    private int numAttackUpgrades, numDefUpgrades;
    private int gold, pots;
    private Inventory playerInv;
    private LOCATION playerLocation;

    // CHANGE THESE LATER TO BETTER EXAMPLES
    // Arrays to store traits names
    private final String[] atkUpgrades = {"Strength", "Power", "Might", "Godlike Strength"}; // attack upgrades
    private final String[] defUpgrades = {"Heavy Bones", "Stone skin", "Scale Armor", "Holy Aura"}; // defensive upgrades

    // Storing the players traits in arraylists
    private ArrayList<String> chosenAttackTraits;
    private ArrayList<String> chosenDefensiveTraits;

    // Class Constructor
    public Player(String name) {
        // calling constructor of superclass
        super(name, 100, 0); // hardcoded values of the hp and xp
        //setting # of upgrades to 0
        this.numDefUpgrades = 0; // these will be used later to calculate in battles
        this.numAttackUpgrades = 0;
        // set additional stats
        this.gold = 5;
        this.pots = 0;
        this.hp = 100;
        // this will be holding the players chosen traits - want to be able to store the traits in a collection
        this.chosenAttackTraits = new ArrayList<>();
        this.chosenDefensiveTraits = new ArrayList<>();
        // let the player choose a trait when creating a new character
        chooseTrait();
        // giving the player an inventory
        this.playerInv = new Inventory();
        // location of the player for the story at the start
        this.playerLocation = LOCATION.ROOM;
        this.isAlive = true;
    }

    // Method that adds an attack trait to the player
    public void addAttackTrait(String trait) {
        this.chosenAttackTraits.add(trait);
    }

    // Method that adds a defensive trait to the player
    public void addDefTrait(String trait) {
        this.chosenDefensiveTraits.add(trait);
    }


    // want to add exception checking - to make sure the user is only entering an int

    // Method that allows the player to choose a trait
    public void chooseTrait() {
        GameLogic.printHeading("Choose a trait: ");
        System.out.println(" (1) " + atkUpgrades[numAttackUpgrades]);
        System.out.println(" (2) " + defUpgrades[numDefUpgrades]);

        // get the players choice:
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        GameLogic.clearConsole();
        // deal with both case

        if (input == 1) {
            GameLogic.printHeading("You chose " + atkUpgrades[numAttackUpgrades] + "!");
            addAttackTrait(atkUpgrades[numAttackUpgrades]);
            numAttackUpgrades++;
        } else {
            GameLogic.printHeading("You chose " + defUpgrades[numDefUpgrades] + "!");
            addDefTrait(defUpgrades[numDefUpgrades]);
            numDefUpgrades++;
        }
        GameLogic.pressEnterKeyToContinue();
    }

    // Accessors
    public Inventory getPlayerInv() {
        return playerInv;
    }

    public LOCATION getPlayerLocation() {
        return playerLocation;
    }

    // Method for calculating attack done by the player when not using magic
    @Override
    public int attack() {
        //return 50;
        return (int) (Math.random() * (xp / 4 + numAttackUpgrades * 3 + 3) + xp / 10 + numAttackUpgrades * 2 + numAttackUpgrades + 1);
    }

    // Method for calculating the defense done by the player.
    @Override
    public int defend() {
        return (int) (Math.random() * (xp / 2 + numDefUpgrades * 3 + 3) + xp / 10 + numAttackUpgrades + 1);
    }

    // Method for calculating the attack damage done by the player when using magic
    public int magic() {
        randomSpell();
        return (int) (Math.random() * (xp / 4 + numAttackUpgrades * 3 + 3) + xp / 10 + numAttackUpgrades * 2 + numAttackUpgrades + 3);
    }

    // Method for printing out the most important information about the player character
    public void characterInfo() {
        clearConsole();
        printHeading("CHARACTER INFO");
        System.out.println(name + "\tHP: " + hp + "/" + maxHp);
        printSeparator(20);
        // player xp and gold
        System.out.println("XP: " + xp + "\tGold: " + gold);
        printSeparator(20);
        // num of pots
        System.out.println("Number of Potions: " + pots);
        printSeparator(20);

        // if the player has attack traits then print these out
        if (numAttackUpgrades > 0) {
            System.out.println("Attack trait: ");
            chosenAttackTraits.forEach((e) -> System.out.println(e + " "));
            printSeparator(20);
        }

        // if the player has defense traits then print these out
        if (numDefUpgrades > 0) {
            System.out.println("Defensive traits: ");
            chosenDefensiveTraits.forEach((e) -> System.out.println(e + " "));
            printSeparator(20);
        }
        GameLogic.pressEnterKeyToContinue();
    }

    // Method that opens the players inventory and lists out the items
    public void openInv() {
        if (this.playerInv.size() > 0) {
            printSeparator(40);
            System.out.println("THE ITEMS IN INVENTORY: ");
            this.playerInv.printInventory();
        } else {
            printSeparator(40);
            System.out.println("There seems to be nothing in your inventory at this time.");
        }
    }

    // Method for flying the broom out of the window
    public void flyBroom() {
        System.out.println("Do you 1: Fly out of the window");
        System.out.println("Do you 2: Fly out of the room");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            // flying out of window
            look("window");
        } else {
            // flying to hallway
            moveTo(LOCATION.HALLWAY);
        }
    }

    // Method that allows the user to pick up the broom
    public void takeBroom() {
        System.out.println("Do you 1: Take the broom");
        System.out.println("Do you 2: Leave the broom");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("You take the broom");
            // in this method maybe say that the item was successfully added or if not throw exception
            this.playerInv.addItem("broom");
            printSeparator(40);
            System.out.println("Do you wish to open your inventory?");
            System.out.println("(1) - Yes, let me see my shiny new broom.\n(2) - No move on, I do not have time to waste here.");
            Scanner scanInv = new Scanner(System.in);
            int option = scanInv.nextInt();
            if (option == 1) {
                // opening invent
                openInv();
                if (playerInv.getItems("broom")) {
                    printSeparator(40);
                    System.out.println("While holding the broom you feel a strange sensation, almost magical...");
                    System.out.println("Do you 1: Get on the broom and test for its magical abilities.");
                    System.out.println("Do you 2: Throw the broom away, seems like a useless cleaning tool.");
                    Scanner scanner2 = new Scanner(System.in);
                    int option2 = scanner2.nextInt();
                    if (option2 == 1) {
                        // the user chooses to get on the broom
                        printSeparator(40);
                        System.out.println("You get on the broom...");
                        flyBroom(); // method for asking if you want to fly
                    } else {
                        printSeparator(40);
                        System.out.println("You get scared by the strange sensation and you throw the broom to the ground.");
                        this.playerInv.removeItem("broom");
                        continueJourney(); // player taken back to the room with all options again
                    }
                }


            } else if (option == 2) {
                // wish to not open inventory
                continueJourney();
            }
        } else {
            printSeparator(40);
            System.out.println("You have decided to leave the broom this time.");
            continueJourney();
        }
    }

    // Method that allows the user to take the wand
    public void takeWand() {
        if (!playerInv.getItems("wand")) {
            printSeparator(40);
            System.out.println("Do you want to add wand to inventory? ");
            System.out.println("(1) - Yes, got to keep that thing safe.\n(2) - No, leave the strange stick.");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            printSeparator(40);
            if (input == 1) { // if the user chooses to keep the wand
                System.out.println("You take the wand....");
                System.out.println("The wand begins to fizzle in your hand and you feel a warm sensation all over.");
                printHeading("YOUR A WIZARD!!!!!");
                getPlayerInv().addItem("wand");
                pressEnterKeyToContinue();
            } else { // if the user chooses to leave the wand
                System.out.println("You leave the strange stick behind.");
            }
        } else {
            System.out.println("You already have a wand.");
        }
    }

    // Method that moves the player to move to a new location.
    public void moveTo(LOCATION location) {
        // PLAYERS ACTIONS IN THE HALLWAY
        if (location.equals(LOCATION.HALLWAY)) {
            clearConsole();
            System.out.println("You leaving the room hastily and rush down the winding hallway.");
            System.out.println("In your hurry, you trip...");
            this.playerLocation = LOCATION.HALLWAY; // setting the player's location to hallway
            printSeparator(40);
            System.out.println("You find a strange box..... ");
            System.out.println("Do you 1: pick up the box");
            System.out.println("Do you 2: continue your journey");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch (input) {
                case (1):
                    // pick up the box
                    printSeparator(40);
                    System.out.println("You pick up the box.");
                    System.out.println("Do you 1: Open the box.");
                    System.out.println("Do you 2: Drop the box and continue your escape.");
                    Scanner scanner2 = new Scanner(System.in);
                    int option2 = scanner2.nextInt();
                    if (option2 == 1) {
                        // opening the box
                        printSeparator(40);
                        System.out.println("You open the box to find..... ");
                        System.out.println("a strange stick object");
                        takeWand();  //taking the wand
                        Story.outOfCastle();
                    }
                    moveTo(LOCATION.FOREST); // moving the player to the forest to continue the next part of the story
                    break;
                case (2): // moving the player to the forest to continue the next part of the story, as they leave the wand
                    Story.outOfCastle();
                    moveTo(LOCATION.FOREST);
                    break;
            }

            // PLAYERS ACTIONS IN THE FOREST
        } else if (location.equals(LOCATION.FOREST)) {
            printHeading("MOVING TO FOREST....... ");
            this.playerLocation = LOCATION.FOREST; // updating the players new location
            checkpoint(); // allows the player to save progress
            continueJourney(); // continuing the players journey in the forest

            // PLAYERS ACTIONS IN THE MOUNTAINS
        } else if (location.equals(LOCATION.MOUNTAINS)) {
            printHeading("MOVING TO MOUNTAINS...... ");
            // update players location here
            this.playerLocation = LOCATION.MOUNTAINS;
            checkpoint();
            // continue story
            continueJourney();

            // PLAYERS ACTIONS IN THE VILLAGE
        } else if (location.equals(LOCATION.VILLAGE)) {
            clearConsole();
            printHeading("MOVING TO VILLAGE......");

            // if player has no gold then they should not be allowed to shop, so just continue the journey
            if (this.gold == 0) {
                System.out.println("You have no gold....");
            } else {
                this.shop();
            }
            // if the player is in the forest, should be moved to the mountains to continue the story
            if (playerLocation.equals(LOCATION.FOREST)) {
                playerLocation = LOCATION.MOUNTAINS;
                checkpoint();
                continueJourney();
                //if the player's location is in the mountains, then should be moved to the mountains
            } else if (playerLocation.equals(LOCATION.MOUNTAINS)) {
                playerLocation = LOCATION.FOREST;
                checkpoint();
                continueJourney();
            }

            // PLAYERS ACTIONS IN THE FINAL BATTLE
        } else if (location.equals(LOCATION.FINALBATTLE)) {
            printHeading("MOVING TO FINAL BATTLE.....");
            this.playerLocation = LOCATION.FINALBATTLE;
            checkpoint();
            continueJourney();
        }


    }

    // Method that allows the user to shop if they have gold
    public void shop() {
        printHeading("you meet a mysterious stranger. \n He offers you something:");
        int price = (int) (Math.random() * (10 + this.pots * 3) + 10 + this.pots); // calculating a random price
        System.out.println("-Magic Potion: " + price + " gold.");
        printSeparator(20);
        // ask the player to buy one
        System.out.println("Do you want to buy one?\n(1) Yes!\n(2) No thanks.");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        // check if player wants to buy
        if (input == 1) {
            clearConsole();
            // check if player has enough gold
            if (this.gold >= price) {
                printHeading("You brought a magical potion for " + price + "gold.");
                this.pots++;
                this.gold -= price;
            } else {
                printHeading("You don't have enough gold to buy this...");
            }
            pressEnterKeyToContinue();
        }
    }

    // Method that allows the player to look out the window
    public void look(String from) {
        if (from.equals("window")) {
            if (this.playerLocation.equals(LOCATION.ROOM)) {
                if (playerInv.getItems("broom")) { // if the player has the broom
                    System.out.println("You look over the window seeing as far as the eye can see.");
                    System.out.println("In the far distance you see a forest, mountains and a village.");
                    System.out.println("Do you travel to?: ");
                    System.out.println("(1) - The Forbidden Forest\n(2) - The Misty Mountains\n(3) - The Vast Village\n(4) - No where, stay in room");
                    System.out.println("Where do you wish to explore? ");
                    Scanner scanner = new Scanner(System.in);
                    int input = scanner.nextInt();
                    switch (input) {
                        case (1):
                            moveTo(LOCATION.FOREST); // player chooses the move to the forest
                            break;
                        case (2):
                            moveTo(LOCATION.MOUNTAINS); // player chooses to move to the mountains
                            break;
                        case (3):
                            moveTo(LOCATION.VILLAGE); // player chooses to move to the village
                            break;
                        case (4):
                            printSeparator(40);
                            System.out.println("You choose to stay in the room."); // player chooses to stay in the room
                            continueJourney();
                            break;
                    }

                } else {
                    printSeparator(40);
                    System.out.println("You do not have a broom.");
                    System.out.println("Do you try travel to location anyways?");
                    System.out.println("(1) - Yes!!!\n(2) - No, I do not want to risk my life yet....");
                    Scanner scanner = new Scanner(System.in);
                    int input = scanner.nextInt();
                    switch (input) {
                        case (1):
                            playerDied();
                            restartGame();
                            break;
                        case (2):
                            printSeparator(40);
                            System.out.println("You choose to stay in the room. You decide this was a sensible decision."); // player chooses to stay in the room
                            continueJourney();
                            break;
                    }
                    continueJourney();

                }
            }
        }

    }

    // Method that allows the player to use a healing potion if they have one
    public void usePotion() {
// use potion
        //clearConsole();
        if (this.pots > 0 && this.hp < this.maxHp) {
            // Player CAN take potion
            // make sure the player wants to drink potion
            printHeading("Do you want to drink a potion? (" + this.pots + " left.)");
            System.out.println("(1) - Yes\n(2) No, maybe later");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input == 1) {
                // player actually took it
                this.hp = this.maxHp;
                clearConsole();
                printHeading("You deal a magic potion. It restored your health back to " + this.maxHp);
                pressEnterKeyToContinue();
            } else {
                System.out.println("you choose not the drink the potion");
            }
        } else {
            // player CANNOT take potion
            printHeading("You don't have any potions or you're at full health");
            pressEnterKeyToContinue();
        }
    }

    // Method that allows the player to try and fly away from enemy
    public void tryEscape(Enemy enemy) {
        // FlY AWAY
        if (this.playerInv.getItems("broom")) {
            clearConsole();
            // checks that player isn't in the final battle
            if (!(playerLocation.equals(LOCATION.FINALBATTLE))) {
                // chance of 35% to escape
                if (Math.random() * 10 + 1 <= 3.5) {
                    printHeading("You were able to fly away... this time!!");
                    enemy.isAlive = false;
                    pressEnterKeyToContinue();
                } else {
                    printHeading("You didn't manage to escape.");
                    // calculate damage the player takes
                    int dmgTook = enemy.attack();
                    this.hp = this.hp - dmgTook;
                    System.out.println("In your hurry you took " + dmgTook + " damage!");
                    pressEnterKeyToContinue();
                    // check if player is still alive
                    if (this.hp <= 0) {
                        playerDied();
                    }
                }
            } else {
                printHeading("YOU CANNOT ESCAPE THE FINAL BATTLE !!!!");
                pressEnterKeyToContinue();
            }
        } else {
            System.out.println("You do not have a broom to fly away with!!");
            // go back to the battle
        }


    }


    // nested class
    public class RandArray {
        private static Random rand = new Random();

        private static <T> T randomFrom(T... items) {
            return items[rand.nextInt(items.length)];
        }
    }

    // Method that calculates a random loot from killing enemy
    public void randomDrops(Enemy enemy) {
        // random drops
        String[] items = new String[]{"gold", "pots"};
        String result = RandArray.randomFrom(items);
        if (result.equals("gold")) {
            int goldEarned = (int) (Math.random() * enemy.xp);
            System.out.println("You collect " + goldEarned + " gold from the " + enemy.name + "'s corpse!");
            this.gold = this.gold + goldEarned;
        } else if (result.equals("pots")) {
            int potsEarned = (int) (Math.random() * enemy.xp);
            System.out.println("You collect " + potsEarned + " potions from the " + enemy.name + "'s corpse!");
            this.pots = this.pots + potsEarned;
        }

    }

    // Method that calculates a random find in explore
    public void randomFind() {
        String[] items = new String[]{"gold", "pots"};
        String result = RandArray.randomFrom(items);
        if (result.equals("gold")) {
            int goldEarned = 3 + (int) (Math.random() * this.xp);
            System.out.println("You found " + goldEarned + " gold from the " + playerLocation);
            this.gold = this.gold + goldEarned;
        } else if (result.equals("pots")) {
            int potsEarned = (int) (Math.random() * this.xp);
            System.out.println("You found " + potsEarned + " potions from the " + playerLocation);
            this.pots = this.pots + potsEarned;
        }

    }

    // Method that allows game to be saved
    public void saveGame() {
        SaveData data = new SaveData();
        data.name = this.name;
        data.hp = this.hp;
        data.maxHp = this.maxHp;
        data.xp = this.xp;
        data.numAttackUpgrades = this.numAttackUpgrades;
        data.numDefUpgrades = this.numDefUpgrades;
        data.gold = this.gold;
        data.pots = this.pots;
        data.chosenAttackTraits = this.chosenAttackTraits;
        data.chosenDefensiveTraits = this.chosenDefensiveTraits;
        data.playerInv = this.playerInv;
        data.playerLocation = this.playerLocation;
        data.isAlive = this.isAlive;
        try {
            ResourceManger.save(data, "test.save");
            printHeading("GAME SAVED");
        } catch (Exception e) {
            System.out.println("Could not save game " + e.getMessage());
        }

    }

    // Method that allows game to be reloaded from saved data
    public void loadGame() {
        try {
            SaveData data = (SaveData) ResourceManger.load("test.save");
            this.name = data.name;
            this.hp = data.hp;
            this.maxHp = data.maxHp;
            this.xp = data.xp;
            this.numAttackUpgrades = data.numAttackUpgrades;
            this.numDefUpgrades = data.numDefUpgrades;
            this.gold = data.gold;
            this.pots = data.pots;
            this.chosenAttackTraits = data.chosenAttackTraits;
            this.chosenDefensiveTraits = data.chosenDefensiveTraits;
            this.playerInv = data.playerInv;
            this.playerLocation = data.playerLocation;
            this.isAlive = data.isAlive;

            this.characterInfo();
            continueJourney(); // this should be starting from the players location
        } catch (Exception e) {
            System.out.println("Could not load save game " + e.getMessage());
        }

    }

    // Method for creating a checkpoint in the game and saving the game data if the player wants
    public void checkpoint() {
        printHeading("\tCheckPoint Reached");
        System.out.println("Would you like to save game? ");
        System.out.println("(1) - Yes!\n(2)- No, continue without saving.");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            this.saveGame();
        }
    }

    // Method that chooses a random spell for the player
    public void randomSpell() {
        final String[] spells = {"Thunder Spike", "Leverous", "Illumiesco", "Experio", "Wave of the Total Eclipse", "Burst of Frost fire"};
        Random random = new Random();
        int index = random.nextInt(spells.length); // choosing a random index
        System.out.println("You are using the " + spells[index] + " spell.");
    }

    // local testing
//    public static void main(String[] args) {
//        Player p1 = new Player("chanel");
////        p1.chooseTrait();
////        p1.characterInfo();
//
//        Enemy enemy1 = new Spider(5, 5);
//
//        p1.randomDrops(enemy1);
//        Item wand = new Item("wand");
//        p1.playerInv.addItem(wand.getName());
//        p1.randomSpell();
//
////        p1.useMagic(enemy1);
//    }


}
