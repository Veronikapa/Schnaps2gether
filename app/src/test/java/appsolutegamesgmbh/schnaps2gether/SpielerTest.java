package appsolutegamesgmbh.schnaps2gether;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by n15r2 on 12.05.15.
 */
public class SpielerTest {

    @Before
    public void setUp() {

    }

    @Test
    public void Spiel2neuenSpielererstellenTest() {
        Spieler spieler = new Spieler();
        assertEquals("keine Handkarten zugewiesen", 0, spieler.Hand.size());
        assertEquals("keine Punkte", 0, spieler.getPunkte());
        assertEquals("keine Karten gestochen", 0, spieler.Gestochen.size());
        assertTrue("ist nicht dran", !spieler.isIstdran());
    }
}
