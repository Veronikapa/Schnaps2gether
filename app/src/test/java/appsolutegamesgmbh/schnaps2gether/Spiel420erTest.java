package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel4;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;

/**
 * Created by n15r2 on 12.06.15.
 */
public class Spiel420erTest {

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
    public void hat20er_funktioniert_S1() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S1 hat 20er", 1, spiel.hat20er(spiel.getS1()).size());

        if (1 == spiel.hat20er(spiel.getS1()).size()) {
            spiel.Ansagen20er("Herz", spiel.getS1());
        }


        assertEquals("S1 hat 20er angesagt", 20, spiel.getS1().getMerkePunkte());

    }

    @Test
    public void hat40er_funktioniert_S1() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S1 hat 20er", 1, spiel.hat20er(spiel.getS1()).size());

        if (1 == spiel.hat20er(spiel.getS1()).size()) {
            spiel.Ansagen20er("Herz", spiel.getS1());
        }


        assertEquals("S1 hat 40er angesagt", 40, spiel.getS1().getMerkePunkte());

    }

    @Test
    public void hat20er_funktioniert_S2() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S2 hat 20er", 1, spiel.hat20er(spiel.getS2()).size());

        if (1 == spiel.hat20er(spiel.getS2()).size()) {
            spiel.Ansagen20er("Karo", spiel.getS2());
        }


        assertEquals("S2 hat 20er angesagt", 20, spiel.getS2().getMerkePunkte());

    }

    @Test
    public void hat40er_funktioniert_S2() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S2 hat 40er", 1, spiel.hat20er(spiel.getS2()).size());

        if (1 == spiel.hat20er(spiel.getS1()).size()) {
            spiel.Ansagen20er("Karo", spiel.getS2());
        }


        assertEquals("S2 hat 40er angesagt", 40, spiel.getS2().getMerkePunkte());

    }

    @Test
    public void hat20er_funktioniert_S3() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S3 hat 20er", 1, spiel.hat20er(spiel.getS3()).size());

        if (1 == spiel.hat20er(spiel.getS3()).size()) {
            spiel.Ansagen20er("Pik", spiel.getS3());
        }


        assertEquals("S3 hat 20er angesagt", 20, spiel.getS3().getMerkePunkte());

    }

    @Test
    public void hat40er_funktioniert_S3() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S3 hat 40er", 1, spiel.hat20er(spiel.getS3()).size());

        if (1 == spiel.hat20er(spiel.getS3()).size()) {
            spiel.Ansagen20er("Pik", spiel.getS3());
        }


        assertEquals("S3 hat 40er angesagt", 40, spiel.getS3().getMerkePunkte());
    }

    @Test
    public void hat20er_funktioniert_S4() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S4 hat 20er", 1, spiel.hat20er(spiel.getS4()).size());

        if (1 == spiel.hat20er(spiel.getS4()).size()) {
            spiel.Ansagen20er("Karo", spiel.getS4());
        }


        assertEquals("S4 hat 20er angesagt", 20, spiel.getS4().getMerkePunkte());
    }

    @Test
    public void hat40er_funktioniert_S4() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S4 hat 40er", 1, spiel.hat20er(spiel.getS4()).size());

        if (1 == spiel.hat20er(spiel.getS4()).size()) {
            spiel.Ansagen20er("Karo", spiel.getS4());
        }


        assertEquals("S4 hat 40er angesagt", 40, spiel.getS4().getMerkePunkte());
    }

    @Test
    public void hatkeinen20er_S1() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(karobube);
        spiel.getS1().Hand.add(karodame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(herzbube);
        spiel.getS2().Hand.add(herzdame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S1 hat keinen 20er", 0, spiel.hat20er(spiel.getS1()).size());
        assertEquals("S1 hat keine gemerkten Punkte", 0, spiel.getS1().getMerkePunkte());

    }

    @Test
    public void hatkeinen40er_S1() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(karobube);
        spiel.getS1().Hand.add(karodame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(herzbube);
        spiel.getS2().Hand.add(herzdame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S1 hat keinen 20er", 0, spiel.hat20er(spiel.getS1()).size());
        assertEquals("S1 hat keine gemerkten Punkte", 0, spiel.getS1().getMerkePunkte());
    }

    @Test
    public void hatkeinen20er_S2() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(karobube);
        spiel.getS1().Hand.add(karodame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(herzbube);
        spiel.getS2().Hand.add(herzdame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S2 hat keinen 20er", 0, spiel.hat20er(spiel.getS2()).size());
        assertEquals("S2 hat keine gemerkten Punkte", 0, spiel.getS2().getMerkePunkte());
    }

    @Test
    public void hatkeinen40er_S2() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(karobube);
        spiel.getS1().Hand.add(karodame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(herzbube);
        spiel.getS2().Hand.add(herzdame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(pikbube);
        spiel.getS3().Hand.add(pikdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S2 hat keinen 40er", 0, spiel.hat20er(spiel.getS2()).size());
        assertEquals("S2 hat keine gemerkten Punkte", 0, spiel.getS2().getMerkePunkte());
    }

    @Test
    public void hatkeinen20er_S3() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(karobube);
        spiel.getS3().Hand.add(karodame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S3 hat keinen 20er", 0, spiel.hat20er(spiel.getS3()).size());
        assertEquals("S3 hat keine gemerkten Punkte", 0, spiel.getS3().getMerkePunkte());
    }

    @Test
    public void hatkeinen40er_S3() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Pik", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(pikbube);
        spiel.getS2().Hand.add(pikdame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(karobube);
        spiel.getS3().Hand.add(karodame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(kreuzbube);
        spiel.getS4().Hand.add(kreuzdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S3 hat keinen 40er", 0, spiel.hat20er(spiel.getS3()).size());
        assertEquals("S3 hat keine gemerkten Punkte", 0, spiel.getS3().getMerkePunkte());
    }

    @Test
    public void hatkeinen20er_S4() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Herz", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(pikbube);
        spiel.getS4().Hand.add(pikdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S4 hat keinen 20er", 0, spiel.hat20er(spiel.getS4()).size());
        assertEquals("S4 hat keine gemerkten Punkte", 0, spiel.getS4().getMerkePunkte());
    }

    @Test
    public void hatkeinen40er_S4() throws WrongGameException {
        Spiel4 spiel = new Spiel4(0);
        spiel.Trumpfansagen("Karo", 0);
        spiel.getS1().Hand.removeAll(spiel.getS1().Hand);
        spiel.getS2().Hand.removeAll(spiel.getS2().Hand);
        spiel.getS3().Hand.removeAll(spiel.getS3().Hand);
        spiel.getS4().Hand.removeAll(spiel.getS4().Hand);

        //Spieler1
        spiel.getS1().Hand.add(herzbube);
        spiel.getS1().Hand.add(herzdame);
        spiel.getS1().Hand.add(herzkoenig);
        spiel.getS1().Hand.add(herz10);
        spiel.getS1().Hand.add(herzass);

        //Spieler2
        spiel.getS2().Hand.add(karobube);
        spiel.getS2().Hand.add(karodame);
        spiel.getS2().Hand.add(karokoenig);
        spiel.getS2().Hand.add(karo10);
        spiel.getS2().Hand.add(karoass);

        //Spieler3
        spiel.getS3().Hand.add(kreuzbube);
        spiel.getS3().Hand.add(kreuzdame);
        spiel.getS3().Hand.add(pikkoenig);
        spiel.getS3().Hand.add(pik10);
        spiel.getS3().Hand.add(pikass);

        //Spieler4
        spiel.getS4().Hand.add(pikbube);
        spiel.getS4().Hand.add(pikdame);
        spiel.getS4().Hand.add(kreuzkoenig);
        spiel.getS4().Hand.add(kreuz10);
        spiel.getS4().Hand.add(kreuzass);

        assertEquals("S4 hat keinen 40er", 0, spiel.hat20er(spiel.getS4()).size());
        assertEquals("S4 hat keine gemerkten Punkte", 0, spiel.getS4().getMerkePunkte());
    }

}
