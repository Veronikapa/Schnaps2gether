package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.06.15.
 */
public class Spiel4darfSpielansagenTest {

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
    public void S1_darfSpielAnsagen_Schnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",! spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS4()));
    }

    @Test
    public void S2_darfSpielAnsagen_Schnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",! spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS4()));
    }

    @Test
    public void S3_darfSpielAnsagen_Schnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(2);
        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",! spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS4()));
    }

    @Test
    public void S4_darfSpielAnsagen_Schnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(3);
        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen",spiel.DarfSpielAnsagen(new Rufspiel("Schnapser"), spiel.getS4()));
    }

    @Test
    public void S1_darfSpielAnsagen_Bauernschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",! spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS4()));
    }

    @Test
    public void S2_darfSpielAnsagen_Bauernschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",! spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS4()));
    }

    @Test
    public void S3_darfSpielAnsagen_Bauernschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(2);
        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",! spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS4()));
    }

    @Test
    public void S4_darfSpielAnsagen_Bauernschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(3);
        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen",spiel.DarfSpielAnsagen(new Rufspiel("Bauernschnapser"), spiel.getS4()));
    }

    @Test
    public void keinSpieler_darfSpielAnsagen_Farbenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(karobube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(herzbube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(pikbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS4()));
    }

    @Test
    public void S1_darfSpielAnsagen_Farbenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(karobube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(karodame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS4()));
    }

    @Test
    public void S2_darfSpielAnsagen_Farbenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(pikkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(karobube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(herzkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(karodame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",!spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS4()));
    }

    @Test
    public void S3_darfSpielAnsagen_Farbenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(kreuzkoenig);
        spiel.getS1().Hand.add(karo10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(herz10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(karodame);
        spiel.getS4().Hand.add(pikkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",!spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS4()));
    }

    @Test
    public void S4_darfSpielAnsagen_Farbenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(kreuzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(pikass);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(karoass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Farbenjodler"), spiel.getS4()));
    }

    @Test
    public void allerSpieler_darfSpielAnsagen_Land() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        assertTrue("Spieler1 darf Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Land"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Land"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Land"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Land"), spiel.getS4()));
    }

    @Test
    public void nurS2_S3_S4_darfSpielAnsagen_Kontraschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS4()));
    }

    @Test
    public void nurS1_S3_S4_darfSpielAnsagen_Kontraschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS4()));
    }

    @Test
    public void nurS1_S2_S4_darfSpielAnsagen_Kontraschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(2);
        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS4()));
    }

    @Test
    public void nurS1_S2_S3_darfSpielAnsagen_Kontraschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(3);
        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Kontraschnapser"), spiel.getS4()));
    }

    @Test
    public void nurS2_S3_S4_darfSpielAnsagen_Kontrabauernschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS4()));
    }

    @Test
    public void nurS1_S3_S4_darfSpielAnsagen_Kontrabauernschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(1);
        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS4()));
    }

    @Test
    public void nurS1_S2_S4_darfSpielAnsagen_Kontrabauernschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(2);
        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS4()));
    }

    @Test
    public void nurS1_S2_S3_darfSpielAnsagen_Kontrabauernschnapser() throws WrongGameException {
        Spiel4 spiel = new Spiel4(3);
        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Kontrabauernschnapser"), spiel.getS4()));
    }

    @Test
    public void keinSpieler_darfSpielAnsagen_Herrenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);

        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(karobube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(herzbube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(pikbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS4()));
    }

    @Test
    public void S1_darfSpielAnsagen_Herrenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz",0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(karobube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(karodame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS4()));
    }

    @Test
    public void S2_darfSpielAnsagen_Herrenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo",0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(pikkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(karobube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(herzkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(karodame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",!spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS4()));
    }

    @Test
    public void S3_darfSpielAnsagen_Herrenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Pik",0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(kreuzkoenig);
        spiel.getS1().Hand.add(karo10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(herz10);
        spiel.getS2().Hand.add(karoass);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(karodame);
        spiel.getS4().Hand.add(pikkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf nicht Spiel ansagen",!spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS4()));
    }

    @Test
    public void S4_darfSpielAnsagen_Herrenjodler() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Kreuz",0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Karten Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(kreuzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Karten Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(pikass);

        //Karten Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(karoass);

        //Karten Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertTrue("Spieler1 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS1()));
        assertTrue("Spieler2 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS2()));
        assertTrue("Spieler3 darf nicht Spiel ansagen", !spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS3()));
        assertTrue("Spieler4 darf Spiel ansagen", spiel.DarfSpielAnsagen(new Rufspiel("Herrenjodler"), spiel.getS4()));
    }

}
