/**********************************************************************************************************************
 File        : ResourceManger.java

 @author      : Chanel Morgan

 Description :  Class that is saving and loading the Objects data, reading this to and from a file
 ********************************************************************************************************************/
package game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ResourceManger {

    // Method that saves the object using serializable
    // takes in a serializable object
    // fileName is where we are saving it to
    public static void save(Serializable data, String fileName) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(data);
        }
    }

    // Method that loads the saved object data from a file
    public static Object load(String fileName) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            return ois.readObject();
        }
    }

}
