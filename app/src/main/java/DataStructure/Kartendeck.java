package DataStructure;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Kerstin on 29.03.2015.
 */
public class Kartendeck {

    Dictionary<String,Integer> Kartenuntermenge;
    Dictionary<String, Dictionary<String,Integer>> Kartenmenge;

    public Kartendeck() {
        Kartenuntermenge = new Hashtable<>();
        Kartenmenge = new Hashtable<>();
    }

    public void Befüllen()
    {
        Kartenuntermenge.put("Bube",2);
        Kartenuntermenge.put("Dame",3);
        Kartenuntermenge.put("König",4);
        Kartenuntermenge.put("10er",10);
        Kartenuntermenge.put("Ass",11);

        Kartenmenge.put("Herz",Kartenuntermenge);
        Kartenmenge.put("Karo",Kartenuntermenge);
        Kartenmenge.put("Pik",Kartenuntermenge);
        Kartenmenge.put("Kreuz", Kartenuntermenge);
    }



}
