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
 * Created by n15r2 on 27.05.15.
 */
public class Spiel320erTest {

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
    public void ansagen20er_funktioniert() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);


        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        assertEquals("S1 hat 20er", 1, spiel.hat20er(spiel.getS1()).size());
        assertEquals("S2 hat keinen 20er", 0, spiel.hat20er(spiel.getS2()).size());

        if (1 == spiel.hat20er(spiel.getS1()).size()) {
            spiel.Ansagen20er("Herz", spiel.getS1());
        }


        assertEquals("S1 hat 20er angesagt", 20, spiel.getS1().getMerkePunkte());

    }

    @Test
    public void ansagen20er_fehler() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        assertEquals("S1 hat keinen 20er", 0, spiel.hat20er(spiel.getS1()).size());
        assertEquals("S2 hat keinen 20er", 0, spiel.hat20er(spiel.getS2()).size());

    }


}
