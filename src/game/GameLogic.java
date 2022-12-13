/**********************************************************************************************************************
 File        : GameLogic.java

 @author      : Chanel Morgan

 Description :  Class that contains all the game's functionality and its main story progression. This class contains
 the game's logic.
 ********************************************************************************************************************/

package game;

import gameobjects.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {

    //Variables
    private static Scanner scanner = new Scanner(System.in);
    private static Player player;
    public static boolean isRunning;


    // Method to simulate clearing out the console
    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    // Method to print a separator with length n
    public static void printSeparator(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    // Method to print a heading
    public static void printHeading(String title) {
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    // Method that stops the game until the player press enter
    public static void pressEnterKeyToContinue() {
        System.out.println("Press Enter key to continue.... ");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    // Method that starts the game - setting the users character and setting the scene
    public static void startGame() {
        boolean nameSet = false;
        String name;
        // print title screen
        printSeparator(40);
        System.out.println("THE ORDER OF THE DRAGON");
        System.out.println("Interactive Fiction Game By Chanel Morgan");
        printSeparator(40);
        pressEnterKeyToContinue();

        // getting the player name
        do {
            clearConsole();
            // should do an intro to the story here
            Story.intro();

            System.out.println("First your character needs a name.");
            printHeading("What's your name?");
            name = scanner.nextLine();
            //asking the player if he wants to correct his choice
            clearConsole();
            printHeading("Your name is " + name + "\nIs that correct? ");
            System.out.println(" (1) Yes!");
            System.out.println(" (2) No, I Want to change my name");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("1")) {
                nameSet = true;
            }

        } while (!nameSet);

        //creating new player object with the name
        player = new Player(name);

        // have the character's info displayed here
        player.characterInfo();

        // setting isRunning to true, so the game loop can continue
        isRunning = true;

        clearConsole();
        start();
    }

    // Method to continue the player's journey
    public static void continueJourney() {
        while (player.isAlive) {
            switch (player.getPlayerLocation().getNumber()) {
                case (1):
                    clearConsole();
                    printSeparator(40);
                    System.out.println("You are in a room, there is a window, a door and a broom");
                    System.out.println("Do you 1: Look out the window");
                    System.out.println("Do you 2: Open the door and leave");
                    // if the player does not have a broom inventory they are able to see broom in room
                    if (!(player.getPlayerInv().getItems("broom"))) // need to be checking if the player has the broom
                    {
                        System.out.println("Do you 3: Move to the broom");
                    }

                    // taking in the users input to see their option
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    switch (input) {
                        case "1":
                            player.look("window"); // our look method will allow the user to look at 3 different places and make a decision
                            break;
                        case "2":
                            player.moveTo(LOCATION.HALLWAY); // moving to hallway method
                            break;
                        case "3":
                            if (!player.getPlayerInv().getItems("broom")) {
                                player.takeBroom();
                            }
                            break;
                    }

                    break;

                // FOREST
                case (2):
                    // in most cases we have moved to the forest
                    System.out.println("You are now approaching an opening in between the thick tree trunks.");
                    pressEnterKeyToContinue();
                    // player encountering some events
                    Story.enteringForest();
                    pressEnterKeyToContinue();
                    explore();
                    Enemy enemy1 = new Spider(10, 10);
                    battle(enemy1);
                    pressEnterKeyToContinue();
                    Enemy enemy2 = new Spider(10, 10);
                    battle(enemy2);
                    pressEnterKeyToContinue();
                    explore();
//                    pressEnterKeyToContinue();
//                    Enemy enemy3 = new Spider(5,5);
//                    battle(enemy3);
//                    pressEnterKeyToContinue();
                    // story continues here
                    Story.afterForestBattles();
                    System.out.println("After each spider attack you were award xp.");
                    // if the xp is more than 10 ( which it should be)
                    // user is able to unlock a new trait
                    if (player.xp >= 10) {
                        System.out.println("Winning these battles means your character has enough exp to choose a new trait.");
                        // player is allowed to choose a new trait
                        player.chooseTrait();
                        // maybe ask player if they want to see character info
                        player.characterInfo();
                        // method to increase the players attack or damage ?
                    } else {
                        System.out.println("You do not currently have enough xp to choose a new trait.");
                    }

                    System.out.println("You move along in your journey and come across two paths");
                    System.out.println("Do you 1: choose path A");
                    System.out.println("Do you 2: choose path B");
                    Scanner scanner1 = new Scanner(System.in);
                    int path = scanner1.nextInt();
                    if (path == 1) {
                        // path A - this will lead to the village
                        player.moveTo(LOCATION.VILLAGE);
                    } else {
                        // path B - mountains
                        Story.leavingForest();
                        player.moveTo(LOCATION.MOUNTAINS);
                    }
                    break;
                // VILLAGE
                case (4):
                    System.out.println("You are now approaching the village....");
                    pressEnterKeyToContinue();
                    player.moveTo(LOCATION.VILLAGE);
                    break;
                // MOUNTAINS
                case (5):
                    Story.enteringMountains();
                    // could have an avoid the falling rocks section here..
                    //player.moveTo(LOCATION.MOUNTAINS);

                    System.out.println("You have made it to the top...");
                    System.out.println("However, here there seems to be moving rocks...");
                    System.out.println("a boulder comes hurdling towards you");
                    System.out.println("Through the mist you make out a stone monster!!!");
                    printSeparator(40);
//                Enemy enemy4 = new StoneMonster(10, 10);
//                battle(enemy4);
                    pressEnterKeyToContinue();
                    Enemy enemy5 = new StoneMonster(20, 15);
                    battle(enemy5);
                    pressEnterKeyToContinue();
                    Enemy enemy6 = new StoneMonster(20, 15);
                    battle(enemy6);
                    pressEnterKeyToContinue();
//                    explore();
//                    pressEnterKeyToContinue();
//                    Enemy enemy7 = new StoneMonster(10, 10);
//                    battle(enemy7);
//                    pressEnterKeyToContinue();
                    Story.endOfMountains();
                    if (player.xp >= 10) {
                        System.out.println("Winning these battles means your character has enough xp to choose a new trait.");
                        // player is allowed to choose a new trait
                        player.chooseTrait();
                        // maybe ask player if they want to see character info
                        player.characterInfo();
                        // method to increase the players attack or damage ?
                    } else {
                        System.out.println("You do not currently have enough exp to choose a new trait.");
                    }
                    System.out.println("In the corner of your eye you see a small village down.");
                    System.out.println("Do you travel to the village? ");
                    System.out.println("(1) - Yes, a break from all this climbing would be nice.\n(2)- No, freedom is just ahead.");
                    Scanner scanner2 = new Scanner(System.in);
                    int option = scanner2.nextInt();
                    if (option == 1) {
                        player.shop();
                        player.moveTo(LOCATION.FINALBATTLE);
                    } else {
                        player.moveTo(LOCATION.FINALBATTLE);
                    }
                    break;
                case (6):
                    // continue the journey to the final battle
                    Story.introToFinal();
                    // player now fights the dragon
                    System.out.println("Your fight with the dragon is now about to being.");
                    System.out.println("Are you ready?");
                    pressEnterKeyToContinue();
                    Dragon dragon = new Dragon(50, 25);
                    battle(dragon);
                    System.out.println("Story here about defeating the dragon...");
                    Story.endDragon();
                    System.out.println("You defeated the Dragon!!!");

                    Story.endDragon();
                    won(); //in this method show the player their stats for the game, and ask them in they want to play again
                    // end
                    break;
            }
        }


    }

    // Method if the player has won the game
    public static void won() {
        System.out.println("Would you like to see your characters final stats: ");
        System.out.println("(1) - Yes!!\n(2)- No, thank you.");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            player.characterInfo();
            // could have different method here to show kills ?
        }
        System.out.println("Thank you for playing.");
        // method called play again
        playAgain();
    }

    // Method that asks the player if they would like to play the game alone
    public static void playAgain() {
        System.out.println("Would you like to play again?");
        System.out.println("(1) - Yes!\n(2) - No, thank you");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            player = null;
            startGame();
        } else {
            player.isAlive = false;
        }
    }

    // Method that starts the game
    public static void start() {
        System.out.println("Are you ready to start your adventure?..... ");
        System.out.println("(1) - yes!!\n(2)- No, not ready yet");
        int input = scanner.nextInt();
        if (input == 1) {
            // call a method to start the main game
            continueJourney();
        } else {
            clearConsole();
            mainMenu();
            Scanner scannerForOptions = new Scanner(System.in);
            int option = scannerForOptions.nextInt();
            switch (option) {
                case (1):
                    System.out.println("You choose to continue your journey");
                    continueJourney();
                    break;
                case (2):
                    System.out.println("You choose to see your character info");
                    player.characterInfo();
                    clearConsole();
                    start();
                    break;
                case (3):
                    System.out.println("You choose to exit the game :(");
                    isRunning = false;
                    break;
                case (4):
                    restartGame();
                    break;
                case (5):
                    player.saveGame();
                    System.out.println("Would you like to load the game? ");
                    System.out.println("(1)- yes!\n(2) - No, restart Game please\n(3) - Exit Game");
                    Scanner scn = new Scanner(System.in);
                    int choice = scn.nextInt();
                    if (choice == 1) {
                        player.loadGame();
                    } else if (choice == 2) {
                        restartGame();
                    } else {
                        break;
                    }
                    break;
            }
        }


    }

    // Method allows the player to explore the players location
    public static void explore() {
        if (player.getPlayerLocation().equals(LOCATION.FOREST)) {
            printHeading("You are currently exploring the forest.");
            //getRandomLine("ExploreTextForest"); // choose a random line of story
            System.out.println(getRandomLine("ExploreTextForest"));
            player.randomFind(); // random finds for the player
            pressEnterKeyToContinue();
        } else if (player.getPlayerLocation().equals(LOCATION.MOUNTAINS)) {
            //System.out.println("You are exploring the Mountains.....");
            printHeading("You are currently exploring the mountains.");
            //getRandomLine("ExploreTextMountains");
            System.out.println(getRandomLine("ExploreTextMountains"));
            player.randomFind();
            pressEnterKeyToContinue();
        }

    }

    // Method for when the player fights an enemy
    public static void fight(Enemy enemy1) {
        int dmg = player.attack() - enemy1.defend();
        int dmgTook = enemy1.attack() - player.defend();
        // check that dmg isn't negative
        if (dmgTook < 0) {
            // add some dmg if player defends very well
            dmg -= dmgTook / 2;
            dmgTook = 0;
        }
        if (dmg < 0) {
            dmg = 0;
        }
        // deal damge to both parties
        player.hp -= dmgTook;
//        player.hp -= dmgTook;
//        enemy.hp -= dmg;
        int newEnemyHp = enemy1.getHp() - dmg;
        enemy1.setHp(newEnemyHp);
        // print the info of this battle round
        printHeading("BATTLE");
        System.out.println("You dealt " + dmg + " damage to the " + enemy1.getName() + ".");
        printSeparator(15);
        System.out.println("The " + enemy1.getName() + " dealt " + dmgTook + " damage to you.");
        pressEnterKeyToContinue();
        // need to be checking if player is alive and enemy here
        if (player.hp <= 0) {
            playerDied();
            enemy1.setAlive(false);
            restartGame();
            // method to end the game
        } else if (enemy1.getHp() <= 0) {
            // tell the player they have won
            clearConsole();
            printHeading("You defeated the " + enemy1.getName() + "!"); // this should be printed at the end of every battle
            // increase the player xp
            player.xp += enemy1.getXp();
            System.out.println("You earned " + enemy1.getXp() + "XP!");
            //enemy1.isAlive = false; // i want this to be killing the spider object so next time we can make a new one
            // random drop
            player.randomDrops(enemy1);
            enemy1.setAlive(false);
        }

    }

    // Method for when the player is using magic to fight an enemy
    public static void useMagic(Enemy enemy1) {
        int dmg = player.magic() - enemy1.defend();
        int dmgTook = enemy1.attack() - player.defend();
        // check that dmg isn't negative
        if (dmgTook < 0) {
            // add some dmg if player defends very well
            dmg -= dmgTook / 2;
            dmgTook = 0;
        }
        if (dmg < 0) {
            dmg = 0;
        }
        // deal damge to both parties
        player.hp -= dmgTook;
//        player.hp -= dmgTook;
//        enemy.hp -= dmg;
        int newEnemyHp = enemy1.getHp() - dmg;
        enemy1.setHp(newEnemyHp);
        // print the info of this battle round
        printHeading("BATTLE");
        System.out.println("You dealt " + dmg + " damage to the " + enemy1.getName() + ".");
        printSeparator(15);
        System.out.println("The " + enemy1.getName() + " dealt " + dmgTook + " damage to you.");
        pressEnterKeyToContinue();
        // need to be checking if player is alive and enemy here
        if (player.hp <= 0) {
            playerDied();
            enemy1.setAlive(false);
            restartGame();
            // method to end the game
        } else if (enemy1.getHp() <= 0) {
            // tell the player they have won
            clearConsole();
            printHeading("You defeated the " + enemy1.getName() + "!"); // this should be printed at the end of every battle
            // increase the player xp
            player.xp += enemy1.getXp();
            System.out.println("You earned " + enemy1.getXp() + "XP!");
            //enemy1.isAlive = false; // i want this to be killing the spider object so next time we can make a new one
            // random drop
            player.randomDrops(enemy1);
            enemy1.setAlive(false);
        }

    }

    // Method for the battles between the enemies and the player
    public static void battle(Enemy enemy1) {
        // main battle loop
        while (enemy1.isAlive()) {
            printHeading("AN AGGRESSIVE " + enemy1.getName().toUpperCase() + " IS ATTACKING. WHAT DO YOU DO?");
            printHeading(enemy1.getName() + "\nHP: " + enemy1.getHp() + "/" + enemy1.getMaxHp());
            printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHp);
            System.out.println("Choose an action: ");
            printSeparator(20);
            // if the player has a wand and broom
            if (player.getPlayerInv().getItems("wand") && player.getPlayerInv().getItems("broom")) {
                System.out.println("(1) Fight\n(2) Use Potion\n(3) Use magic\n(4) Fly Away");
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                // react accordingly to player input
                switch (input) {
                    // fight with hands no magic
                    case (1):
                        fight(enemy1);
                        break;
                    // use potion to heal health
                    case (2):
                        player.usePotion();
                        break;
                    // use magic
                    case (3):
                        useMagic(enemy1);
                        break;
                    // attempt to fly away
                    case (4):
                        player.tryEscape(enemy1);
                        break;
                }

            } else if (player.getPlayerInv().getItems("wand")) {
                System.out.println("(1) Fight\n(2) Use Potion\n(3) Use magic");
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                // react accordingly to player input
                switch (input) {
                    // fight with hands no magic
                    case (1):
                        fight(enemy1);
                        break;
                    // use potion to heal health
                    case (2):
                        player.usePotion();
                        break;
                    // use magic
                    case (3):
                        // not working yet
                        useMagic(enemy1);
                        break;
                }

            } else if (player.getPlayerInv().getItems("broom")) {
                System.out.println("(1) Fight\n(2) Use Potion\n(3) Fly Away");
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                // react accordingly to player input
                switch (input) {
                    // fight with hands no magic
                    case (1):
                        fight(enemy1);
                        break;
                    // use potion to heal health
                    case (2):
                        player.usePotion();
                        break;
                    // attempt to fly away
                    case (3):
                        player.tryEscape(enemy1);
                        break;
                }

            } else { // the player has none of these options
                System.out.println("(1) Fight\n(2) Use Potion");
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                // react accordingly to player input
                switch (input) {
                    // fight with hands no magic
                    case (1):
                        fight(enemy1);
                        break;
                    // use potion to heal health
                    case (2):
                        player.usePotion();
                        break;
                }

            }

        }
    }

    // Method that displays the main menu of the game
    public static void mainMenu() {
        System.out.println("Choose an option: ");
        printSeparator(20);
        System.out.println(" (1) continue on your journey ");
        System.out.println(" (2) Character Info");
        System.out.println(" (3) Exit Game");
        System.out.println(" (4) Restart Game");
        System.out.println(" (5) Save Game ");

    }

    // Method that aks the player if they want to restart the game
    public static void restartGame() {
        // reset player method
        System.out.println("Do you wish to restart the game? ");
        System.out.println("(1) - Yes\n(2)- No ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 1) {
            // reset the characters values
            // then take them back to the start of the game
            player = null;
            startGame();

        } else {
            if (player.isAlive || !(player.getPlayerLocation().equals(LOCATION.FINALBATTLE))) {
                continueJourney();
            } else {
                playerDied();
            }

        }
    }

    // Method for when the player has died
    public static void playerDied() {
        clearConsole();
        printHeading("You died....");
        printHeading("You earned " + player.xp + "XP on your journey. ");
        System.out.println("Thank you for playing :)");
        player.isAlive = false;
    }

    // Method to get a random line from a file
    private static String getRandomLine(String path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Random random = new Random();
        return lines.get(random.nextInt(lines.size()));
    }


//    // local testing harness
//    public static void main(String[] args) {
//        Player p1 = new Player("chanel");
////        p1.chooseTrait();
////        p1.characterInfo();
//
//        Enemy enemy1 = new Spider(5,5);
//        getRandomLine("ExploreTextForest");
//        System.out.println(getRandomLine("ExploreTextForest"));
//
//    }
//
//

}
