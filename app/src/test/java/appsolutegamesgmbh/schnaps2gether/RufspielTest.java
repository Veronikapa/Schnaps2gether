package appsolutegamesgmbh.schnaps2gether;

import org.junit.Before;
import org.junit.Test;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Spiel3;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Rufspiel;
import appsolutegamesgmbh.schnaps2gether.DataStructure.WrongGameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by n15r2 on 12.05.15.
 */
public class RufspielTest {
    Rufspiel spiel;

    @Before
    public void setUp() {

    }

    @Test
    public void Rufspiel_normal() throws WrongGameException {
        spiel = new Rufspiel("normal");
        assertEquals("normal - 3 Punkte", 3, spiel.getPunkte());

    }
    @Test
    public void Rufspiel_Schnapser() throws WrongGameException {
        spiel = new Rufspiel("Schnapser");
        assertEquals("Schnapser - 6 Punkte", 6, spiel.getPunkte());

    }
    @Test
    public void Rufspiel_Land() throws WrongGameException {
        spiel = new Rufspiel("Land");
        assertEquals("Land - 9 Punkte", 9, spiel.getPunkte());

    }
    @Test
    public void Rufspiel_Kontraschnapser() throws WrongGameException {
        spiel = new Rufspiel("Kontraschnapser");
        assertEquals("Kontraschnapser - 12 Punkte", 12, spiel.getPunkte());

    }
    @Test
    public void Rufspiel_Bauernschnapser() throws WrongGameException {
        spiel = new Rufspiel("Bauernschnapser");
        assertEquals("Bauernschnapser - 12 Punkte", 12, spiel.getPunkte());

    }
    @Test
    public void Rufspiel_Kontrabauernschnapserl() throws WrongGameException {
        spiel = new Rufspiel("Kontrabauernschnapser");
        assertEquals("Kontrabauernschnapser - 24 Punkte", 24, spiel.getPunkte());

    }
    @Test
    public void Rufspiel_Herrenjodler() throws WrongGameException {
        spiel = new Rufspiel("Herrenjodler");
        assertEquals("Herrenjodler - 24 Punkte", 24, spiel.getPunkte());

    }
    @Test
    public void Rufspiel_Farbenjodler() throws WrongGameException {
        spiel = new Rufspiel("Farbenjodler");
        assertEquals("Farbenjodler - 18 Punkte", 18, spiel.getPunkte());

    }

}
