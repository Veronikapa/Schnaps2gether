package appsolutegamesgmbh.schnaps2gether;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel2;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Bummerl2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Spiel2ComputerTest {

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
    public void zudrehen_computer_farbzwang() {
        Spiel2 spiel = new Spiel2(0);
        int runde = 0;
        int zudrehen = 0;
        boolean print = true;

        //Zug 1
        Karte ausgespielt1 = spiel.getS1().Hand.get(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        Karte ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
        spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
        spiel.istSpielzuEnde(bummerl);

        do {

            if (spiel.getS2().isIstdran() && !spiel.getS2().Hand.isEmpty()) {
                ausgespielt2 = spiel.AuspielenComputer(null);
                if (spiel.isZugedreht() && print) {
                    print = false;
                }
                spiel.ZugAuswerten(spiel.getS1().Hand.get(0), ausgespielt2, 0);
                spiel.getS1().Hand.remove(0);
                spiel.istSpielzuEnde(bummerl);
            }
            else if (spiel.getS1().isIstdran() && !spiel.getS1().Hand.isEmpty()) {
                ausgespielt1 = spiel.getS1().Hand.get(0);
                spiel.getS1().Hand.remove(0);
                ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
                if (spiel.isZugedreht()) {
                    assertTrue("Farb/Stich-Zwang", checkColor(ausgespielt1, ausgespielt2, spiel.getS2().Hand));
                }
                spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
                spiel.istSpielzuEnde(bummerl);
            }
            zudrehen++;
        }
        while (!spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void zudrehen_computer_stichzwang() {
        Spiel2 spiel = new Spiel2(0);
        int runde = 0;
        int zudrehen = 0;
        boolean print = true;

        //Zug 1
        Karte ausgespielt1 = spiel.getS1().Hand.get(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        Karte ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
        spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
        spiel.istSpielzuEnde(bummerl);

        do {

            if (spiel.getS2().isIstdran() && !spiel.getS2().Hand.isEmpty()) {
                ausgespielt2 = spiel.AuspielenComputer(null);
                if (spiel.isZugedreht() && print) {
                    print = false;
                }
                spiel.ZugAuswerten(spiel.getS1().Hand.get(0), ausgespielt2, 0);
                spiel.getS1().Hand.remove(0);
                spiel.istSpielzuEnde(bummerl);
            }
            else if (spiel.getS1().isIstdran() && !spiel.getS1().Hand.isEmpty()) {
                ausgespielt1 = spiel.getS1().Hand.get(0);
                spiel.getS1().Hand.remove(0);
                ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
                if (spiel.isZugedreht()) {
                    assertTrue("Farb/Stich-Zwang", checkValue(ausgespielt1, ausgespielt2, spiel.getS2().Hand));
                }
                spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
                spiel.istSpielzuEnde(bummerl);
            }
            zudrehen++;
        }
        while (!spiel.istSpielzuEnde(bummerl));
    }

    @Test
    public void zudrehen_S1_stichzwang() {
        Spiel2 spiel = new Spiel2(0);
        int runde = 0;
        int zudrehen = 0;
        boolean print = true;

        //Zug 1
        Karte ausgespielt1 = spiel.getS1().Hand.get(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        Karte ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
        spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
        spiel.istSpielzuEnde(bummerl);
        spiel.Zudrehen(spiel.getS1());

        do {

            if (spiel.getS2().isIstdran() && !spiel.getS2().Hand.isEmpty()) {
                ausgespielt2 = spiel.AuspielenComputer(null);
                if (spiel.isZugedreht() && print) {
                    print = false;
                }
                spiel.ZugAuswerten(spiel.getS1().Hand.get(0), ausgespielt2, 0);
                spiel.getS1().Hand.remove(0);
                spiel.istSpielzuEnde(bummerl);
            }
            else if (spiel.getS1().isIstdran() && !spiel.getS1().Hand.isEmpty()) {
                ausgespielt1 = spiel.getS1().Hand.get(0);
                spiel.getS1().Hand.remove(0);
                ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
                if (spiel.isZugedreht()) {
                    assertTrue("Farb/Stich-Zwang", checkValue(ausgespielt1, ausgespielt2, spiel.getS2().Hand));
                }
                spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
                spiel.istSpielzuEnde(bummerl);
            }
            zudrehen++;
        }
        while (!spiel.istSpielzuEnde(bummerl));
    }


    @Test
    public void zudrehen_S1_farbzwang() {
        Spiel2 spiel = new Spiel2(0);
        int runde = 0;
        int zudrehen = 0;
        boolean print = true;

        //Zug 1
        Karte ausgespielt1 = spiel.getS1().Hand.get(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        Karte ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
        spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
        spiel.istSpielzuEnde(bummerl);
        spiel.Zudrehen(spiel.getS1());

        do {
            if (spiel.getS2().isIstdran() && !spiel.getS2().Hand.isEmpty()) {
                ausgespielt2 = spiel.AuspielenComputer(null);
                if (spiel.isZugedreht() && print) {
                    print = false;
                }
                spiel.ZugAuswerten(spiel.getS1().Hand.get(0), ausgespielt2, 0);
                spiel.getS1().Hand.remove(0);
                spiel.istSpielzuEnde(bummerl);
            }
            else if (spiel.getS1().isIstdran() && !spiel.getS1().Hand.isEmpty()) {
                ausgespielt1 = spiel.getS1().Hand.get(0);
                spiel.getS1().Hand.remove(0);
                ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
                if (spiel.isZugedreht()) {
                    assertTrue("Farb/Stich-Zwang", checkColor(ausgespielt1, ausgespielt2, spiel.getS2().Hand));
                }
                spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
                spiel.istSpielzuEnde(bummerl);
            }
            zudrehen++;
        }
        while (!spiel.istSpielzuEnde(bummerl));
    }

    public boolean checkColor (Karte ks1, Karte ks2, ArrayList<Karte> hand2) {
        if (ks1.getFarbe() == ks2.getFarbe()) {
            return true;
        }
        for (Karte k : hand2) { //überprüfen, ob Computer richtige Farbe hat
            if (ks1.getFarbe() == k.getFarbe()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkValue (Karte ks1, Karte ks2, ArrayList<Karte> hand2) {
        if (ks1.getPunkte() <= ks2.getPunkte()) {
            return true;
        }
        for (Karte k : hand2) { //überprüfen, ob Computer höherwertige Karte hat
            if (ks1.getFarbe() == k.getFarbe()) {
                if (ks1.getPunkte() < k.getPunkte()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void S1_20er_40er() {
        Spiel2 spiel = new Spiel2(0);
        int runde = 0;
        int zudrehen = 0;

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

        //Zug 1
        spiel.Ansagen20er("Herz", spiel.getS1());
        if (spiel.getTrumpf() == "Herz") {
        assertEquals("S1 hat 40er angesagt", 40, spiel.getS1().getMerkePunkte());
        }
        if (spiel.getTrumpf() != "Herz") {
            assertEquals("S1 hat 20er angesagt", 20, spiel.getS1().getMerkePunkte());
        }

        Karte ausgespielt1 = spiel.getS1().Hand.get(0);
        spiel.Auspielen(spiel.getS1().Hand.get(0), spiel.getS1());
        Karte ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
        spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
        spiel.istSpielzuEnde(bummerl);

        do {
            if (spiel.getS2().isIstdran() && !spiel.getS2().Hand.isEmpty()) {
                ausgespielt2 = spiel.AuspielenComputer(null);
                spiel.ZugAuswerten(spiel.getS1().Hand.get(0), ausgespielt2, 0);
                spiel.getS1().Hand.remove(0);
                spiel.istSpielzuEnde(bummerl);
            }
            else if (spiel.getS1().isIstdran() && !spiel.getS1().Hand.isEmpty()) {
                ausgespielt1 = spiel.getS1().Hand.get(0);
                spiel.getS1().Hand.remove(0);
                ausgespielt2 = spiel.AuspielenComputer(ausgespielt1);
                spiel.ZugAuswerten(ausgespielt1, ausgespielt2, 0);
                spiel.istSpielzuEnde(bummerl);
            }
            zudrehen++;
        }
        while (!spiel.istSpielzuEnde(bummerl));
    }


}