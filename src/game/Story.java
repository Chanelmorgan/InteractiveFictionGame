/**********************************************************************************************************************
 File        : Story.java

 @author      : Chanel Morgan

 Description :  Class that is holding the lines of the main story.
 ********************************************************************************************************************/
package game;


public class Story {

    // Method for the introduction of the story
    public static void intro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("INTRO...");
        GameLogic.printSeparator(30);
        System.out.println("You wake up in a room, located within the castle. You have no memory of how you ended up here," +
                " all you know is that you need to escape.");
        GameLogic.pressEnterKeyToContinue();

    }

    // Method for when the player is leaving the castle via the hallway
    public static void outOfCastle() {
        GameLogic.printSeparator(40);
        System.out.println("You continue out of the castle, pushing the wooden doors wide open.");
        System.out.println("You close your eyes and burst through to the outside world.");
        System.out.println("As your eyes adjust you become aware of the thousands of trees towering over you.");
        GameLogic.printSeparator(40);

    }

    // Method for when the player is entering the forest location
    public static void enteringForest() {
        GameLogic.clearConsole();
        System.out.println("You begin to dart through the gaps of the damp tree trunk while springing over ever-growing tree roots, " +
                "becoming more and more unaware of how deep you are in the forest.");
        System.out.println("The forest is full of all kinds of creatures.");
        System.out.println("However a word of warning, look out for the spiders. These may attack at any time.");
        System.out.println("If you are lucky enough to survive these attacks, you may be in for the chance of discovering some treasures.");
        System.out.println("Its time to continue running.");
        GameLogic.printSeparator(40);
    }

    // Method for after the player has been through the forest battles
    public static void afterForestBattles() {
        GameLogic.printSeparator(40);
        System.out.println("You make it out past the spiders alive and have made it to a less dangerous area of forest.");
        System.out.println("After exploring the forest and encountering many spider attacks.");
        GameLogic.printSeparator(40);
        GameLogic.pressEnterKeyToContinue();
    }


    // Method for story when the player leaves forest and goes to mountains
    public static void leavingForest() {
        GameLogic.printSeparator(40);
        System.out.println("You begin to see daylight piercing through some smaller trees." +
                " From these you assume you are reaching the end of the forest.  ");
        System.out.println("Eventually, you reach an edge of the forest. However, the challenge does not seem over yet…. ");
        System.out.println("In front of you stands everlasting mountain, looking up its unclear where they end.");
        GameLogic.printSeparator(40);
        GameLogic.pressEnterKeyToContinue();
    }

    // Method for entering the mountains from any location
    public static void enteringMountains() {
        GameLogic.printSeparator(40);
        System.out.println("Rocks tumble down from the sky, you can dodge these this time.");
        System.out.println("However, you know if you stand still much longer one is bond to hit you soon enough.");
        System.out.println("Moving into the mist and up the mountains……");
        GameLogic.printSeparator(40);
        GameLogic.pressEnterKeyToContinue();
    }

    // Method for the end of the mountains
    public static void endOfMountains() {
        GameLogic.printSeparator(40);
        System.out.println("You have destroyed all visible Stone Monsters.");
        System.out.println("The rest of the journey over the mountains seems clear.");
        System.out.println("You can see freedom on the horizon. ");
        GameLogic.printSeparator(40);
        GameLogic.pressEnterKeyToContinue();

    }

    // Method for the intro to the final battle
    public static void introToFinal() {
        GameLogic.printSeparator(40);
        System.out.println("You thought freedom was just around the corner." +
                " However, it appears to you now that this was untrue and that" +
                " the peaceful land is guarded by a vicious red flame dragon.  ");
        GameLogic.printSeparator(40);
        GameLogic.pressEnterKeyToContinue();

    }

    public static void endDragon() {
        GameLogic.printSeparator(40);
        System.out.println("You have defeated the Dragon.");
        System.out.println("You finally run to freedom.");
        GameLogic.printHeading("THE END");
    }


}