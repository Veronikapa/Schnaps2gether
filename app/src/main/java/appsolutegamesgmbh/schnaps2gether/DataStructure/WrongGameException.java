package appsolutegamesgmbh.schnaps2gether.DataStructure;

/**
 * Created by Kerstin on 05.05.2015.
 */
public class WrongGameException extends Exception {
    public WrongGameException() {
        super("Ihr eingegebenes Spiel existiert nicht!");
    }
}
