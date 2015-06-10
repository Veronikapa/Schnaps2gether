package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 27.05.15.
 */
public class Spiel3ZugAuswertenTest {

    Bummerl3 bummerl = new Bummerl3();

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
    public void ZugAuswertenTrumpfKaroGewinnerS2() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);


        if (spiel.getTrumpf() == "Herz") {
            assertEquals("Spieler1 erhält 9 Punkte", 9, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        }

        if (spiel.getTrumpf() == "Karo") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 9 Punkte", 9, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        }

        if (spiel.getTrumpf() == "Pik") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 9 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        }

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));

        assertEquals("HandKArte S1", 3, spiel.getS1().Hand.get(0).getPunkte());
        assertEquals("HandKArte S2",4, spiel.getS2().Hand.get(0).getPunkte());
        assertEquals("HandKArte S3", 10, spiel.getS3().Hand.get(0).getPunkte());


        if (spiel.getTrumpf() == "Herz") {
            assertEquals("Spieler1 erhält 9 Punkte", 9, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        }

        if (spiel.getTrumpf() == "Karo") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 26 Punkte", 26, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        }

        if (spiel.getTrumpf() == "Pik") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 9 Punkte", 9, spiel.getS3().getPunkte());
            assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        }
    }

    @Test
    public void ZugAuswertenTrumpfHerzGewinnerS1() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Herz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);


        if (spiel.getTrumpf() == "Herz") {
            assertEquals("Spieler1 erhält 9 Punkte", 9, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        }

        if (spiel.getTrumpf() == "Karo") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 9 Punkte", 9, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        }

        if (spiel.getTrumpf() == "Pik") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 9 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        }

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));

        assertEquals("HandKArte S1", 3, spiel.getS1().Hand.get(0).getPunkte());
        assertEquals("HandKArte S2", 4, spiel.getS2().Hand.get(0).getPunkte());
        assertEquals("HandKArte S3", 10, spiel.getS3().Hand.get(0).getPunkte());


        if (spiel.getTrumpf() == "Herz") {
            assertEquals("Spieler1 erhält 26 Punkte", 26, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        }

        if (spiel.getTrumpf() == "Karo") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 26 Punkte", 26, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        }

        if (spiel.getTrumpf() == "Pik") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 9 Punkte", 9, spiel.getS3().getPunkte());
            assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        }
    }

    @Test
    public void ZugAuswertenTrumpfPikGewinnerS3() throws WrongGameException {
        Spiel3 spiel = new Spiel3(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);
        spiel.getS1().Hand.add(karobube);

        //Karten Spieler2
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(kreuzkoenig);

        Karte ausgespieltS1 = spiel.getS1().Hand.get(0);
        Karte ausgespieltS2 = spiel.getS2().Hand.get(0);
        Karte ausgespieltS3 = spiel.getS3().Hand.get(0);

        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        spiel.Auspielen(spiel.getS2().Hand.get(0), spiel.getS2());
        spiel.Auspielen(spiel.getS3().Hand.get(0), spiel.getS3());

        spiel.ZugAuswerten(ausgespieltS1, ausgespieltS2, ausgespieltS3);


        if (spiel.getTrumpf() == "Herz") {
            assertEquals("Spieler1 erhält 9 Punkte", 9, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        }

        if (spiel.getTrumpf() == "Karo") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 9 Punkte", 9, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        }

        if (spiel.getTrumpf() == "Pik") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 9 Punkte", 9, spiel.getS3().getPunkte());
            assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        }

        spiel.ZugAuswerten(spiel.getS1().Hand.get(0), spiel.getS2().Hand.get(0), spiel.getS3().Hand.get(0));

        assertEquals("HandKArte S1", 3, spiel.getS1().Hand.get(0).getPunkte());
        assertEquals("HandKArte S2", 3, ausgespieltS2.getPunkte());
        assertEquals("HandKArte S3", 10, spiel.getS3().Hand.get(0).getPunkte());


        if (spiel.getTrumpf() == "Herz") {
            assertEquals("Spieler1 erhält 9 Punkte", 9, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler1 ist dran", spiel.getS1().isIstdran());
        }

        if (spiel.getTrumpf() == "Karo") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 25 Punkte", 25, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 0 Punkte", 0, spiel.getS3().getPunkte());
            assertTrue("Spieler2 ist dran", spiel.getS2().isIstdran());
        }

        if (spiel.getTrumpf() == "Pik") {
            assertEquals("Spieler1 erhält 0 Punkte", 0, spiel.getS1().getPunkte());
            assertEquals("Spieler2 erhält 0 Punkte", 0, spiel.getS2().getPunkte());
            assertEquals("Spieler3 erhält 26 Punkte", 26, spiel.getS3().getPunkte());
            assertTrue("Spieler3 ist dran", spiel.getS3().isIstdran());
        }
    }
}
