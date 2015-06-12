package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;

/**
 * Created by n15r2 on 12.05.15.
 */
public class Spiel4AusspielenTest {
    Bummerl4 bummerl = new Bummerl4();

    Karte herzbube = new Karte("Herz", "Bube", 2);
    Karte herzdame = new Karte("Herz", "Dame", 3);
    Karte herzkoenig = new Karte("Herz", "König", 4);
    Karte herz10 = new Karte("Herz", "10er", 10);
    Karte herzass = new Karte("Herz", "Ass", 11);

    Karte karobube = new Karte("Karo", "Bube", 2);
    Karte karodame = new Karte("Karo", "Dame", 3);
    Karte karokoenig = new Karte("Karo", "König", 4);
    Karte karo10 = new Karte("Karo", "10er", 10);
    Karte karoass = new Karte("Karo", "Ass", 11);

    Karte pikbube = new Karte("Pik", "Bube", 2);
    Karte pikdame = new Karte("Pik", "Dame", 3);
    Karte pikkoenig = new Karte("Pik", "König", 4);
    Karte pik10 = new Karte("Pik", "10er", 10);
    Karte pikass = new Karte("Pik", "Ass", 11);

    Karte kreuzbube = new Karte("Kreuz", "Bube", 2);
    Karte kreuzdame = new Karte("Kreuz", "Dame", 3);
    Karte kreuzkoenig = new Karte("Kreuz", "König", 4);
    Karte kreuz10 = new Karte("Kreuz", "10er", 10);
    Karte kreuzass = new Karte("Kreuz", "Ass", 11);

    @Before
    public void setUp() throws WrongGameException {
    }

    @Test
    public void S1_spieltKarteAus() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        assertEquals("Spieler1 hat Karte ausgespielt", 4, spiel.getS1().Hand.size());
    }

    @Test
    public void Spieler2spieltKarteAus() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        assertEquals("Spieler2 hat Karte ausgespielt", 4, spiel.getS2().Hand.size());
    }

    @Test
    public void Spieler3spieltKarteAus() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());
        assertEquals("Spieler3 hat Karte ausgespielt", 4, spiel.getS3().Hand.size());
    }

    @Test
    public void Spieler4spieltKarteAus() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler4
        spiel.getS4().Hand.add(pikbube);
        spiel.getS4().Hand.add(pikdame);
        spiel.getS4().Hand.add(pikkoenig);
        spiel.getS4().Hand.add(pik10);
        spiel.getS4().Hand.add(pikass);

        spiel.Auspielen(spiel.getS4().Hand.get(0), spiel.getS4());
        assertEquals("Spieler4 hat Karte ausgespielt", 4, spiel.getS4().Hand.size());
    }
}
