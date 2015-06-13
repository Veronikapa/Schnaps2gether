package appsolutegamesgmbh.schnaps2gether;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Spiel2zudrehenTest {

    Bummerl2 bummerl = new Bummerl2();

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
    public void setUp() {


    }

    @Test
    public void zugedreht_S1() {
        Spiel2 spiel = new Spiel2(0);

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        assertTrue("Es ist nicht zugedreht", !spiel.isZugedreht());
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.ZugAuswerten(herzbube, spiel.getS2().Hand.get(0), 0);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);

        assertTrue("Es ist nicht zugedreht", !spiel.isZugedreht());
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), 0);
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);

        spiel.Zudrehen(spiel.getS1()); //Spieler1 dreht zu
        assertTrue("Es ist zugedreht", spiel.isZugedreht());
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0),0);
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        assertEquals("keine neuen Karten werden ausgeteilt (S1 Handkarten 4)", 4, spiel.getS1().Hand.size());
        assertEquals("keine neuen Karten werden ausgeteilt (S2 Handkarten 4", 4, spiel.getS2().Hand.size());

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), 0);
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        assertEquals("keine neuen Karten werden ausgeteilt (S1 Handkarten 3)", 3, spiel.getS1().Hand.size());
        assertEquals("keine neuen Karten werden ausgeteilt (S2 Handkarten 3)", 3, spiel.getS2().Hand.size());
    }

    @Test
    public void zugedreht_S2() {
        Spiel2 spiel = new Spiel2(0);

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        assertTrue("Es ist nicht zugedreht", !spiel.isZugedreht());
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.ZugAuswerten(herzbube, spiel.getS2().Hand.get(0), 0);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);

        assertTrue("Es ist nicht zugedreht", !spiel.isZugedreht());
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), 0);
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);

        spiel.Zudrehen(spiel.getS2()); //Spieler2 dreht zu
        assertTrue("Es ist zugedreht", spiel.isZugedreht());
        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0),0);
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        assertEquals("keine neuen Karten werden ausgeteilt (S1 Handkarten 4)", 4, spiel.getS1().Hand.size());
        assertEquals("keine neuen Karten werden ausgeteilt (S2 Handkarten 4", 4, spiel.getS2().Hand.size());

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), 0);
        spiel.getS1().Hand.remove(0);
        spiel.getS2().Hand.remove(0);
        spiel.istSpielzuEnde(bummerl);
        assertEquals("keine neuen Karten werden ausgeteilt (S1 Handkarten 3)", 3, spiel.getS1().Hand.size());
        assertEquals("keine neuen Karten werden ausgeteilt (S2 Handkarten 3)", 3, spiel.getS2().Hand.size());
    }
}