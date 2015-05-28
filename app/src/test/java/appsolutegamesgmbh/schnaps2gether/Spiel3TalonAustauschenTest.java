package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel3TalonAustauschenTest {
    private ArrayList<Karte> talon;

    Karte herzbube = new Karte("Herz","Bube",2);
    Karte herzdame = new Karte("Herz","Dame",3);
    Karte herzkoenig = new Karte("Herz","König",4);
    Karte herz10 = new Karte("Herz","10er",10);
    Karte herzass = new Karte("Herz", "Ass", 11);

    Karte karobube = new Karte("Karo","Bube",2);
    Karte karodame = new Karte("Karo","Dame",3);
    Karte karokoenig = new Karte("Karo","König",4);
    Karte karo10 = new Karte("Karo","10er",10);
    Karte karoass = new Karte("Karo","Ass",11);

    Karte pikbube = new Karte("Pik","Bube",2);
    Karte pikdame = new Karte("Pik","Dame",3);
    Karte pikkoenig = new Karte("Pik","König",4);
    Karte pik10 = new Karte("Pik","10er",10);
    Karte pikass = new Karte("Pik","Ass",11);

    Karte kreuzbube = new Karte("Kreuz","Bube",2);
    Karte kreuzdame = new Karte("Kreuz","Dame",3);
    Karte kreuzkoenig = new Karte("Kreuz","König",4);
    Karte kreuz10 = new Karte("Kreuz","10er",10);
    Karte kreuzass = new Karte("Kreuz","Ass",11);

    @Before
    public void setUp() {

    }

    @Test
    public void TalonWirdmitSpielerkarteAusgetauscht_S1() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        spiel.getTalon().removeAll(spiel.getTalon());

        //Talonkarten ausgeben
        spiel.getTalon().add(kreuz10);
        spiel.getTalon().add(kreuzass);

        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        spiel.TalonAustauschen(kreuzass, herzbube, spiel.getS1());

        if (spiel.getS1().Hand.get(5).getFarbe().contains("Kreuz")) {
            if (spiel.getTalon().get(1).getFarbe().contains("Herz")) {
                assertEquals("herz mit kreuz getauscht", 6, spiel.getS1().Hand.size());
            }
        }
        else
        {
            assertTrue(false);
        }

    }


}
